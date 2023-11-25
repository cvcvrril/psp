package cliente.data;

import cliente.data.retrofit.PersonajeApi;
import cliente.domain.errores.ErrorC;
import com.google.gson.Gson;
import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class DaoPersonaje extends DaoGenerics{

    private final PersonajeApi personajeApi;

    @Inject
    public DaoPersonaje(PersonajeApi personajeApi, Gson gson) {
        super(gson);
        this.personajeApi = personajeApi;
    }

    public Single<Either<ErrorC, List<Personaje>>> getAllPersonajes(){
        return safeSingleApicall(personajeApi.getPersonajes());
    }

    public Single<Either<ErrorC, Personaje>> createPersonaje(Personaje personaje) {
        return safeSingleApicall(personajeApi.createPersonaje(personaje));
    }

    public Single<Either<ErrorC, Personaje>> updatePersonaje(int id, Personaje personaje) {
        return safeSingleApicall(personajeApi.updatePersonaje(id, personaje));
    }

    public Single<Either<ErrorC, String>> deletePersonaje(int id) {
        return safeSingleVoidApicall(personajeApi.deletePersonaje(id));
    }

}
