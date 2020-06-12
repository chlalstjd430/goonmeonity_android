package com.example.gunmunity.community.detail;

import com.example.gunmunity.model.ConstValue;
import com.example.gunmunity.model.comment.CreateCommentResponse;
import com.example.gunmunity.model.comment.GetCommentsResponse;
import com.example.gunmunity.network.CommentService;
import com.example.gunmunity.network.RetrofitUtil;
import com.example.gunmunity.util.SharedPreferenceUtil;
import com.example.gunmunity.util.SingleLiveEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityDetailPresenter {
    private CommunityDetailActivity detailActivity;
    private CommentService commentService;
    private CommunityCommentAdapter commentAdapter;
    private SharedPreferenceUtil mPref;
    SingleLiveEvent<Void> finishCommentRegister = new SingleLiveEvent<>();

    public CommunityDetailPresenter(CommunityDetailActivity detailActivity, CommunityCommentAdapter commentAdapter) {
        this.detailActivity = detailActivity;
        this.commentAdapter = commentAdapter;
        this.commentService = RetrofitUtil.getRetrofit().create(CommentService.class);
    }

    public void getCommentList(long id) {
        commentService.requestCommentList(id)
                .enqueue(new Callback<GetCommentsResponse>() {
                    @Override
                    public void onResponse(Call<GetCommentsResponse> call, Response<GetCommentsResponse> response) {
                        commentAdapter.setCommentInfos(response.body().getCommentsInfo());
                    }

                    @Override
                    public void onFailure(Call<GetCommentsResponse> call, Throwable t) {

                    }
                });
    }

    public void postComment(long id, String content) {
        commentService.requestPostComment(
                mPref.getString(detailActivity, ConstValue.ACCESS_TOKEN),
                id, content)
                .enqueue(new Callback<CreateCommentResponse>() {
                    @Override
                    public void onResponse(Call<CreateCommentResponse> call, Response<CreateCommentResponse> response) {
                        finishCommentRegister.call();
                        detailActivity.onResume();
                    }

                    @Override
                    public void onFailure(Call<CreateCommentResponse> call, Throwable t) {

                    }
                });
    }
}
