package com.example.gunmunity.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    Retrofit retrofit;
    Service service;

    private String baseUrl =
            "https://jsonplaceholder.typicode.com/";

    public Service getService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        return service;
    }
}
