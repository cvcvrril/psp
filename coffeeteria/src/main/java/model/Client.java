package model;

import lombok.Data;

@Data
public class Client {

    /*Atributos*/

    private int id_c;
    private String firstName;
    private String secondName;
    private String email;
    private int phoneNumber;

    /*Construcctores*/

    public Client(int id_c, String firstName, String secondName, String email, int phoneNumber) {
        this.id_c = id_c;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
