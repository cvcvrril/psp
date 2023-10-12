package dao.impl;

import common.Constantes;
import dao.retrofit.llamadas.PersonajeAPI;
import domain.modelo.MiPersonaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DAOpersonaje {

    /*Atributos*/
    private final PersonajeAPI personajeAPI;


    /*Constructor*/
    @Inject
    public DAOpersonaje(PersonajeAPI personajeAPI) {
        this.personajeAPI = personajeAPI;
    }

    /*Métodos*/

    public Either<String, List<MiPersonaje>> llamadaRetrofit() {
        Either<String, List<MiPersonaje>> res;
        Response<Map<String, Object>> r;
        try {
            r = personajeAPI.getPersonajes().execute();
            if (r.isSuccessful()) {
                Map<String, Object> responseMap = r.body();
                List<Map<String, Object>> responsePersonajes = (List<Map<String, Object>>) responseMap.get(Constantes.RESULTS);
                List<MiPersonaje> miPersonajes = responsePersonajes.stream()
                        .map(result ->
                                new MiPersonaje(
                                        ((Double) result.get(Constantes.ID)).intValue(),
                                        (String) result.get(Constantes.NAME),
                                        (String) result.get(Constantes.STATUS)))
                        .toList();
                res = Either.right(miPersonajes);
            } else {
                r.errorBody().string();
                res = Either.left(r.message());
            }

        } catch (IOException e) {
            res = Either.left(e.getMessage());
        }
        return res;
    }

    public Either<String, List<MiPersonaje>> llamadaRetrofitFem() {
        Either<String, List<MiPersonaje>> res;
        Response<Map<String, Object>> r;
        try {
            r = personajeAPI.getPersonajesGender(Constantes.FEMALE).execute();
            if (r.isSuccessful()) {
                Map<String, Object> responseMap = r.body();
                List<Map<String, Object>> responsePersonajes = (List<Map<String, Object>>) responseMap.get(Constantes.RESULTS);
                List<MiPersonaje> miPersonajes = responsePersonajes.stream()
                        .map(result ->
                                new MiPersonaje(
                                        ((Double) result.get(Constantes.ID)).intValue(),
                                        (String) result.get(Constantes.NAME),
                                        (String) result.get(Constantes.STATUS)))
                        .toList();
                res = Either.right(miPersonajes);
            } else {
                r.errorBody().string();
                res = Either.left(r.message());
            }

        } catch (IOException e) {
            res = Either.left(e.getMessage());
        }
        return res;
    }

    public Either<String, List<MiPersonaje>> llamadaRetrofitDead() {
        Either<String, List<MiPersonaje>> res;
        Response<Map<String, Object>> r;
        try {
            r = personajeAPI.getPersonajesStatus(Constantes.DEAD).execute();
            if (r.isSuccessful()) {
                Map<String, Object> responseMap = r.body();
                List<Map<String, Object>> responsePersonajes = (List<Map<String, Object>>) responseMap.get(Constantes.RESULTS);
                List<MiPersonaje> miPersonajes = responsePersonajes.stream()
                        .map(result ->
                                new MiPersonaje(
                                        ((Double) result.get(Constantes.ID)).intValue(),
                                        (String) result.get(Constantes.NAME),
                                        (String) result.get(Constantes.STATUS)))
                        .toList();
                res = Either.right(miPersonajes);
            } else {
                r.errorBody().string();
                res = Either.left(r.message());
            }

        } catch (IOException e) {
            res = Either.left(e.getMessage());
        }
        return res;
    }

    public Either<String, MiPersonaje> llamadaRetrofitJD() {
        Either<String, MiPersonaje> res;
        Response<Map<String, Object>> r;
        try {
            r = personajeAPI.getPersonajesId(183).execute();
            if (r.isSuccessful()) {
                Map<String, Object> responseMap = r.body();
                MiPersonaje miPersonaje = new MiPersonaje(
                        ((Double) responseMap.get(Constantes.ID)).intValue(),
                        (String) responseMap.get(Constantes.NAME),
                        (String) responseMap.get(Constantes.STATUS)
                );
                res = Either.right(miPersonaje);
            } else {
                r.errorBody().string();
                res = Either.left(r.message());
            }

        } catch (IOException e) {
            res = Either.left(e.getMessage());
        }
        return res;
    }

}
