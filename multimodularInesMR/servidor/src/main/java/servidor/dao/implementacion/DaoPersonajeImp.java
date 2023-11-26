package servidor.dao.implementacion;

import domain.errores.ApiError;
import domain.modelo.Faccion;
import domain.modelo.Personaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.common.SqlQueries;
import servidor.dao.ConstantsDao;
import servidor.dao.DaoPersonaje;
import servidor.dao.DbConnectionPool;
import servidor.domain.modelo.excepciones.BadArgumentException;
import servidor.domain.modelo.excepciones.BaseCaidaException;
import servidor.domain.modelo.excepciones.WrongObjectException;
import servidor.domain.servicios.ServicioFaccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoPersonajeImp implements DaoPersonaje {

    private final DbConnectionPool db;
    private final ServicioFaccion servicioFaccion;

    @Inject
    public DaoPersonajeImp(DbConnectionPool db, ServicioFaccion servicioFaccion) {
        this.db = db;
        this.servicioFaccion = servicioFaccion;
    }


    @Override
    public Either<ApiError, List<Personaje>> getAll() {
        List<Personaje> personajeList;
        Either<ApiError, List<Personaje>> res;
        try (Connection myconnection = db.getConnection()) {
            Statement stmt = myconnection.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueries.SELECT_FROM_PERSONAJES);
            personajeList = readRS(rs);
            res = Either.right(personajeList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Personaje> get(int id) {
        List<Personaje> personajeList;
        Either<ApiError, Personaje> res;
        try (Connection myConnection = db.getConnection()) {
            PreparedStatement pstmt = myConnection.prepareStatement(SqlQueries.SELECT_FROM_PERSONAJES_WHERE_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            personajeList = readRS(rs);
            if (!personajeList.isEmpty()) {
                res = Either.right(personajeList.get(0));
            } else {
                throw new WrongObjectException(ConstantsDao.WRONG_OBJECT_EXCEPTION);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Integer> add(Personaje nuevoPersonaje) {
        int rowsAffected;
        Either<ApiError, Integer> res;
        try (Connection myConnection = db.getConnection()) {
            myConnection.setAutoCommit(false);
            try (PreparedStatement pstmt = myConnection.prepareStatement(SqlQueries.INSERT_INTO_PERSONAJES, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, nuevoPersonaje.getNombre());
                pstmt.setInt(2, nuevoPersonaje.getRaza());
                pstmt.setString(3, nuevoPersonaje.getPlanetaRes());
                rowsAffected = pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nuevoPersonaje.setId(rs.getInt(1));
                }
                if (rowsAffected != 1) {
                    myConnection.rollback();
                    throw new BadArgumentException(ConstantsDao.BAD_ARGUMENT_EXCEPTION);
                } else {
                    myConnection.commit();
                    res = Either.right(rowsAffected);
                }
            } catch (SQLException e) {
                myConnection.rollback();
                log.error(e.getMessage(), e);
                throw new BadArgumentException(ConstantsDao.BAD_ARGUMENT_EXCEPTION);
            } finally {
                myConnection.setAutoCommit(true);

            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Integer> update(Personaje actualizadoPersonaje) {
        int rowsAffected;
        Either<ApiError, Integer> res;
        try (Connection myConnection = db.getConnection()) {
            try (PreparedStatement pstmt = myConnection.prepareStatement(SqlQueries.UPDATE_PERSONAJES)) {
                myConnection.setAutoCommit(false);
                pstmt.setString(1, actualizadoPersonaje.getNombre());
                pstmt.setString(2, actualizadoPersonaje.getPlanetaRes());
                pstmt.setInt(3, actualizadoPersonaje.getId());
                rowsAffected = pstmt.executeUpdate();
                myConnection.commit();
                res = Either.right(rowsAffected);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new BadArgumentException(ConstantsDao.BAD_ARGUMENT_EXCEPTION);
            } finally {
                myConnection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Integer> delete(int id) {
        Either<ApiError, Integer> res;
        try (Connection myConnection = db.getConnection()) {
            myConnection.setAutoCommit(false);
            try {
                String deleteFaccionPersonajeSQL = SqlQueries.DELETE_FROM_FACCION_PERSONAJE_WHERE_ID_PERSONAJE;
                try (PreparedStatement pstmtFaccionPersonaje = myConnection.prepareStatement(deleteFaccionPersonajeSQL)) {
                    pstmtFaccionPersonaje.setInt(1, id);
                    pstmtFaccionPersonaje.executeUpdate();
                }
                String deletePersonajeSQL = SqlQueries.DELETE_FROM_PERSONAJES_WHERE_ID;
                try (PreparedStatement pstmtPersonaje = myConnection.prepareStatement(deletePersonajeSQL)) {
                    pstmtPersonaje.setInt(1, id);
                    int rowsAffectedPersonaje = pstmtPersonaje.executeUpdate();
                    if (rowsAffectedPersonaje != 1) {
                        throw new WrongObjectException(ConstantsDao.WRONG_OBJECT_EXCEPTION);
                    }
                }
                myConnection.commit();
                res = Either.right(1);
            } catch (SQLException e) {
                myConnection.rollback();
                log.error(e.getMessage(), e);
                throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
            } finally {
                myConnection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Integer> deleteMultiple(int idFaccion) {
        Either<ApiError, Integer> res;
        try (Connection myConnection = db.getConnection()) {
            myConnection.setAutoCommit(false);
            try {
                String deleteFaccionPersonajeSQL = SqlQueries.DELETE_FROM_FACCION_PERSONAJE_WHERE_ID_FACCION;
                try (PreparedStatement pstmtFaccionPersonaje = myConnection.prepareStatement(deleteFaccionPersonajeSQL)) {
                    pstmtFaccionPersonaje.setInt(1, idFaccion);
                    int rowsAffectedFaccionPersonaje = pstmtFaccionPersonaje.executeUpdate();
                    if (rowsAffectedFaccionPersonaje == 0) {
                        throw new WrongObjectException(ConstantsDao.WRONG_OBJECT_EXCEPTION);
                    }
                }

                String deletePersonajeSQL = SqlQueries.DELETE_FROM_PERSONAJES_WHERE_ID_NOT_IN_SELECT_ID_PERSONAJE_FROM_FACCION_PERSONAJE;
                try (PreparedStatement pstmtPersonaje = myConnection.prepareStatement(deletePersonajeSQL)) {
                    int rowsAffectedPersonaje = pstmtPersonaje.executeUpdate();
                    myConnection.commit();
                    res = Either.right(rowsAffectedPersonaje);
                }
            } catch (SQLException e) {
                myConnection.rollback();
                log.error(e.getMessage(), e);
                throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
            } finally {
                myConnection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, List<Faccion>> getFaccionesByPersonaje(int idPersonaje) {
        List<Faccion> faccionList;
        Either<ApiError, List<Faccion>> res;
        try (Connection myConnection = db.getConnection()) {
            String sql = SqlQueries.SELECT_FACCIONES_JOIN;
            PreparedStatement pstmt = myConnection.prepareStatement(sql);
            pstmt.setInt(1, idPersonaje);
            ResultSet rs = pstmt.executeQuery();
            faccionList = readFaccionesRS(rs);
            res = Either.right(faccionList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    private List<Faccion> readFaccionesRS(ResultSet rs) {
        try {
            List<Faccion> faccionList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(ConstantsDao.IDFACCIONES);
                String nombreFaccion = rs.getString(ConstantsDao.NOMBRE_FACCION);
                faccionList.add(new Faccion(id, nombreFaccion));
            }
            return faccionList;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
    }


    private List<Personaje> readRS(ResultSet rs) {
        try {
            List<Personaje> personajeList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(ConstantsDao.ID);
                String nombre = rs.getString(ConstantsDao.NOMBRE);
                int raza = rs.getInt(ConstantsDao.RAZA);
                String planetaRes = rs.getString(ConstantsDao.PLANETA_RESIDENCIA);
                personajeList.add(new Personaje(id, nombre, raza, planetaRes, servicioFaccion.get(id).get()));
            }
            return personajeList;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
    }
}
