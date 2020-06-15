package com.example.gunmunity.discharge;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DischargePresenter implements DischargeContract.Presenter {

    private DischargeContract.View view;
    private DischargeModel model;

    @Override
    public void setView(DischargeContract.View view) {
        this.view = view;
    }

    @Override
    public void createModel() {
        model = new DischargeModel();
    }

    @Override
    public int cadre_loaditem() {
        try {
            return cadre_load_data();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int cadre_load_data() throws IOException, ParseException {
        FileInputStream fis = null;
        BufferedReader buf = null;
        int i;
        String data[] = new String[4];

        fis = ((Fragment)view).getContext().openFileInput("discharge.dat");
        buf = new BufferedReader(new InputStreamReader(fis));
        data[0] = buf.readLine();
        if(data[0].equals("0")){
            return 1;
        }

        for(i=1;i<4;i++){
            String str = buf.readLine();
            data[i] = str;
        }



        cadre_Setting_data(data);
        view.updateView(model);
        return 0;
    }

    private void cadre_Setting_data(String[] data) throws ParseException {
        Calendar cal_enli = new GregorianCalendar();
        Calendar cal_discharge = new GregorianCalendar();
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        cal_enli.setTime(fm.parse(data[2]));
        cal_discharge.setTime(fm.parse(data[3]));

        Date dischrage = cal_discharge.getTime();
        Date enli = cal_enli.getTime();
        Calendar cur = new GregorianCalendar();

        long allday = (dischrage.getTime()-enli.getTime())/(24*60*60*1000);
        long difference =(cur.getTime().getTime() - enli.getTime())/(24*60*60*1000);
        int dif_month = (int) (cal_discharge.get(Calendar.MONTH) - cur.get(Calendar.MONTH));
        int dif_day = (int) (cal_discharge.get(Calendar.DATE) - cur.get(Calendar.DATE));
        double percentage = (double)difference / (double)allday;

        SimpleDateFormat year_format = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_format = new SimpleDateFormat("MM");
        SimpleDateFormat day_format = new SimpleDateFormat("dd");

        if(dif_day < 0) {
            --dif_month;
            dif_day += cur.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        if(dif_month < 0) {
            dif_month += 12 * (cal_discharge.get(Calendar.YEAR) - cur.get(Calendar.YEAR));
        }

        model.setMon_text1(""+String.format("%02d",dif_month).substring(0,1));
        model.setMon_text2(""+String.format("%02d",dif_month).substring(1));
        model.setRemain_text1(""+String.format("%02d",dif_day).substring(0,1));
        model.setRemain_text2(""+String.format("%02d",dif_day).substring(1));
        model.setDischargeDay(year_format.format(fm.parse(data[3]))+"년"+month_format.format(fm.parse(data[3]))+"월"+day_format.format(fm.parse(data[3]))+"일");
        model.set_dischargeDay(year_format.format(fm.parse(data[3]))+"."+month_format.format(fm.parse(data[3]))+"."+day_format.format(fm.parse(data[3]))+".");
        model.setPercentage(String.format("%.2f",percentage * 100));
        model.setService_text(""+difference);
        model.setCardre(Integer.parseInt(data[0]));
        model.setDischargDate(data[3]);
        model.setEnlistmentDay(data[2]);
        model.setRemain_service_text(""+(allday - difference));

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

    @Override
    public int saveitem(DischargeSaveData data) throws IOException {
        if(isvalid(data) < 0){
            return -1;
        }

        FileOutputStream fos = null;
        PrintWriter buf = null;

        try {
            fos = ((Fragment)view).getContext().openFileOutput("discharge.dat", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
        buf = new PrintWriter(fos);
        buf.println(data.get_class());
        buf.println(""+data.getArmy());
        buf.println(""+data.getEndlidate());
        buf.close();
        fos.close();
        return 0;
    }

    private int cadre_isvalid(DischargeSaveData data) {
        if(data.get_class() == 0){
            System.out.println("class?");
            return -1;
        }

        if(data.getArmy() > 3 && data.getArmy() < 0)
            return -1;
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        try{
            fm.parse(data.getEndlidate());
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        try{
            fm.parse(data.getDischargedate());
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public int cadre_saveitem(DischargeSaveData data) throws IOException {
        if(cadre_isvalid(data) < 0){
            return -1;
        }

        FileOutputStream fos = null;
        PrintWriter buf = null;

        try {
            fos = ((Fragment)view).getContext().openFileOutput("discharge.dat", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
        buf = new PrintWriter(fos);
        buf.println(data.get_class());
        buf.println(""+data.getArmy());
        buf.println(""+data.getEndlidate());
        buf.println(""+data.getDischargedate());
        buf.close();
        fos.close();
        return 0;
    }

    private int isvalid(DischargeSaveData data) {
        if(data.get_class() == 1)
            return -1;
        if(data.getArmy() > 3 && data.getArmy() < 0)
            return -1;
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        try{
            fm.parse(data.getEndlidate());
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    private int load_data()  throws Exception{
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
        view.updateView(model);
        return 0;
    }

    private void Setting_data(String[] data) throws ParseException {
        String[] discharge_day = new String[3];
        final String[] class_flag = {"이병","일병","상병","병장"};
        String _class_text = null;
        String remain_class = null;
        String discharge_year,discharge_month, discharge_date;
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");

        Calendar cal_enli = new GregorianCalendar();
        Calendar cal_discharge = new GregorianCalendar();
        Calendar fundament = new GregorianCalendar(2018,Calendar.OCTOBER,1);

        cal_enli.setTime(fm.parse(data[2]));
        cal_discharge.setTime(fm.parse(data[2]));
        Calendar[] _class = {new GregorianCalendar(),new GregorianCalendar(),new GregorianCalendar(),new GregorianCalendar()};

        for(int i = 0;i<4;i++)
            _class[i].setTime(fm.parse(data[2]));

        switch(data[1]){
            case "0":
                cal_discharge.add(Calendar.MONTH, 21);
                cal_discharge.add(Calendar.DATE, -1);
                _class[1].add(Calendar.MONTH, 2);
                _class[1].set(Calendar.DATE, 1);
                _class[2].add(Calendar.MONTH, 8);
                _class[2].set(Calendar.DATE, 1);
                _class[3].add(Calendar.MONTH, 14);
                _class[3].set(Calendar.DATE, 1);
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
        //Date cur1 = cur.getTime();

        long shorten = (dischrage.getTime() - funda.getTime() >= 0) ?(((dischrage.getTime() - funda.getTime()) / 14)/(24*60*60*1000)) + 1: 0;
        cal_discharge.add(Calendar.DATE, (int) (-1 * shorten));
        dischrage = cal_discharge.getTime();
        long allday = (dischrage.getTime()-enli.getTime())/(24*60*60*1000);
        long difference =(cur.getTime().getTime() - enli.getTime())/(24*60*60*1000);
        int dif_month = (int) (cal_discharge.get(Calendar.MONTH) - cur.get(Calendar.MONTH));
        int dif_day = (int) (cal_discharge.get(Calendar.DATE) - cur.get(Calendar.DATE));
        double percentage = (double)difference / (double)allday;


        SimpleDateFormat year_format = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_format = new SimpleDateFormat("MM");
        SimpleDateFormat day_format = new SimpleDateFormat("dd");
        discharge_year = year_format.format(dischrage);
        discharge_month = month_format.format(dischrage);
        discharge_date = day_format.format(dischrage);


        if(dif_day < 0) {
            --dif_month;
            dif_day += cur.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        if(dif_month < 0) {
            dif_month += 12;
        }

        for(int i=2;i<5;i++) {

            if(cur.getTime().getTime() < _class[i-1].getTime().getTime()) {

                _class_text = class_flag[i-2] +" "+(Math.abs(_class[i-2].get(Calendar.MONTH)-cur.get(Calendar.MONTH))+1) + "호봉";
                remain_class = ""+(_class[i-1].getTime().getTime() - cur.getTime().getTime())/(24*60*60*1000);
                break;
            }
        }

        if(_class_text == null) {
            System.out.println("data : "+ cal_enli.get(Calendar.MONTH));
            _class_text = class_flag[3] +" "+(Math.abs( _class[3].get(Calendar.MONTH)- cur.get(Calendar.MONTH))+1) + "호봉";
            remain_class = ""+(allday - difference);
        }

        model.setMon_text1(""+String.format("%02d",dif_month).substring(0,1));
        model.setMon_text2(""+String.format("%02d",dif_month).substring(1));
        model.setRemain_text1(""+String.format("%02d",dif_day).substring(0,1));
        model.setRemain_text2(""+String.format("%02d",dif_day).substring(1));
        model.setDischargeDay(discharge_year+"년 "+discharge_month+"월 "+discharge_date+"일");
        model.set_dischargeDay(discharge_year+"."+discharge_month+"."+discharge_date);
        model.setEnlistmentDay(year_format.format(fm.parse(data[2]))+"."+month_format.format(fm.parse(data[2]))+"."+day_format.format(fm.parse(data[2])));
        model.setPercentage(String.format("%.2f",percentage * 100));
        model.setService_text(""+difference);
        model.setClass_text(_class_text);
        model.setRemain_service_text(""+(allday - difference));
        model.setCurclass(_class_text.substring(0, 2));
        model.setPromotion_text(remain_class);
        model.setCardre(Integer.parseInt(data[0]));

        model.set_1per((int) (((_class[1].getTime().getTime() - enli.getTime())/(24*60*60*1000)/(double)allday) * 100));
        model.set_2per((int) (((_class[2].getTime().getTime() - enli.getTime())/(24*60*60*1000)/(double)allday) * 100));
        model.set_3per((int) (((_class[3].getTime().getTime() - enli.getTime())/(24*60*60*1000)/(double)allday) * 100));
        model.setCardre(Integer.parseInt(data[0]));
        model.setArmy(Integer.parseInt(data[1]));
    }
}
