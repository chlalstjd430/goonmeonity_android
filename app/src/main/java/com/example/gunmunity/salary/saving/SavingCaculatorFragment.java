package com.example.gunmunity.salary.saving;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;

import java.util.ArrayList;
import java.util.List;

public class SavingCaculatorFragment extends Fragment implements SavingContract.View {

    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SavingCalculatorAdapter adapter;
    SavingCalculatorPersenter presenter;
    int code;

    public SavingCaculatorFragment(int code){
        this.presenter = new SavingCalculatorPersenter();
        presenter.setView(this);
        this.code = code;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.saving_calculator, container, false);
        initRecyclerView();
        setbutton();
        presenter.loaditem(code);
        return view;
    }

    private void setbutton() {
        Button editor = view.findViewById(R.id.saving_setting);
        editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SavingCaculatorSettingActivity.class);
                intent.putExtra("code",code);
                startActivityForResult(intent,0);
            }
        });
    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.saving_recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SavingCalculatorAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateView(SavingModel model) {
        TextView title = view.findViewById(R.id.title);
        TextView saving_product = view.findViewById(R.id.saving_product);
        TextView percentage = view.findViewById(R.id.percentage);
        TextView saving_remain_day = view.findViewById(R.id.saving_remain_day);
        TextView savin_endday = view.findViewById(R.id.savin_endday);
        TextView savin_newday = view.findViewById(R.id.savin_newday);
        TextView interestrate = view.findViewById(R.id.interestrate);

        ProgressBar progressBar = view.findViewById(R.id.progressBar2);

        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.progressconstraint);
        ConstraintSet constraintset = new ConstraintSet();
        constraintset.clone(constraintLayout);
        double percent = ((Double.parseDouble(model.getAllday()) -Double.parseDouble(model.getRemainDate()))  / Double.parseDouble(model.getAllday())) * 100;

        title.setText(model.getNickname() + "의 적금");
        saving_product.setText(model.getSavingNmae());
        percentage.setText(String.format("%.2f",percent) + " %");
        saving_remain_day.setText("D-"+model.getRemainDate());
        savin_endday.setText(model.getMaturityDate());
        savin_newday.setText(model.getNewDate());
        progressBar.setProgress((int)percent);
        interestrate.setText(model.getInterestRate()+"%");
        constraintset.setHorizontalBias(R.id.dtext, (float) (percent/100));
        constraintset.applyTo(constraintLayout);
    }

    @Override
    public void updateView(List<SavingCurModel> model) {
        adapter.setData(model);
        TextView saving_recycle_title = view.findViewById(R.id.saving_recycle_title);
        saving_recycle_title.setText("거래내역 ("+adapter.list.size()+"회차)");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0){
            presenter.loaditem(code);
        }
    }
}
