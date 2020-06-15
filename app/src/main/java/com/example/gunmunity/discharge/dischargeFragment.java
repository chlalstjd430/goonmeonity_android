package com.example.gunmunity.discharge;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.example.gunmunity.R;

import java.io.IOException;


public class dischargeFragment extends Fragment implements DischargeContract.View{

    DischargeContract.Presenter presenter;
    View view;
    int flag = 0;

    public dischargeFragment(int i) {
        presenter = new DischargePresenter();
        this.flag = i;
    }

    private void setspinner() {
        String[] _class = {"병사","간부"};
        String[] army = {"육군","해군","공군"};
        Spinner classification_of_service = (Spinner)view.findViewById(R.id.classselection);
        Spinner armyselection = (Spinner)view.findViewById(R.id.armyselection);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,_class);
        ArrayAdapter<String> armyAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,army);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        armyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classification_of_service.setAdapter(classAdapter);
        armyselection.setAdapter(armyAdapter);
        classification_of_service.setPrompt("복무 구분");
        armyselection.setPrompt("군 구분");
        classification_of_service.setSelection(0);
        classification_of_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position == 1){
                    System.out.println("간부");
                    getFragmentManager().beginTransaction().replace(R.id.main_container,new dischargeCadreFragment(1)).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.discharge_fragment,container,false);
        presenter.setView(this);
        presenter.createModel();
        if(flag != 1){
            if(presenter.loaditem() == 1){
                getFragmentManager().beginTransaction().replace(R.id.main_container,new dischargeCadreFragment(0)).commit();
            }
        }
        setspinner();
        setbutton();
        return view;
    }

    private void setbutton() {
        Button savebutton = view.findViewById(R.id.apply);
        final Spinner classification_of_service = (Spinner)view.findViewById(R.id.classselection);
        final Spinner armyselection = (Spinner)view.findViewById(R.id.armyselection);
        final EditText enli_day_edit = view.findViewById(R.id.enli_day_edit);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DischargeSaveData data = new DischargeSaveData();
                data.set_class(classification_of_service.getSelectedItemPosition());
                data.setArmy(armyselection.getSelectedItemPosition());
                data.setendlidate(enli_day_edit.getText().toString());
                try {
                    presenter.saveitem(data);
                    presenter.createModel();
                    if(presenter.loaditem() == 1){
                        getFragmentManager().beginTransaction().replace(R.id.main_container,new dischargeCadreFragment(0)).commit();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void updateView(DischargeModel model) {
        TextView mon_text1 = view.findViewById(R.id.mon_text1);
        TextView mon_text2 = view.findViewById(R.id.mon_text2);
        TextView remain_text1 = view.findViewById(R.id.remain_text1);
        TextView remain_text2 = view.findViewById(R.id.remain_text2);
        TextView DischargeDay = view.findViewById(R.id.DischargeDay);
        TextView curclass = view.findViewById(R.id.curclass);
        TextView percentage = view.findViewById(R.id.percentage);
        TextView EnlistmentDay = view.findViewById(R.id.EnlistmentDay);
        TextView dischargeDay = view.findViewById(R.id.dischargeDay);
        TextView class_text = view.findViewById(R.id.class_text);
        TextView remain_service_text = view.findViewById(R.id.remain_service_text);
        TextView service_text = view.findViewById(R.id.service_text);
        TextView promotion_text = view.findViewById(R.id.promotion_text);
        EditText enli_day_edit = view.findViewById(R.id.enli_day_edit);
        ProgressBar pgb = view.findViewById(R.id.progressBar);
        Spinner classification_of_service = (Spinner)view.findViewById(R.id.classselection);
        Spinner armyselection = (Spinner)view.findViewById(R.id.armyselection);
        ConstraintSet constraintset = new ConstraintSet();
        ConstraintLayout pgblayout = view.findViewById(R.id.progresslayout);
        constraintset.clone(pgblayout);


        mon_text1.setText(model.getMon_text1());
        mon_text2.setText(model.getMon_text2());
        remain_text1.setText(model.getRemain_text1());
        remain_text2.setText(model.getRemain_text2());
        DischargeDay.setText(model.getDischargeDay());
        curclass.setText(model.getCurclass());
        percentage.setText(model.getPercentage() + " %");
        EnlistmentDay.setText(model.getEnlistmentDay());
        dischargeDay.setText(model.get_dischargeDay());
        class_text.setText(model.getClass_text());
        service_text.setText(model.getService_text());
        remain_service_text.setText(model.getRemain_service_text());
        promotion_text.setText(model.getPromotion_text());
        enli_day_edit.setText(model.getEnlistmentDay().substring(0,4)+model.getEnlistmentDay().substring(5,7)+model.getEnlistmentDay().substring(8,10));
        pgb.setProgress((int)Double.parseDouble(model.getPercentage()));
        constraintset.setHorizontalBias(R.id.firstlayout,((float)model.get_1per()/100));
        constraintset.setHorizontalBias(R.id.speciallayout,((float)model.get_2per()/100));
        constraintset.setHorizontalBias(R.id.sergeantlayout,((float)model.get_3per()/100));
        constraintset.applyTo(pgblayout);
        pgblayout.bringChildToFront(view.findViewById(R.id.firstlayout));
        pgblayout.bringChildToFront(view.findViewById(R.id.speciallayout));
        pgblayout.bringChildToFront(view.findViewById(R.id.sergeantlayout));
        classification_of_service.setSelection(0);
        armyselection.setSelection(0);
        System.out.println("remain_1 : " +model.getRemain_text1());
        System.out.println("remain_2 : " +model.getRemain_text2());


    }
}
