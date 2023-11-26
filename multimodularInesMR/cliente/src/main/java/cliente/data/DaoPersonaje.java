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

    public Single<Either<ErrorC, Personaje>> getPersonajeById(int id){
        return safeSingleApicall(personajeApi.getPersonajeById(id));
    }

    public Single<Either<ErrorC, Personaje>> addPersonaje(Personaje personaje) {
        return safeSingleApicall(personajeApi.addPersonaje(personaje));
    }

    public Single<Either<ErrorC, Personaje>> updatePersonaje(Personaje personaje) {
        return safeSingleApicall(personajeApi.updatePersonaje(personaje));
    }

    public Single<Either<ErrorC, String>> deletePersonaje(int id) {
        return safeSingleVoidApicall(personajeApi.deletePersonaje(id));
    }

    public Single<Either<ErrorC, String>> deletePersonajePorFaccion(int idFaccion){
        return safeSingleVoidApicall(personajeApi.deletePersonajePorFaccion(idFaccion));
    }

}
