package com.example.gunmunity;

public interface MainContract {
    interface View {
        void initList();
    }

    interface Presenter {
        void callLoadList();
        void setAdapter(MainAdapter adapter);
    }
}
