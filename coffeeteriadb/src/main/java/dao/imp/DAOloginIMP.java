package dao.imp;

import common.Configuration;
import dao.interfaces.DAOlogin;
import jakarta.inject.Inject;
import model.Credential;

public class DAOloginIMP implements DAOlogin {

    private final Configuration conf;

    @Inject
    public DAOloginIMP(Configuration conf) {
        this.conf = conf;
    }

    @Override
    public boolean doLogin(Credential credential) {
        return credential.getUser().equals("root") && credential.getPasswd().equals("2dam");
    }

}
