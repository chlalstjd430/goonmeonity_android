package com.example.gunmunity.salary.saving;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;

public class SavingCaculatorFragment extends Fragment implements SavingContract.View {

    View view;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.saving_calculator, container, false);
        ProgressBar pgb = view.findViewById(R.id.progressBar2);
        LayerDrawable bgshape = (LayerDrawable) pgb.getProgressDrawable();
        ClipDrawable gd = (ClipDrawable) bgshape.getDrawable(2);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.saving_recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        SavingCalculatorAdapter adapter = new SavingCalculatorAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateView(SavingModel model) {

    }

    @Override
    public void updateView(SavingCurModel model) {

    }
}
