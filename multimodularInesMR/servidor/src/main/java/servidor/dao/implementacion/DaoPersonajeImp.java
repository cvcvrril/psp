package servidor.dao.implementacion;

import domain.errores.ApiError;
import domain.modelo.Personaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.dao.ConstantsDao;
import servidor.dao.DaoPersonaje;
import servidor.dao.DbConnectionPool;
import servidor.domain.modelo.excepciones.BaseCaidaException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoPersonajeImp implements DaoPersonaje {

    private final DbConnectionPool db;

    @Inject
    public DaoPersonajeImp(DbConnectionPool db) {
        this.db = db;
    }


    @Override
    public Either<ApiError, List<Personaje>> getAll() {
        List<Personaje> personajeList;
        Either <ApiError, List<Personaje>> res;
        try (Connection myconnection = db.getConnection()){
            Statement stmt = myconnection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from personajes");
            personajeList = readRS(rs);
            res = Either.right(personajeList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Personaje> get(int i) {
        return null;
    }

    private List<Personaje> readRS(ResultSet rs){
        try {
            List<Personaje> personajeList = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int raza = rs.getInt("raza");
                String planetaRes = rs.getString("planeta_residencia");
                personajeList.add(new Personaje(id, nombre, raza, planetaRes));
            }
            return personajeList;
        }catch (SQLException e){
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
    }
}
