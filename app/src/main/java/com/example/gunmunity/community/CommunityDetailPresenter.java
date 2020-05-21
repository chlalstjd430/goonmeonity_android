package com.example.gunmunity.community;

import com.example.gunmunity.model.CommunityList;

public class CommunityDetailPresenter implements CommunityDetailContract.Presenter {
    CommunityDetailActivity detailActivity;

    public CommunityDetailPresenter(CommunityDetailActivity detailActivity) {
        this.detailActivity = detailActivity;
    }
    @Override
    public void getArticleData() {
        //request Article Detail API
        CommunityList communityList = new CommunityList();

        detailActivity.bindArticleData(communityList);
    }
}
