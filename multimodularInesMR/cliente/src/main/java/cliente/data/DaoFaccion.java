package cliente.data;

import cliente.data.retrofit.FaccionApi;
import cliente.domain.errores.ErrorC;
import com.google.gson.Gson;
import domain.modelo.Faccion;
import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class DaoFaccion extends DaoGenerics{

    private final FaccionApi faccionApi;

    @Inject
    public DaoFaccion(FaccionApi faccionApi, Gson gson) {
        super(gson);
        this.faccionApi = faccionApi;
    }

    public Single<Either<ErrorC, List<Faccion>>> getAllFacciones(){
        return safeSingleApicall(faccionApi.getFacciones());
    }

}
