package model;

import lombok.Data;

@Data
public class Client {

    /*Atributos*/

    private int id_c;
    private String firstName;
    private String secondName;

    /*Construcctores*/

    public Client(int id_c, String firstName, String secondName) {
        this.id_c = id_c;
        this.firstName = firstName;
        this.secondName = secondName;
    }

}
