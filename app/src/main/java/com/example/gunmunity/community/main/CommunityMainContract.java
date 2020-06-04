package com.example.gunmunity.community.main;

public interface CommunityMainContract {
    interface View {
        void startDetailActivity();
    }

    interface Presenter {
        void clickCategory(int viewType);
        void getBoardList();
    }
}
