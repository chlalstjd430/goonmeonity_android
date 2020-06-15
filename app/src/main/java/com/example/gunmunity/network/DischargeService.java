package com.example.gunmunity.network;

import com.example.gunmunity.model.discharge.DischargeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DischargeService {
    @GET("/v1/users/{userId}/discharge")
    Call<DischargeResponse> getdischarge(@Header("accessToken")String accessToken,
                                           @Path("userId") int userId);

    @POST("/v1/users/{userId}/discharge")
    Call<DischargeResponse> adddischarge(@Header("accessToken")String accessToken,
                                         @Path("userId")int userId,
                                          @Query("dischargeDate") String dischargeDate,
                                          @Query("enlistmentDate") String enlistmentDate,
                                          @Query("militaryAffiliate") String militaryAffiliate,
                                          @Query("militaryStatus") String militaryStatus);
}
