package com.example.gunmunity;

import com.example.gunmunity.model.Result;
import com.example.gunmunity.network.RetrofitUtil;
import com.example.gunmunity.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter{

    MainContract.View view;
    MainAdapter adapter;
    Service service;

    public MainPresenter(MainContract.View view){
        this.view = view;
    }

    @Override
    public void callLoadList() {
        service = new RetrofitUtil().getService();
        service.postJson().enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                if (response.isSuccessful()) {
                    List<Result> results = response.body();
                    view.initList();
                    adapter.setData(results);
                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {

            }
        });
    }

    @Override
    public void setAdapter(MainAdapter adapter){
        this.adapter = adapter;
    }
}
