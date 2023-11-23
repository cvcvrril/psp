package cliente.data;

import cliente.data.retrofit.PersonajeApi;
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

    public Single<Either<Object, List<Personaje>>> getAllPersonajes(){
        return safeSingleApicall(personajeApi.getPersonajes());
    }
}
