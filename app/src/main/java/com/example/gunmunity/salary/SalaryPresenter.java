package com.example.gunmunity.salary;


import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SalaryPresenter implements SalaryContract.Presenter{

    SalaryContract.View view;

    @Override
    public void setView(SalaryContract.View view) {
        this.view = view;
    }

    @Override
    public void createModel() {

    }

    @Override
    public int loaditem() {
        try {
            return load_data();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int load_data() throws IOException, ParseException {
        FileInputStream fis = null;
        BufferedReader buf = null;
        int i;
        String data[] = new String[3];

        fis = ((Fragment)view).getContext().openFileInput("discharge.dat");
        buf = new BufferedReader(new InputStreamReader(fis));
        for(i=0;i<3;i++){
            String str = buf.readLine();
            data[i] = str;
        }

        if(data[0].equals("1"))
            return 1;

        Setting_data(data);
        return 0;
    }

    private void Setting_data(String[] data) throws ParseException {
        String discharge_year,discharge_month, discharge_date;
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        SalaryModel model =new SalaryModel();

        Calendar cal_enli = new GregorianCalendar();
        Calendar cal_discharge = new GregorianCalendar();
        Calendar fundament = new GregorianCalendar(2018,Calendar.OCTOBER,1);
        cal_enli.setTime(fm.parse(data[2]));
        cal_discharge.setTime(fm.parse(data[2]));

        switch(data[1]){
            case "0":
                cal_discharge.add(Calendar.MONTH, 21);
                cal_discharge.add(Calendar.DATE, -1);
                break;
            case "1":
                cal_discharge.add(Calendar.MONTH, 21);
                cal_discharge.add(Calendar.DATE, -1);
                break;
            case "2":
                cal_discharge.add(Calendar.MONTH, 23);
                cal_discharge.add(Calendar.DATE, -1);
                break;
            case "3":
                cal_discharge.add(Calendar.MONTH, 24);
                cal_discharge.add(Calendar.DATE, -1);
                break;
        }



        Date dischrage = cal_discharge.getTime();
        Date enli = cal_enli.getTime();
        Date funda = fundament.getTime();
        Calendar cur = new GregorianCalendar();

        long shorten = (dischrage.getTime() - funda.getTime() >= 0) ?(((dischrage.getTime() - funda.getTime()) / 14)/(24*60*60*1000)) + 1: 0;
        cal_discharge.add(Calendar.DATE, (int) (-1 * shorten));
        dischrage = cal_discharge.getTime();
        long allday = (dischrage.getTime()-enli.getTime())/(24*60*60*1000);
        long difference =(cur.getTime().getTime() - enli.getTime())/(24*60*60*1000);
        double percentage = (double)difference / (double)allday;


        SimpleDateFormat year_format = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_format = new SimpleDateFormat("MM");
        SimpleDateFormat day_format = new SimpleDateFormat("dd");
        discharge_year = year_format.format(dischrage);
        discharge_month = month_format.format(dischrage);
        discharge_date = day_format.format(dischrage);


        model.setEndday(discharge_year+"."+discharge_month+"."+discharge_date);
        model.setNewday(year_format.format(fm.parse(data[2]))+"."+month_format.format(fm.parse(data[2]))+"."+day_format.format(fm.parse(data[2])));
        model.setPercentage(String.format("%.2f",percentage * 100));
        model.setRemainday(""+(allday - difference));
        view.updateView(model);
        setlist(cal_enli,cal_discharge,data[1]);
    }

    private void setlist(Calendar cal_enli, Calendar cal_discharge,String army) {
        Calendar cur = new GregorianCalendar();
        Calendar ptr = new GregorianCalendar();
        final String[] class_flag = {"이병","일병","상병","병장"};
        List<SalaryListModel> model = new ArrayList<SalaryListModel>();
        SalaryListModel temp = null;
        int totalmoney = 0, i =0;
        SimpleDateFormat year_format = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_format = new SimpleDateFormat("MM");
        SimpleDateFormat day_format = new SimpleDateFormat("dd");

        Calendar[] _class = {new GregorianCalendar(),new GregorianCalendar(),new GregorianCalendar(),new GregorianCalendar()};

        ptr.setTime(cal_enli.getTime());
        ptr.set(Calendar.DATE, 10);

        for(int j = 0;j<4;j++)
            _class[j].setTime(cal_enli.getTime());

        switch(army){
            case "0":
                _class[1].add(Calendar.MONTH, 2);
                _class[1].set(Calendar.DATE, 1);
                _class[2].add(Calendar.MONTH, 8);
                _class[2].set(Calendar.DATE, 1);
                _class[3].add(Calendar.MONTH, 14);
                _class[3].set(Calendar.DATE, 1);
                break;
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
        }


        if(ptr.getTime().getTime() <= cal_enli.getTime().getTime())
            ptr.add(Calendar.MONTH, 1);

        while(ptr.getTime().getTime() <= cur.getTime().getTime()){
            String class_text = get_class(ptr,_class);
            int sal = get_money(class_text,ptr);
            temp = new SalaryListModel();
            temp.setPhase_text(year_format.format(ptr.getTime()) + "." + month_format.format(ptr.getTime()));
            temp.setPayment_date(year_format.format(ptr.getTime()) + "." + month_format.format(ptr.getTime())+"."+day_format.format(ptr.getTime()));
            temp.setCurclass(class_text);
            temp.setPayment_money(""+sal);
            totalmoney += sal;
            temp.setPayment_allmoney(""+totalmoney);
            model.add(temp);
            ptr.add(Calendar.MONTH, 1);
            i++;
        }
        view.updateView(model);

    }

    private String get_class(Calendar cur, Calendar[] _class){
        final String[] class_flag = {"이병","일병","상병","병장"};
        String _class_text = null;
        for(int i = 2; i<5; i++) {
            if(cur.getTime().getTime() < _class[i-1].getTime().getTime()) {
                _class_text = class_flag[i-2];
                break;
            }
        }

        if(_class_text == null) {
            _class_text = class_flag[3];
        }
        return _class_text;
    }

    private int get_money(String _class, Calendar cur){
        switch (_class){
            case "이병":
                if(cur.get(Calendar.YEAR) >= 2020)
                    return 408100;
                else if(cur.get(Calendar.YEAR) >= 2018)
                    return 306100;
                break;
            case "일병":
                if(cur.get(Calendar.YEAR) >= 2020)
                    return 441700;
                else if(cur.get(Calendar.YEAR) >= 2018)
                    return 331300;
                break;
            case "상병":
                if(cur.get(Calendar.YEAR) >= 2020)
                    return 488200;
                else if(cur.get(Calendar.YEAR) >= 2018)
                    return 366200;
                break;
            case "병장":
                if(cur.get(Calendar.YEAR) >= 2020)
                    return 540900;
                else if(cur.get(Calendar.YEAR) >= 2018)
                    return 405700;
                break;

        }
        return 0;
    }

}
