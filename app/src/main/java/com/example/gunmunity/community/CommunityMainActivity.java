package com.example.gunmunity.community;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gunmunity.R;

public class CommunityMainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_main);
        initView();

    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, new CommunityMainFragment()).commit();

    }

    private void testAPI() {
        /*Service service = new RetrofitUtil().getService();
        service.postJson().enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                if (response.isSuccessful()) {
                    List<Result> results = response.body();
                    initRecyclerView(results);
                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {

            }
        });*/
    }
}
