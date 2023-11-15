package dao.imp;

import common.Configuration;
import dao.DAOlogin;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Credential;
import model.errors.ErrorCCustomer;

import java.util.List;

public class DAOloginIMP implements DAOlogin {

    private final Configuration conf;

    @Inject
    public DAOloginIMP(Configuration conf) {
        this.conf = conf;
    }

    @Override
    public boolean doLogin(Credential credential) {
        return credential.getUser() && credential.getPasswd();
    }

    @Override
    public Either<ErrorCCustomer, Credential> get(int id) {
        return null;
    }

    @Override
    public Either<ErrorCCustomer, List<Credential>> getAll() {
        return null;
    }

}
