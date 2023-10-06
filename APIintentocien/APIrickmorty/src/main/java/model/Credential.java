package model;

import lombok.Data;

@Data
public class Credential {

    /*Atributos*/

    private String user;
    private String passwd;

    /*Constructores*/

    public Credential(String user, String passwd) {
        this.user = "root";
        this.passwd = "2dam";
    }
}
