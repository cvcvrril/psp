package domain;

import common.Configuration;
import common.Constantes;
import dao.retrofit.llamadas.EpisodioAPI;
import dao.retrofit.llamadas.LugarAPI;
import dao.retrofit.llamadas.PersonajeAPI;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.Retrofit;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi(){
        return new Moshi.Builder().build();
    }

    @Produces
    public OkHttpClient getOk(){
        return new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                .build();
    }

    @Produces
    @Singleton
    public Retrofit retrofit(Configuration config, Moshi moshi, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(config.getProperty(Constantes.API_URL))
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /*Personajes*/
    @Produces
    public PersonajeAPI getPersonajeAPI (Retrofit retrofit){
        return retrofit.create(PersonajeAPI.class);
    }

    /*Episodios*/
    @Produces
    public EpisodioAPI getEpisodioAPI (Retrofit retrofit){
        return retrofit.create(EpisodioAPI.class);
    }

    /*Lugares*/
    @Produces
    public LugarAPI getLugarAPI (Retrofit retrofit){
        return retrofit.create(LugarAPI.class);
    }

}
