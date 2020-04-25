package com.example.gunmunity.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    Retrofit retrofit;
    Service service;

    public Service getService(){
        String baseUrl =
                "https://jsonplaceholder.typicode.com";

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        service = retrofit.create(Service.class);

        return service;
    }
}
