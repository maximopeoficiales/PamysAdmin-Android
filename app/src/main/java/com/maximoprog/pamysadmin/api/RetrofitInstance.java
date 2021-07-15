package com.maximoprog.pamysadmin.api;

import com.maximoprog.pamysadmin.enviroments.Credentials;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//instancia generica para los servicios web
public class RetrofitInstance {
    private static RetrofitInstance instance;
    private final Retrofit api;

    private RetrofitInstance() {
        api = new Retrofit.Builder()
                .baseUrl(Credentials.URL_API)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    public <S> S createRepository(Class<S> IRepository) {
        return api.create(IRepository);
    }

    public <T> Response<T> execute(Call<T> call) {
        try {
            return call.execute();
        } catch (IOException err) {
            err.printStackTrace();
            return null;
        }
    }

}
