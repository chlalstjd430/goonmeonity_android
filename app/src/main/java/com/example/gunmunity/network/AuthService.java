package com.example.gunmunity.network;

import com.example.gunmunity.model.auth.SigninRequest;
import com.example.gunmunity.model.auth.SigninResponse;
import com.example.gunmunity.model.auth.SignupRequest;
import com.example.gunmunity.model.auth.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/v1/auth/sign-in")
    Call<SigninResponse> isRegisteredAccount(@Body SigninRequest signinRequest);

    @POST("/v1/auth/sign-up")
    Call<SignupResponse> registerAccount(@Body SignupRequest signupRequest);
}
