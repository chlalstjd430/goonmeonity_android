package com.example.gunmunity.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static String baseUrl = "http://ec2-13-125-6-45.ap-northeast-2.compute.amazonaws.com/";

    public static Retrofit getRetrofit() {
        Retrofit retrofit;
        return retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient;
        return okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .retryOnConnectionFailure(true)
                .build();
    }
}
