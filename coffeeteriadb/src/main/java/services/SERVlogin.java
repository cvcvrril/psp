package services;

import dao.interfaces.DAOlogin;
import jakarta.inject.Inject;
import model.Credential;

public class SERVlogin {

    @Inject
    private DAOlogin daOlogin;

    public boolean doLogin(Credential credential){
        return daOlogin.doLogin(credential);
    }
}
