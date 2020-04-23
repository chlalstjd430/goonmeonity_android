package com.example.gunmunity.network;

import com.example.gunmunity.model.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/posts")
    Call<ResponseAPI> postJson();
}
