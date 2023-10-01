package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Client {

    /*Atributos*/

    private int id_c;
    private String firstName;
    private String secondName;
    private String email;
    private int phoneNumber;
    private LocalDate date;

    /*Construcctores*/

    public Client(int id_c, String firstName, String secondName, String email, int phoneNumber, LocalDate date) {
        this.id_c = id_c;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }


    public String toStringFile() {
        return id_c + ";" + firstName + ";" + secondName + ";" + email + ";" + phoneNumber + ";" + date + "\n";
    }
}
