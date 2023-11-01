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
    private String emailCus;
    private int phoneNumber;
    private LocalDate dateBirth;

    /*Construcctores*/
}
