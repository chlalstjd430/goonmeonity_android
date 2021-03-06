package com.example.gunmunity.community.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;
import com.example.gunmunity.community.detail.CommunityDetailActivity;
import com.example.gunmunity.community.create.CommunityCreateActivity;
import com.example.gunmunity.model.board.BoardInfo;

import java.util.List;

public class CommunityMainFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CommunityMainPresenter presenter;
    private CommunityMainAdapter adapter;
    private ImageView buttonCreate;
    private Button category1;
    private Button category2;
    private Button category3;
    private Button category4;

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
        category1 = rootView.findViewById(R.id.main_category1);
        category2 = rootView.findViewById(R.id.main_category2);
        category3 = rootView.findViewById(R.id.main_category3);
        category4 = rootView.findViewById(R.id.main_category4);

        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickCategory(1);
                category1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                category1.setTextColor(getResources().getColor(R.color.colorWhite));
                category2.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category2.setTextColor(getResources().getColor(R.color.colorBlack));
                category3.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category3.setTextColor(getResources().getColor(R.color.colorBlack));
                category4.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category4.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickCategory(2);
                category2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                category2.setTextColor(getResources().getColor(R.color.colorWhite));
                category1.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category1.setTextColor(getResources().getColor(R.color.colorBlack));
                category3.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category3.setTextColor(getResources().getColor(R.color.colorBlack));
                category4.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category4.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickCategory(3);
                category3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                category3.setTextColor(getResources().getColor(R.color.colorWhite));
                category2.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category2.setTextColor(getResources().getColor(R.color.colorBlack));
                category1.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category1.setTextColor(getResources().getColor(R.color.colorBlack));
                category4.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category4.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickCategory(4);
                category4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                category4.setTextColor(getResources().getColor(R.color.colorWhite));
                category2.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category2.setTextColor(getResources().getColor(R.color.colorBlack));
                category1.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category1.setTextColor(getResources().getColor(R.color.colorBlack));
                category3.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                category3.setTextColor(getResources().getColor(R.color.colorBlack));
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
        adapter = new CommunityMainAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void startDetailActivity(BoardInfo boardInfo) {
        Intent intent = new Intent(getActivity(), CommunityDetailActivity.class);
        intent.putExtra("boardInfo", boardInfo);
        startActivity(intent);
    }

}
