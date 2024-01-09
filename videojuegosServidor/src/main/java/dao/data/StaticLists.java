package dao.data;

import dao.modelo.Credencial;
import dao.modelo.Personaje;
import dao.modelo.Videojuego;

import java.util.ArrayList;
import java.util.List;

public class StaticLists {

    public static List<Videojuego> listaVideojuegos = new ArrayList<>(List.of(
            new Videojuego(1, "Pokemon Negro", ""),
            new Videojuego(2, "Super Mario Wonder", ""),
            new Videojuego(3, "Metal Gear Solid 2", ""),
            new Videojuego(4, "Death Stranding", ""),
            new Videojuego(5, "The Last Of Us",  ""),
            new Videojuego(6, "Valorant", ""),
            new Videojuego(7, "The Walking Dead",""),
            new Videojuego(8, "Profesor Layton y la Caja de Pandora", "")
    ));

    public static List<Personaje> listaPersonajes = new ArrayList<>(List.of(
            new Personaje(1, "Clementine", "", 7),
            new Personaje(2, "Mario", "", 2),
            new Personaje(3, "Luigi", "", 2),
            new Personaje(4, "Bowser", "", 2),
            new Personaje(5, "Sam Bridges", "", 4),
            new Personaje(6, "Fragile", "", 4),
            new Personaje(7, "Phoenix", "", 6),
            new Personaje(8, "Reyna", "", 6),
            new Personaje(9, "Layton", "", 8),
            new Personaje(10, "Luke", "", 8)
    ));

    public static List<Credencial> listaCredenciales = new ArrayList<>(List.of(
            new Credencial("root", "PBKDF2WithHmacSHA256:2048:IkYZsBXLSqjTryINcSjkN9+PXLEchrZTuZOAQ/O5Huc=:5HOA5qBfUwIH3ixPKBL4teybX9AtB6k5sqeGlqdFFM0=", "ines07mar@gmail.com", true, "Admin", "=Lm9QmRmUy-RWQjLRQusHuhyxhdBy2xCEHbpJJE4bmSU=" ),
            new Credencial("patata", "PBKDF2WithHmacSHA256:2048:/QSvjU3uE30KKMJV94/EC7FhqVF4x3H1ekWw5A+6XDQ=:87UgaTeuKinKRcytYD1dWyvZoS4FBtz/qUNnoxDeIZQ=", "ines07mar@gmail.com", true, "User", "=IzfSbZY2Zxp_tx1jttRwhrorQKc14LtcHNAUD1ARe4E=" )
    ));


    /*Contraseña usuario root -> Admin*/
    /*Contraseña usuario patata -> Patata*/


}
