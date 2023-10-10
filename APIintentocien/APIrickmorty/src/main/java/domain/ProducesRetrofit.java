package domain;

import common.Configuration;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.Retrofit;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi(){
        return new Moshi.Builder().build();
    }

    @Produces
    public OkHttpClient getOk(Configuration config){
        return new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                .build();
    }
    





}
