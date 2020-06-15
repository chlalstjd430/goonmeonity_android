package com.example.gunmunity.discharge;

import android.os.Bundle;
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
import androidx.fragment.app.Fragment;

import com.example.gunmunity.R;

import java.io.IOException;
import java.text.ParseException;

public class dischargeCadreFragment extends Fragment implements DischargeContract.View{

    DischargeContract.Presenter presenter;
    View view;
    int flag = 0;

    public dischargeCadreFragment(int i) {
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
        classification_of_service.setSelection(1);
        classification_of_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position == 0){
                    getFragmentManager().beginTransaction().replace(R.id.main_container,new dischargeFragment(1)).commit();
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
        presenter.setView(this);
        view = inflater.inflate(R.layout.discharge_cadre_fragment,container,false);
        presenter.createModel();
        if(flag != 1){
            try {
                if(presenter.cadre_loaditem() == 1){
                    getFragmentManager().beginTransaction().replace(R.id.main_container,new dischargeCadreFragment(0)).commit();
                }
            } catch (ParseException e) {
                e.printStackTrace();
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
        final EditText discharge_edit = view.findViewById(R.id.discharge_edit);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DischargeSaveData data = new DischargeSaveData();

                data.set_class(classification_of_service.getSelectedItemPosition());
                data.setArmy(armyselection.getSelectedItemPosition());
                data.setendlidate(enli_day_edit.getText().toString());
                data.setDischargedate(discharge_edit.getText().toString());
                System.out.println("ret : " + data.get_class());
                try {
                    System.out.println("ret : " + presenter.cadre_saveitem(data));
                    presenter.createModel();
                    if(presenter.cadre_loaditem() == 1){
                        getFragmentManager().beginTransaction().replace(R.id.main_container,new dischargeCadreFragment(0)).commit();
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void updateView(DischargeModel model) {
        this.getLayoutInflater().inflate(R.layout.discharge_cadre_fragment,null,false);
        TextView mon_text1 = view.findViewById(R.id.mon_text1);
        TextView mon_text2 = view.findViewById(R.id.mon_text2);
        TextView dischargeDay = view.findViewById(R.id.dischargeDay);
        TextView DischargeDay = view.findViewById(R.id.DischargeDay);
        TextView remain_text1 = view.findViewById(R.id.remain_text1);
        TextView remain_text2 = view.findViewById(R.id.remain_text2);
        TextView percentage = view.findViewById(R.id.percentage);
        TextView remain_service_text = view.findViewById(R.id.remain_service_text);
        TextView service_text = view.findViewById(R.id.service_text);
        TextView EnlistmentDay = view.findViewById(R.id.EnlistmentDay1);
        EditText enli_day_edit = view.findViewById(R.id.enli_day_edit);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        EditText discharge_edit = view.findViewById(R.id.discharge_edit);


        EnlistmentDay.setText(model.getEnlistmentDay().substring(0,4)+"."+model.getEnlistmentDay().substring(4,6)+"."+model.getEnlistmentDay().substring(6,8));
        enli_day_edit.setText(model.getEnlistmentDay());
        discharge_edit.setText(model.getDischargDate());
        mon_text1.setText(model.getMon_text1());
        mon_text2.setText(model.getMon_text2());
        remain_text1.setText(model.getRemain_text1());
        remain_text2.setText(model.getRemain_text2());
        DischargeDay.setText(model.getDischargeDay());
        dischargeDay.setText(model.get_dischargeDay());
        service_text.setText(model.getService_text());
        remain_service_text.setText(model.getRemain_service_text());
        percentage.setText(model.getPercentage() + " %");
        progressBar.setProgress((int)Double.parseDouble(model.getPercentage()));


    }
}
