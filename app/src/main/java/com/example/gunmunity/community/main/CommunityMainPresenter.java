package com.example.gunmunity.community.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.gunmunity.model.board.BoardCategory;
import com.example.gunmunity.model.board.BoardInfo;
import com.example.gunmunity.model.board.SearchBoardResponse;
import com.example.gunmunity.network.CommunityService;
import com.example.gunmunity.network.RetrofitUtil;
import com.example.gunmunity.util.SingleLiveEvent;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityMainPresenter {
    private CommunityMainFragment mFragment;
    private CommunityService communityService;
    BoardCategory boardCategory;
    String mCategory;
    int currentPage;
    String keyword;

    MutableLiveData<ArrayList<BoardInfo>> boardList = new MutableLiveData<>();
    SingleLiveEvent<Void> emptyDataCall = new SingleLiveEvent<>();

    public CommunityMainPresenter(CommunityMainFragment mFragment) {
        this.mFragment = mFragment;
        this.communityService = RetrofitUtil.getRetrofit().create(CommunityService.class);

        mCategory = boardCategory.FREE.toString();
        currentPage = 0;
        //keyword = "";
    }

    public void clickCategory(int viewType) {
        switch (viewType) {
            case 1 :
                //API 완성시 카테고리 API와 통신하는 로직 추가
                //통신해서 카테고리별 리스트 데이터를 받아오면 어댑터를 다시 정의해주는 로직 실행
                mCategory = boardCategory.FREE.toString();
                getBoardList();
                break;
            case 2 :
                mCategory = boardCategory.COUNSEL.toString();
                getBoardList();
                break;
            case 3 :
                mCategory = boardCategory.INFORMATION.toString();
                getBoardList();
                break;
        }
    }

    public void getBoardList() {
        Log.d("Mytag", mCategory);
        communityService.getBoardList(mCategory, currentPage)
                .enqueue(new Callback<SearchBoardResponse>() {
                    @Override
                    public void onResponse(Call<SearchBoardResponse> call, Response<SearchBoardResponse> response) {
                        if (response.body().getBoardsCount()==0) {
                            emptyDataCall.call();
                        } else {
                            boardList.setValue(response.body().getBoardInfo());
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchBoardResponse> call, Throwable t) {

                    }
                });
    }
}
