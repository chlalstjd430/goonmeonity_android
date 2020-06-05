package com.example.gunmunity.discharge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gunmunity.R;


public class dischargeFragment extends Fragment implements DischargeContract.View{

    DischargeContract.Presenter presenter;
    View view;

    public dischargeFragment() {

        presenter = new DischargePresenter();
        presenter.setView(this);

    }

    private void setspinner() {
        String[] _class = {"간부","병사"};
        String[] army = {"육군","해군","공군"};
        Spinner classification_of_service = (Spinner)view.findViewById(R.id.armyselection);
        Spinner armyselection = (Spinner)view.findViewById(R.id.classselection);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,_class);
        ArrayAdapter<String> armyAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,army);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        armyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classification_of_service.setAdapter(classAdapter);
        armyselection.setAdapter(armyAdapter);
        classification_of_service.setPrompt("복무 구분");
        armyselection.setPrompt("군 구분");
        classification_of_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("test");
            }
        });

        EditText enli_day = view.findViewById(R.id.enli_day_edit);
        enli_day.setHint("입대일 ex)20191010");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.discharge_fragment,container,false);
        setspinner();
        //presenter.loaditem();
        return view;
    }

    @Override
    public void updateView(DischargeModel model) {
        TextView mon_text1 = view.findViewById(R.id.mon_text1);
        TextView mon_text2 = view.findViewById(R.id.mon_text2);
        TextView remain_text1 = view.findViewById(R.id.remain_text1);
        TextView remain_text2 = view.findViewById(R.id.remain_text2);
        TextView DischargeDay = view.findViewById(R.id.DischargeDay);
        TextView curclass = view.findViewById(R.id.curclass);
        TextView specialist = view.findViewById(R.id.specialist);
        TextView EnlistmentDay = view.findViewById(R.id.EnlistmentDay);
        TextView dischargeDay = view.findViewById(R.id.dischargeDay);
        TextView class_text = view.findViewById(R.id.class_text);
        TextView remain_service_text = view.findViewById(R.id.remain_service_text);
        TextView service_text = view.findViewById(R.id.service_text);
        TextView promotion_text = view.findViewById(R.id.promotion_text);

        mon_text1.setText(model.getMon_text1());
        mon_text2.setText(model.getMon_text2());
        remain_text1.setText(model.getRemain_text1());
        remain_text2.setText(model.getRemain_text2());
        DischargeDay.setText(model.getDischargeDay());
        curclass.setText(model.getCurclass());
        specialist.setText(model.getSpecialist());
        EnlistmentDay.setText(model.getEnlistmentDay());
        dischargeDay.setText(model.get_dischargeDay());
        class_text.setText(model.getClass_text());
        service_text.setText(model.getService_text());
        remain_service_text.setText(model.getRemain_service_text());
        promotion_text.setText(model.getPromotion_text());
    }
}
