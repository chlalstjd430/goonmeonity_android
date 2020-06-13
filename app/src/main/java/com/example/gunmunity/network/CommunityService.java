package com.example.gunmunity.network;

import com.example.gunmunity.model.board.BoardInfoResponse;
import com.example.gunmunity.model.board.CreateBoardRequest;
import com.example.gunmunity.model.board.SearchBoardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommunityService {
    @GET("/v1/boards")
    Call<SearchBoardResponse> getBoardList(@Query("boardCategory") String boardCategory,
                                           @Query("currentPage") int currentPage);

    @POST("/v1/boards/board")
    Call<BoardInfoResponse> postArticle(@Header("accessToken")String accessToken,
                                          @Body CreateBoardRequest createBoardRequest);
}
