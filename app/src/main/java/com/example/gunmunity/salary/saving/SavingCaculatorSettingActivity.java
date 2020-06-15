package com.example.gunmunity.salary.saving;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.gunmunity.R;
import com.example.gunmunity.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SavingCaculatorSettingActivity extends AppCompatActivity {

    EditText edit_tile;
    EditText edit_interestrate;
    EditText startdate;
    EditText enddate;
    EditText money;
    EditText cycle;
    int code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saving_editor);
        edit_tile = findViewById(R.id.edit_title);
        edit_interestrate = findViewById(R.id.edit_interestrate);
        startdate = findViewById(R.id.startdate);
        enddate = findViewById(R.id.enddate);
        money = findViewById(R.id.money);
        cycle = findViewById(R.id.cycle);
        Intent intent = getIntent();
        code = intent.getExtras().getInt("code");
        try {
            inittext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        settingbutton();
    }

    private void inittext() throws IOException {
        FileInputStream fis = null;
        BufferedReader buf = null;
        int i;
        String data[] = new String[7];
        String filename = code +".dat";
        File directory = getBaseContext().getFileStreamPath("saving");

        fis = new FileInputStream(directory.getAbsolutePath() +"/"+filename);
        buf = new BufferedReader(new InputStreamReader(fis));
        for(i=0;i<7;i++){
            String str = buf.readLine();
            data[i] = str;
        }

        edit_tile.setText(data[0]);
        edit_interestrate.setText(data[2]);
        money.setText(data[3]);
        cycle.setText(data[4]);
        startdate.setText(data[5]);
        enddate.setText(data[6]);
    }

    private void settingbutton() {
        Button savingbutton = findViewById(R.id.saving_button);
        savingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValidData() > 0)
                    return;
                try {
                    saveData();
                    Intent intent = getIntent();
                    setResult(1,intent);
                    finish();
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private int isValidData() {

        double Dedit_interestrate;
        Date Dstartdate;
        Date Denddate;
        int Dmoney, Dcycle;
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");

        int flag = 0;

        if(edit_tile.getText().toString().equals("")){
            edit_tile.setBackground(getBaseContext().getDrawable(R.drawable.error_edittext));
            flag  = 1;
        }
        else{
            edit_interestrate.setBackground(null);
        }

        try{
            Dedit_interestrate = Double.parseDouble(edit_interestrate.getText().toString());
            edit_interestrate.setBackground(null);
        }catch(Exception e){
            edit_interestrate.setBackground(getBaseContext().getDrawable(R.drawable.error_edittext));
            flag  = 1;
        }

        try{
            Dstartdate = fm.parse(startdate.getText().toString());
            startdate.setBackground(null);
        }catch(Exception e){
            e.printStackTrace();
            startdate.setBackground(getBaseContext().getDrawable(R.drawable.error_edittext));
            flag  = 1;
        }

        try{
            Denddate = fm.parse(enddate.getText().toString());
            enddate.setBackground(null);
        }catch(Exception e){
            enddate.setBackground(getBaseContext().getDrawable(R.drawable.error_edittext));
            flag  = 1;
        }

        try{
            Dmoney = Integer.parseInt(money.getText().toString());
            money.setBackground(null);
        }catch(Exception e){
            money.setBackground(getBaseContext().getDrawable(R.drawable.error_edittext));
            flag  = 1;
        }

        try{
            Dcycle = Integer.parseInt(cycle.getText().toString());
            if(Dcycle > 31 && Dcycle < 1){
                flag = 1;
                cycle.setBackground(getBaseContext().getDrawable(R.drawable.error_edittext));
            }
        }catch(Exception e){
            cycle.setBackground(getBaseContext().getDrawable(R.drawable.error_edittext));
            flag  = 1;
        }

        return flag;
    }

    private void saveData() throws IOException, ParseException {
        FileOutputStream fos = null;
        PrintWriter wbuf = null;

        String str = null;

        File directory = getBaseContext().getFileStreamPath("saving");
        Log.d("test",directory.getAbsolutePath());

        try {
            fos = new FileOutputStream(directory.getAbsolutePath() +"/"+code+".dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        wbuf = new PrintWriter(fos);

        Calendar cnewdate = Calendar.getInstance();
        Calendar cenddate = Calendar.getInstance();

        int Icycle = Integer.parseInt(cycle.getText().toString());
        int Imoney = Integer.parseInt(money.getText().toString());

        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");

        cnewdate.setTime(fm.parse(startdate.getText().toString()));
        cenddate.setTime(fm.parse(enddate.getText().toString()));


        wbuf.println(edit_tile.getText().toString());
        wbuf.println(Util.total_money(cnewdate,cenddate,Icycle,Imoney));
        wbuf.println(edit_interestrate.getText().toString());
        wbuf.println(money.getText().toString());
        wbuf.println(cycle.getText().toString());
        wbuf.println(startdate.getText().toString());
        wbuf.println(enddate.getText().toString());
        wbuf.close();
        fos.close();
        
    }

}
