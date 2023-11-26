package servidor.dao.implementacion;

import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.common.SqlQueries;
import servidor.dao.ConstantsDao;
import servidor.dao.DaoLogin;
import servidor.dao.DbConnectionPool;
import servidor.domain.modelo.excepciones.BaseCaidaException;
import servidor.domain.modelo.excepciones.WrongObjectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoLoginImp implements DaoLogin {

    private final DbConnectionPool db;

    @Inject
    public DaoLoginImp(DbConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<ApiError, Usuario> check(String usuario) {
        List<Usuario> usuarioList;
        Either<ApiError, Usuario> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement(SqlQueries.SELECT_FROM_CREDENCIALES_WHERE_USUARIO);
            pstmt.setString(1, usuario);
            ResultSet rs = pstmt.executeQuery();
            usuarioList = readRS(rs);
            if (!usuarioList.isEmpty()){
                res = Either.right(usuarioList.get(0));
            } else {
                throw new WrongObjectException(ConstantsDao.WRONG_OBJECT_EXCEPTION);
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
