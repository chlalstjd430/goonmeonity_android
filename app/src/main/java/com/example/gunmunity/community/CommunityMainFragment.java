package com.example.gunmunity.community;

import android.content.Context;
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
import com.example.gunmunity.model.CommunityListModel;

import java.util.ArrayList;
import java.util.List;

public class CommunityMainFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community_main, container, false);
        mRecyclerView = rootView.findViewById(R.id.community_recyclerview);

        List<CommunityListModel> list = new ArrayList<>();
        list.add(new CommunityListModel());
        list.add(new CommunityListModel());
        list.add(new CommunityListModel());

        initRecyclerView(list);

        return rootView;
    }

    private void initRecyclerView(List<CommunityListModel> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        CommunityMainAdapter adapter = new CommunityMainAdapter(getActivity());
        adapter.setData(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }


}
