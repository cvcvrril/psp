package dao.data;

import dao.modelo.Credencial;
import dao.modelo.Personaje;
import dao.modelo.Videojuego;

import java.util.ArrayList;
import java.util.List;

public class StaticLists {

    public static List<Videojuego> listaVideojuegos = new ArrayList<>(List.of(
            new Videojuego(1, "Red Dead Redemption 2", "Un épico juego de acción y aventuras ambientado en el salvaje oeste. Red Dead Redemption 2 ofrece una inmersiva experiencia en un vasto mundo abierto. Con una historia profunda sobre forajidos y lealtades cuestionadas, el juego destaca por sus gráficos impresionantes, su jugabilidad envolvente y la exploración de temas como la moralidad y la redención."),
            new Videojuego(2, "Super Mario Wonder", "Una emocionante entrega en la icónica serie de Super Mario. Super Mario Wonder combina la jugabilidad clásica de plataformas con nuevos elementos y desafíos. Los jugadores se sumergen en mundos coloridos, enfrentan enemigos ingeniosos y disfrutan de la diversión y la creatividad que son sellos distintivos de la franquicia."),
            new Videojuego(3, "Metal Gear Solid 2", "Un juego de sigilo y acción que lleva a los jugadores a intrincadas tramas de conspiración. Metal Gear Solid 2 destaca por su narrativa compleja y su protagonista, Raiden, un ciborg ninja. El juego desafía las expectativas y ofrece momentos impactantes que han dejado una marca duradera en la cultura de los videojuegos."),
            new Videojuego(4, "Death Stranding", "Una experiencia única que combina exploración, acción y narrativa en un mundo postapocalíptico. Death Stranding, dirigido por Hideo Kojima, se sumerge en temas de conexión, soledad y supervivencia. Con un elenco estelar, una historia intrigante y mecánicas de juego innovadoras, el juego ofrece una experiencia inmersiva y reflexiva."),
            new Videojuego(5, "The Last Of Us",  "Un juego de acción y supervivencia que sigue la emotiva historia de Joel y Ellie en un mundo postapocalíptico. The Last Of Us destaca por su narrativa conmovedora, personajes bien desarrollados y una jugabilidad intensa. Explora temas de amor, pérdida y esperanza en un mundo devastado por una plaga mortal."),
            new Videojuego(6, "Valorant", "Un juego de disparos táctico en primera persona que combina estrategia y habilidad. Valorant destaca por sus personajes únicos, cada uno con habilidades especiales, y su enfoque en el trabajo en equipo. Con partidas competitivas y mecánicas de juego sólidas, Valorant se ha convertido en un título destacado en el género de los juegos de disparos."),
            new Videojuego(7, "The Walking Dead","Un juego narrativo basado en la exitosa serie de cómics y televisión. The Walking Dead sumerge a los jugadores en un mundo infestado de zombis, donde las decisiones éticas y emocionales tienen consecuencias significativas. La historia se centra en la supervivencia y las relaciones humanas en un apocalipsis zombi."),
            new Videojuego(8, "Profesor Layton y la Caja de Pandora", "Una encantadora aventura de rompecabezas que sigue al astuto Profesor Layton y su aprendiz Luke. La Caja de Pandora presenta una historia intrigante llena de misterios y enigmas por resolver. Los jugadores disfrutan de desafiantes rompecabezas mientras exploran un mundo pintoresco y siguen la trama cautivadora del juego.")
    ));

    public static List<Personaje> listaPersonajes = new ArrayList<>(List.of(
            new Personaje(1, "Clementine", " Una valiente joven que ha superado innumerables desafíos en un mundo postapocalíptico. Su astucia y valentía la convierten en una líder inesperada entre los supervivientes.", 7),
            new Personaje(2, "Mario", "El intrépido fontanero del Reino Champiñón, conocido por su valentía y determinación. Mario se embarca en aventuras épicas para rescatar a la Princesa Peach y salvar su reino de las garras de Bowser.", 2),
            new Personaje(3, "Luigi", "El hermano menor de Mario, a menudo subestimado pero igualmente valiente. Luigi se une a su hermano en sus aventuras, demostrando que tiene su propio conjunto único de habilidades.", 2),
            new Personaje(4, "Bowser", "El temible rey de los Koopas y archienemigo de Mario. Bowser busca constantemente secuestrar a la Princesa Peach y conquistar el Reino Champiñón, desencadenando conflictos épicos con el fontanero.", 2),
            new Personaje(5, "Sam Bridges", "Un mensajero intrépido en un mundo postapocalíptico, enfrentándose a amenazas sobrenaturales mientras entrega esperanza a través de terrenos peligrosos. Su conexión con el Otro Lado le otorga habilidades únicas.", 4),
            new Personaje(6, "Fragile", "Una misteriosa aliada de Sam Bridges con habilidades de teletransporte. Fragile navega por el paisaje devastado, desentrañando secretos y desempeñando un papel clave en el destino del mundo.", 4),
            new Personaje(7, "Phoenix", "Un hábil agente táctico en el mundo de Valorant. Phoenix domina el fuego y las llamas, desencadenando habilidades ardientes para desorientar a sus enemigos y liderar a su equipo hacia la victoria.", 6),
            new Personaje(8, "Reyna", "Una letal depredadora en Valorant, Reyna se alimenta de la energía de sus enemigos para potenciar sus habilidades. Su naturaleza voraz la convierte en una fuerza imparable en el campo de batalla.", 6),
            new Personaje(9, "Layton", "El distinguido Profesor Hershel Layton, conocido por su aguda inteligencia y habilidades deductivas. Layton se embarca en investigaciones intrigantes, resolviendo enigmas y descubriendo la verdad detrás de misterios aparentemente insolubles.", 8),
            new Personaje(10, "Luke", "El joven aprendiz de Profesor Layton, curioso y dispuesto a aprender. Luke acompaña al Profesor en sus aventuras, aportando su ingenio y perspicacia a la resolución de acertijos y misterios.", 8),
            new Personaje(11, "Solid Snake", "Un legendario soldado y espía, conocido por su astucia y habilidades tácticas. Solid Snake ha enfrentado amenazas globales y ha demostrado ser un maestro en el combate cuerpo a cuerpo y el sigilo. Su presencia imponente y su habilidad para superar situaciones peligrosas lo convierten en un ícono en el mundo de los videojuegos.", 3),
            new Personaje(12, "Raiden", "Inicialmente un novato en la serie Metal Gear, Raiden ha evolucionado para convertirse en un hábil ciborg ninja. Con su espada de alta frecuencia y habilidades mejoradas, Raiden es un guerrero formidable. Su historia personal y su transformación lo han convertido en un personaje fascinante dentro del universo de Metal Gear Solid.", 3),
            new Personaje(13, "Joel Miller", "Un superviviente curtido por la dureza del mundo postapocalíptico. Joel es conocido por su habilidad para tomar decisiones difíciles en su búsqueda de la supervivencia. Su pasado oscuro y sus relaciones complicadas le dan profundidad a su personaje, y su viaje en \"The Last of Us\" lo ha convertido en un ícono memorable en la narrativa de los videojuegos.", 5),
            new Personaje(14, "Ellie", "Una joven valiente y resiliente que ha crecido en un mundo devastado por la epidemia. Ellie es conocida por su inteligencia, habilidades de supervivencia y su conexión especial con Joel. Su viaje en \"The Last of Us\" revela capas emocionales profundas y muestra su capacidad para enfrentar los desafíos con determinación.", 5),
            new Personaje(15, "Arthur Morgan", "Un forajido y miembro destacado de la banda Van der Linde en el salvaje oeste. Arthur Morgan es conocido por su lealtad a la banda y su habilidad para desenvolverse en un mundo en constante cambio. Su historia en Red Dead Redemption 2 explora temas de redención y moralidad, convirtiéndolo en un personaje complejo y memorable.", 1),
            new Personaje(16, "Abigail Roberts", "La única mujer en la banda Van der Linde, Abigail Roberts es conocida por su fuerza y determinación en un mundo dominado por hombres. Como madre y compañera de Arthur Morgan, su papel es fundamental en la historia de Red Dead Redemption 2. Su lucha por la estabilidad y la seguridad de su familia la hace destacar en el paisaje narrativo del juego.", 1)
    ));

    public static List<Credencial> listaCredenciales = new ArrayList<>(List.of(
            new Credencial("root", "PBKDF2WithHmacSHA256:2048:IkYZsBXLSqjTryINcSjkN9+PXLEchrZTuZOAQ/O5Huc=:5HOA5qBfUwIH3ixPKBL4teybX9AtB6k5sqeGlqdFFM0=", "ines07mar@gmail.com", true, "Admin", "=Lm9QmRmUy-RWQjLRQusHuhyxhdBy2xCEHbpJJE4bmSU=" ),
            new Credencial("patata", "PBKDF2WithHmacSHA256:2048:/QSvjU3uE30KKMJV94/EC7FhqVF4x3H1ekWw5A+6XDQ=:87UgaTeuKinKRcytYD1dWyvZoS4FBtz/qUNnoxDeIZQ=", "inesysoniadam@gmail.com", true, "User", "=IzfSbZY2Zxp_tx1jttRwhrorQKc14LtcHNAUD1ARe4E=" )
    ));


    /*Contraseña usuario root -> Admin*/
    /*Contraseña usuario patata -> Patata*/


}
