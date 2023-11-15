package dao;

import io.vavr.control.Either;
import model.Credential;
import model.errors.ErrorCCustomer;

import java.util.List;

public interface DAOlogin {

    boolean doLogin(Credential credential);
    Either<ErrorCCustomer,Credential> get(int id);
    Either<ErrorCCustomer, List<Credential>> getAll();

}
