package servidor.dao.implementacion;

import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.common.SqlQueries;
import servidor.dao.ConstantsDao;
import servidor.dao.DaoRegistro;
import servidor.dao.DbConnectionPool;
import servidor.domain.modelo.excepciones.BadArgumentException;
import servidor.domain.modelo.excepciones.BaseCaidaException;
import servidor.domain.modelo.excepciones.WrongObjectException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoRegistroImp implements DaoRegistro {

    private final DbConnectionPool db;

    @Inject
    public DaoRegistroImp(DbConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<ApiError, Usuario> get(int id) {
        List<Usuario> usuarioList;
        Either<ApiError, Usuario> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement(SqlQueries.SELECT_FROM_CREDENCIALES_WHERE_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            usuarioList = readRS(rs);
            if (!usuarioList.isEmpty()){
                res = Either.right(usuarioList.get(0));
            }else {
                throw new WrongObjectException(ConstantsDao.WRONG_OBJECT_EXCEPTION);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Integer> add(Usuario nuevoUsuario) {
        int rowsAffected;
        Either<ApiError, Integer> res;
        try (Connection myConnection = db.getConnection()){
            myConnection.setAutoCommit(false);
            try (PreparedStatement pstmt = myConnection.prepareStatement(SqlQueries.INSERT_INTO_CREDENCIALES_USUARIO_PASS_VALUES, Statement.RETURN_GENERATED_KEYS )){
                pstmt.setString(1, nuevoUsuario.getUsername());
                pstmt.setString(2,nuevoUsuario.getPassword());
                rowsAffected = pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()){
                    nuevoUsuario.setId(rs.getInt(1));
                }
                if (rowsAffected!=1){
                    myConnection.rollback();
                    throw new BadArgumentException(ConstantsDao.BAD_ARGUMENT_EXCEPTION);
                } else {
                    myConnection.commit();
                    res = Either.right(rowsAffected);
                }
            }catch (SQLException e) {
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

    private List<Usuario> readRS(ResultSet rs) {
        try {
            List<Usuario> usuarioList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(ConstantsDao.ID);
                String username = rs.getString(ConstantsDao.USUARIO);
                String password = rs.getString(ConstantsDao.PASS);
                usuarioList.add(new Usuario(id, username, password));
            }
            return usuarioList;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
    }

}
