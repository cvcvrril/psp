package cliente.data;

import cliente.domain.errores.ErrorC;
import com.google.gson.Gson;
import domain.errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;

import java.util.Objects;

abstract class DaoGenerics {


    private Gson gson;

    @Inject
    protected DaoGenerics(Gson gson) {
        this.gson = gson;
    }

    public <T> Either<ErrorC, T> safeApicall(Call<T> call) {
        Either<ErrorC, T> resultado = null;

        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                resultado = Either.right(response.body());
            } else {

                resultado = Either.left(new ErrorC(response.errorBody().toString()));
            }
        } catch (Exception e) {
            resultado = Either.left(new ErrorC(ConstantesData.ERROR_DE_COMUNICACION));

        }

        return resultado;
    }


    //TODO: USAR EN TODAS MENOS EN DELETE

    public <T> Single<Either<ErrorC, T>> safeSingleApicall(Single<T> call) {
        return call.map(t -> Either.right(t).mapLeft(o -> (ErrorC) o))

                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<ErrorC, T> error = Either.left(new ErrorC(throwable.getMessage())
                    );

                    if (throwable instanceof HttpException) {
                        HttpException httpException = (HttpException) throwable;
                        int code = httpException.code();
                        Response<?> response = httpException.response();

                        if (response != null && response.errorBody() != null) {
                            if (Objects.equals(response.errorBody().contentType(), MediaType.get(ConstantesData.CONTENT_TYPE))) {
                                ApiError api = gson.fromJson(response.errorBody().charStream(), ApiError.class);
                                error = Either.left(new ErrorC(api.getMensaje()));
                            } else {
                                error = Either.left(new ErrorC(response.message())
                                );
                            }
                        }
                    }
                    return error;
                });

    }

    //TODO: USAR ESTE SOLO PARA EL DELETE Y LOGIN

    public Single<Either<ErrorC, String>> safeSingleVoidApicall(Single<Response<Void>> call) {
        return call.map(response -> {
                    var retorno = Either.right(ConstantesData.OK).mapLeft(o -> (ErrorC) o);
                    if (!response.isSuccessful()) {
                        retorno = Either.left(new ErrorC(response.message()));
                    }
                    return retorno;
                })
                .subscribeOn(Schedulers.io());
    }

}
