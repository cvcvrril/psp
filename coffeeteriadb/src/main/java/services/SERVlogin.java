package services;

import dao.db.DAOcredentials;
import dao.interfaces.DAOlogin;
import jakarta.inject.Inject;
import model.Credential;

import java.util.ArrayList;
import java.util.List;

public class SERVlogin {

    private final DAOlogin daOlogin;
    private final DAOcredentials daOcredentials;

    @Inject
    public SERVlogin(DAOlogin daOlogin, DAOcredentials daOcredentials) {
        this.daOlogin = daOlogin;
        this.daOcredentials = daOcredentials;
    }

    public boolean doLogin(Credential credential){
        return daOlogin.doLogin(credential);
    }

    public Credential getCredentialByUsername(String username) {
        List<Credential> credentials = daOcredentials.getAll().getOrElse(new ArrayList<>());

        for (Credential credential : credentials) {
            if (credential.getUserName().equals(username)) {
                return credential;
            }
        }
        return null;
    }
}
