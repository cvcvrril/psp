package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Customer {

    /*Atributos*/

    private int idC;
    private String firstName;
    private String secondName;
    private String email;
    private int phoneNumber;
    private LocalDate date;

    /*Construcctores*/

    public String toStringFile() {
        return idC + ";" + firstName + ";" + secondName + ";" + email + ";" + phoneNumber + ";" + date;
    }
}
