package com.example.graphql.utils;

public class Constantes {

    private static final String[] WHITE_LIST_URL = {
            "/auth/login",
            "/auth/registro",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs/**"
    };
    public static final String KEY_STORE_PASSWORD = "${KeyStorePassword}";
    public static final String TABLE_MAPAS = "mapas";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String TABLE_PERSONAJES = "personajes";
    public static final String DESCRIPCION = "descripcion";
    public static final String ID_VIDEOJUEGO = "id_videojuego";
    public static final String TABLE_VIDEOJUEGOS = "videojuegos";
    public static final String TITULO = "titulo";
    public static final String VIDEOJUEGO = "videojuego";
    public static final String QUERY_FIND_PERSONAJES = """ 
            select p from PersonajeEntity p 
            JOIN FETCH p.videojuego
            """;
    public static final String QUERY_FIND_VIDEOJUEGOS = """ 
            select v from VideojuegoEntity v 
            JOIN FETCH v.personajes
            """;
    public static final String COMPONENT_MODEL_SPRING = "spring";
    public static final String ERROR_PERSONAJE_NO_ENCONTRADO = "Personaje no encontrado";
    public static final String DESCRIPCION_POR_DEFECTO = "Videojuego añadido por el usuario";
    public static final String ERROR_VIDEOJUEGO_NO_ENCONTRADO = "No se encontró el videojuego.";

    public Constantes() {}
}
