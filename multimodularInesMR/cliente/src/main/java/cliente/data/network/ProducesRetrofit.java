package cliente.data.network;

import cliente.data.ConstantesData;
import cliente.data.retrofit.*;
import jakarta.inject.Singleton;
import com.google.gson.*;
import jakarta.enterprise.inject.Produces;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext) -> new JsonPrimitive(localDate.toString())
                ).create();
    }

    @Singleton
    @Produces
    public OkHttpClient getOk() {
        return new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                .build();
    }

    @Produces
    @Singleton
    public Retrofit retrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ConstantesData.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Produces
    public PersonajeApi getPersonajeApi (Retrofit retrofit){
        return retrofit.create(PersonajeApi.class);
    }
    @Produces
    public RazaApi getRazaApi (Retrofit retrofit){
        return retrofit.create(RazaApi.class);
    }
    @Produces
    public FaccionApi getFaccionApi (Retrofit retrofit){
        return retrofit.create(FaccionApi.class);
    }
    @Produces
    public LoginApi getLoginApi (Retrofit retrofit){
        return retrofit.create(LoginApi.class);
    }
    @Produces
    public RegistroApi getRegistroApi (Retrofit retrofit){
        return retrofit.create(RegistroApi.class);
    }
}
