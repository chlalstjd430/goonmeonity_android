package com.example.gunmunity.salary.saving;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gunmunity.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SavingListFragment extends Fragment implements SavinglistContract.View{
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SavingListAdapter adapter;
    SavinglistContract.Presenter presenter;

    public SavingListFragment(){
        presenter = new SavinglistPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.savinglist_activity, container, false);
        presenter.setView(this);
        initRecyclerView();
        settingbutton();
        presenter.loaditem();
        return view;
    }

    private void settingbutton() {
        Button create_button = view.findViewById(R.id.create_saving_button);
        Button remove_button = view.findViewById(R.id.remove_saving_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int code = -1;
                try {
                    code = presenter.getnamecode();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(code == -1)
                    code = 0;

                Intent intent = new Intent(getContext(),SavingCaculatorSettingActivity.class);
                intent.putExtra("code",code);
                startActivityForResult(intent,0);

            }
        });

        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout layout = view.findViewById(R.id.constraintLayout2);

                if(adapter.getRemove_flag() == 0) {
                    Log.d("test","flagon");
                    layout.setBackgroundColor(Color.rgb(46,44,44));
                    adapter.setRemove_flag(1);
                }
                else if(adapter.getRemove_flag() == 1){
                    Log.d("test","flagoff");
                    layout.setBackgroundColor(Color.rgb(239,236,236));
                    adapter.setRemove_flag(0);
                }
            }
        });

    }

    private void initRecyclerView() {
        recyclerView = view.findViewById(R.id.saving_recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SavingListAdapter(this.getContext(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateView(List<SavinglistModel> model,int num) {
        adapter.setData(model);
        TextView saving_recycle_title = view.findViewById(R.id.saving_recycle_title);
        saving_recycle_title.setText("적금개수 ("+num +"개)");
    }

    public void request_remove(int code){
        presenter.removeitem(code);
        presenter.loaditem();
    }

}
