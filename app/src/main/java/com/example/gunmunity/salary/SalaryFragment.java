package com.example.gunmunity.salary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;
import com.example.gunmunity.salary.saving.SavingContract;
import com.example.gunmunity.salary.saving.SavingCurModel;
import com.example.gunmunity.salary.saving.SavingModel;

import java.util.List;

public class SalaryFragment  extends Fragment implements SalaryContract.View {
    RecyclerView recyclerView;
    SalaryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SalaryContract.Presenter presenter;

    public SalaryFragment(){
        this.presenter=new SalaryPresenter();
        this.presenter.setView(this);
    }

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.salarycaculator,container,false);
        initRecyclerView();
        presenter.createModel();
        presenter.loaditem();
        return view;
    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.saving_recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SalaryAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateView(SalaryModel model) {
        TextView savin_newday = view.findViewById(R.id.savin_newday);
        TextView savin_endday = view.findViewById(R.id.savin_endday);
        TextView percentage = view.findViewById(R.id.percentage);
        TextView salary_remain_day = view.findViewById(R.id.salary_remain_day);
        ProgressBar progressBar = view.findViewById(R.id.progressBar2);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.progressconstraint);
        ConstraintSet constraintset = new ConstraintSet();
        constraintset.clone(constraintLayout);

        savin_newday.setText(model.getNewday());
        savin_endday.setText(model.getEndday());
        salary_remain_day.setText("D-"+model.getRemainday());
        percentage.setText(model.getPercentage()+"%");
        progressBar.setProgress((int)Double.parseDouble(model.getPercentage()));
        constraintset.setHorizontalBias(R.id.salarydtext, (float) (Double.parseDouble(model.getPercentage())/100));
        constraintset.applyTo(constraintLayout);
    }

    @Override
    public void updateView(List<SalaryListModel> model) {
        TextView saving_recycle_title = view.findViewById(R.id.saving_recycle_title);

        saving_recycle_title.setText("월급내역 ("+model.size()+"회차)");
        adapter.setData(model);
    }
}
