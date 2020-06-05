package com.example.gunmunity.community.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;
import com.example.gunmunity.community.CommunityDetailActivity;
import com.example.gunmunity.community.create.CommunityCreateActivity;
import com.example.gunmunity.model.board.BoardInfo;

import java.util.List;

public class CommunityMainFragment extends Fragment implements CommunityMainContract.View {

    private RecyclerView mRecyclerView;
    private CommunityMainPresenter presenter;
    private CommunityMainAdapter adapter;
    private ImageView buttonCreate;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community_main, container, false);
        mRecyclerView = rootView.findViewById(R.id.community_recyclerview);
        buttonCreate = rootView.findViewById(R.id.main_create);
        presenter = new CommunityMainPresenter(this);

        initRecyclerView();
        setBinding(rootView);
        setObserveLiveData();
        presenter.getBoardList();

        return rootView;
    }

    private void setObserveLiveData() {
        presenter.boardList.observe(this, new Observer<List<BoardInfo>>() {
            @Override
            public void onChanged(List<BoardInfo> boardInfos) {
                adapter.setData(presenter.boardList.getValue());
            }
        });

        presenter.emptyDataCall.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                Toast mToast = Toast.makeText(getActivity(), "데이터가 없습니다.", Toast.LENGTH_LONG);
                mToast.show();
            }
        });
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

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCreateActivity();
            }
        });
    }

    private void startCreateActivity() {
        Intent intent = new Intent(getActivity(), CommunityCreateActivity.class);
        startActivity(intent);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter = new CommunityMainAdapter(getActivity(), this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void startDetailActivity() {
        Intent intent = new Intent(getActivity(), CommunityDetailActivity.class);
        startActivity(intent);
    }

}
