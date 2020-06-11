package com.example.gunmunity.community.create;

import com.example.gunmunity.model.ConstValue;
import com.example.gunmunity.model.board.BoardInfoResponse;
import com.example.gunmunity.model.board.CreateBoardRequest;
import com.example.gunmunity.network.CommunityService;
import com.example.gunmunity.network.RetrofitUtil;
import com.example.gunmunity.util.SharedPreferenceUtil;
import com.example.gunmunity.util.SingleLiveEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityCreatePresenter {
    private CommunityCreateActivity mActivity;
    private CommunityService communityService;
    private SharedPreferenceUtil mPref;
    CreateBoardRequest createBoardRequest;

    SingleLiveEvent<Void> successCreateCall = new SingleLiveEvent<>();

    CommunityCreatePresenter(CommunityCreateActivity mFragment) {
        this.mActivity = mFragment;
        communityService = RetrofitUtil.getRetrofit().create(CommunityService.class);
        createBoardRequest = new CreateBoardRequest("","","");
    }

    public void postArticle() {
        communityService.postArticle(mPref.getString(mActivity, ConstValue.ACCESS_TOKEN), createBoardRequest)
                .enqueue(new Callback<BoardInfoResponse>() {
                    @Override
                    public void onResponse(Call<BoardInfoResponse> call, Response<BoardInfoResponse> response) {
                        successCreateCall.call();
                    }

                    @Override
                    public void onFailure(Call<BoardInfoResponse> call, Throwable t) {

                    }
                });
    }
}
