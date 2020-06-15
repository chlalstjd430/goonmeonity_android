package com.example.gunmunity.network;

import com.example.gunmunity.model.saving.SavingListResponse;
import com.example.gunmunity.salary.saving.SavinglistPresenter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SavingService {
    @GET("/v1/users/{userId}/discharge")
    Call<SavingListResponse> getsavinglist(@Header("accessToken")String accessToken,
                                           @Path("userId") int userId);
    @POST("/v1/users/{userId}/discharge")
    Call<SavingListResponse> addsavinglist(@Header("accessToken")String accessToken,
                                            @Query("amount") int amount,
                                           @Query("dueDate") String dueDate,
                                           @Query("interestRate") double interest,
                                           @Query("name") String name,
                                           @Query("payment") int payment,
                                           @Query("paymentDay") int paymentDay,
                                           @Query("startDate") String startDate,
                                            @Path("userId") int userId);

}
