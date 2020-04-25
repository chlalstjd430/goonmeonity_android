package com.example.gunmunity.network;

import com.example.gunmunity.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("/posts")
    Call<List<Result>> postJson();

    @GET("/posts/{userId}")
    Call<List<Result>> getJsonItem(@Path("userId") String userId);
}
