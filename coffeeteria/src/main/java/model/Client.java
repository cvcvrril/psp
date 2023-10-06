package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Client {

    /*Atributos*/

    private int id_c;
    private String firstName;
    private String secondName;
    private String email;
    private int phoneNumber;
    private LocalDate date;

    /*Construcctores*/

    public String toStringFile() {
        return id_c + ";" + firstName + ";" + secondName + ";" + email + ";" + phoneNumber + ";" + date;
    }
}
