package com.example.gunmunity.network;

import com.example.gunmunity.model.comment.CreateCommentResponse;
import com.example.gunmunity.model.comment.GetCommentsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentService {
    @GET("/v1/boards/{boardId}/comments")
    Call<GetCommentsResponse> requestCommentList(@Path("boardId") long boardId);

    @POST("/v1/boards/{boardId}/comments/comment")
    Call<CreateCommentResponse> requestPostComment(@Header("accessToken") String accessToken,
                                                   @Path("boardId") long boardId,
                                                   @Body String content);
}
