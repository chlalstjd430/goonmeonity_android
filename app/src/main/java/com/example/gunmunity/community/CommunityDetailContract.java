package com.example.gunmunity.community;

import com.example.gunmunity.model.CommunityList;

public interface CommunityDetailContract {
    interface View {
        void bindArticleData(CommunityList communityList);
    }

    interface Presenter {
        void getArticleData();
    }
}
