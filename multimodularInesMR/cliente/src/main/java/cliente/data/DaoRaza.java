package cliente.data;

import cliente.data.retrofit.RazaApi;
import cliente.domain.errores.ErrorC;
import com.google.gson.Gson;
import domain.modelo.Raza;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class DaoRaza extends DaoGenerics{

    private final RazaApi razaApi;

    @Inject
    public DaoRaza(RazaApi razaApi, Gson gson) {
        super(gson);
        this.razaApi = razaApi;
    }

    public Single<Either<ErrorC, List<Raza>>> getAllRazas(){
        return safeSingleApicall(razaApi.getRazas());
    }

}
