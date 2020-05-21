package com.example.gunmunity.community.main;

public interface CommunityMainContract {
    interface View {
        void goToDetailCommunity();
    }

    interface Presenter {
        void clickCategory(int viewType);
    }
}
