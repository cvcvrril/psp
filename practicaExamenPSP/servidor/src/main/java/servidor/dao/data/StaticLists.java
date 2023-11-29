package servidor.dao.data;

import domain.model.Album;
import domain.model.Artista;

import java.util.ArrayList;
import java.util.List;

public class StaticLists {

    public static final List<Artista> listaArtistas = new ArrayList<>(List.of(
            new Artista(1, "Extremoduro", "España"),
            new Artista(2, "Mägo de Oz", "España"),
            new Artista(3, "Rosendo", "España"),
            new Artista(4, "Soda Stereo", "Argentina"),
            new Artista(5, "MGMT", "Estados Unidos"),
            new Artista(6, "Dover", "España"),
            new Artista(7, "Bôa", "Reino Unido"),
            new Artista(8, "Gorillaz", "Reino Unido")
    ));

    public static final List<Album> listaAlbumes = new ArrayList<>(List.of(
            new Album(1, "", new ArrayList<>(), 1)
    ));

}
