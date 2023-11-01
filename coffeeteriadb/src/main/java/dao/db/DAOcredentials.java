package dao.db;

import common.Configuration;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Credential;
import model.errors.ErrorCCredential;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOcredentials {


    private final Configuration config;
    private final DBConnection db;

    @Inject
    public DAOcredentials(Configuration config, DBConnection db) {
        this.config = config;
        this.db = db;
    }

    public Either<ErrorCCredential, List<Credential>> getAll(){
        List<Credential> credentialList = new ArrayList<>();
        Either<ErrorCCredential, List<Credential>> res;
        try (Connection myConnection = db.getConnection()){
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from credential");
            credentialList = readRS(rs);
            res = Either.right(credentialList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCredential(e.getMessage(), 0));
        }
        return res;
    }

    private List<Credential> readRS(ResultSet rs) throws SQLException {
        List<Credential> credentialList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String user = rs.getString("username");
            String password = rs.getString("password");
            credentialList.add(new Credential(id, user, password));
        }
        return credentialList;
    }
}
