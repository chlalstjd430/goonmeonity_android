package com.example.gunmunity.community.main;

import android.util.Log;

public class CommunityMainPresenter implements CommnuityMainContract.Presenter {


    @Override
    public void clickCategory(int viewType) {
        switch (viewType) {
            case 1 :
                Log.d("MyTag", "category1 Click");
                //API 완성시 카테고리 API와 통신하는 로직 추가
                //통신해서 카테고리별 리스트 데이터를 받아오면 어댑터를 다시 정의해주는 로직 실행
                break;
            case 2 :
                break;
            case 3 :
                break;
        }
    }
}
