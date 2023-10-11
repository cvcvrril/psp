package dao.impl;

import dao.retrofit.llamadas.LugarAPI;
import domain.modelo.MiLugar;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DAOlugar {

    /*Atributos*/

    private final LugarAPI lugarAPI;

    /*Constructor*/

    @Inject
    public DAOlugar(LugarAPI lugarAPI) {
        this.lugarAPI = lugarAPI;
    }

    /*Métodos*/

    public Either<String, List<MiLugar>> llamadaRetrofit(){
        Either<String, List<MiLugar>> res = null;
        Response<Map<String, Object>> r;
        try {
            r = lugarAPI.getLugares().execute();
            if (r.isSuccessful()){
                Map<String,Object> responseMap = r.body();
                List<Map<String,Object>> responseLugares = (List<Map<String, Object>>) responseMap.get("results");
                List<MiLugar> miLugares = responseLugares.stream()
                        .map(result -> new MiLugar(((Double)result.get("id")).intValue(), (String) result.get("name"), (String) result.get("type"), (String) result.get("dimension")))
                        .toList();
                res = Either.right(miLugares);
            }
        } catch (IOException e) {
            res = Either.left(e.getMessage());
        }
        return res;
    }

}
