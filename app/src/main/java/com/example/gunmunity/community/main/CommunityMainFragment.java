package com.example.gunmunity.community.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;
import com.example.gunmunity.community.CommunityDetailActivity;
import com.example.gunmunity.model.CommunityList;

import java.util.ArrayList;
import java.util.List;

public class CommunityMainFragment extends Fragment implements CommunityMainContract.View {

    private RecyclerView mRecyclerView;
    private CommunityMainPresenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community_main, container, false);
        mRecyclerView = rootView.findViewById(R.id.community_recyclerview);
        presenter = new CommunityMainPresenter();

        List<CommunityList> list = new ArrayList<>();
        list.add(new CommunityList());
        list.add(new CommunityList());
        list.add(new CommunityList());

        initRecyclerView(list);
        setBinding(rootView);

        return rootView;
    }

    private void setBinding(View rootView) {
        View category1 = rootView.findViewById(R.id.main_category1);
        View category2 = rootView.findViewById(R.id.main_category2);
        View category3 = rootView.findViewById(R.id.main_category3);

        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickCategory(1);
            }
        });

        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickCategory(2);
            }
        });

        category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickCategory(3);
            }
        });
    }

    private void initRecyclerView(List<CommunityList> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        CommunityMainAdapter adapter = new CommunityMainAdapter(getActivity(), this);
        adapter.setData(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void goToDetailCommunity() {
        Intent intent = new Intent(getActivity(), CommunityDetailActivity.class);
        startActivity(intent);
    }

}
