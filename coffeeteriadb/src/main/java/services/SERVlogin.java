package services;

import dao.DAOlogin;
import jakarta.inject.Inject;
import model.Credential;

public class SERVlogin {

    @Inject
    private DAOlogin daOlogin;

    public boolean doLogin(Credential credential){
        return daOlogin.doLogin(credential);
    }
}
