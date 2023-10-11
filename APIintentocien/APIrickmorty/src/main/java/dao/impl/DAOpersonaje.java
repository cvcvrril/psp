package dao.impl;

import dao.retrofit.llamadas.PersonajeAPI;
import dao.retrofit.modelo.ResponsePersonaje;
import domain.modelo.MiPersonaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        Response<List<ResponsePersonaje>> r;
        try {
            r = personajeAPI.getPersonajes().execute();
            if (r.isSuccessful()) {
                List<ResponsePersonaje> responsePersonajes = r.body();
                assert responsePersonajes != null;
                List<MiPersonaje> miPersonajes = responsePersonajes.stream()
                        .map(rp -> new MiPersonaje(rp.getId(), rp.getName()))
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
}
