package cliente.data.retrofit;

import cliente.data.ConstantesData;
import domain.modelo.Raza;
import retrofit2.http.GET;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface RazaApi {

    @GET(ConstantesData.RAZA)
    Single<List<Raza>> getRazas();

}
