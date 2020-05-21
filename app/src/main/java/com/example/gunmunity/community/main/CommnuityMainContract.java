package com.example.gunmunity.community.main;

public interface CommnuityMainContract {
    interface View {
        void goToDetailCommunity();
    }

    interface Presenter {
        void clickCategory(int viewType);
    }
}
