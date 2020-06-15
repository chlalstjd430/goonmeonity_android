package com.example.gunmunity.salary.saving;


import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SavingCalculatorPersenter implements SavingContract.Presenter{

    private SavingContract.View view;
    private SavingModel Smodel;
    private List<SavingCurModel> Cmodel;

    @Override
    public void setView(SavingContract.View view) {
        this.view = view;
    }

    @Override
    public void createModel() {
        this.Smodel = new SavingModel();
        this.Cmodel = new ArrayList<SavingCurModel>();
    }

    @Override
    public int loaditem(int code) {
        createModel();
        try {
            load_data(code);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    private void load_data(int code)  throws Exception{
        FileInputStream fis = null;
        BufferedReader buf = null;
        int i;
        String data[] = new String[7];
        String filename = code+".dat";

        File directory = ((Fragment)view).getContext().getFileStreamPath("saving");

        fis = new FileInputStream(directory.getAbsolutePath() +"/"+filename);
        buf = new BufferedReader(new InputStreamReader(fis));
        for(i=0;i<7;i++){
            String str = buf.readLine();
            data[i] = str;
        }

        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        Date newdate = fm.parse(data[5]);
        Date maturitydate = fm.parse(data[6]);
        Date ctime = new Date();

        long allday = (maturitydate.getTime() - newdate.getTime()) / (24*60*60*1000);
        long remainday = ((maturitydate.getTime() - ctime.getTime()) / (24*60*60*1000) )+ 1;

        Smodel.setNickname("홍길동");
        Smodel.setSavingNmae(data[0]);
        Smodel.setInterestRate(data[2]);
        Smodel.setMaturityDate(data[6].substring(0,4)+"."+data[6].substring(4,6)+"."+data[6].substring(6));
        Smodel.setNewDate(data[5].substring(0,4)+"."+data[5].substring(4,6)+"."+data[5].substring(6));
        Smodel.setAllday(""+allday);
        Smodel.setRemainDate(""+remainday);
        view.updateView(Smodel);

        load_savingitem(newdate, maturitydate, data[3], data[4]);
    }

    private void load_savingitem(Date newdate, Date maturitydate, String money, String cycle) {
        Calendar cur = new GregorianCalendar();
        Calendar cnewdate = new GregorianCalendar(), cmaturiy = new GregorianCalendar();
        cnewdate.setTime(newdate);
        cmaturiy.setTime(maturitydate);
        int CurMonth = cur.get(Calendar.MONTH);
        int Icycle = Integer.parseInt(cycle);
        int Allmoney = 0;

        if(cur.get(Calendar.YEAR) > cnewdate.get(Calendar.YEAR))
            CurMonth += (cur.get(Calendar.YEAR) - cnewdate.get(Calendar.YEAR)) * 12;
        int sub = CurMonth - cnewdate.get(Calendar.MONTH);

        SimpleDateFormat fm = new SimpleDateFormat("yyyy.MM.dd");

        if(cnewdate.get(Calendar.DATE) != Icycle){

            Allmoney += Integer.parseInt(money);
            SavingCurModel model = new SavingCurModel();
            model.setCurrentMoney(money);
            model.setAllMoney(""+Allmoney);
            model.setIndex(0);
            model.setDate(fm.format(cnewdate.getTime()));
            Cmodel.add(model);
            cnewdate.set(Calendar.DATE,Icycle);
            cnewdate.add(Calendar.MONTH,1);
            for(int i = 1; i <= sub && cnewdate.getTime().getTime() < cur.getTime().getTime();i++){
                Allmoney += Integer.parseInt(money);
                model = new SavingCurModel();
                model.setCurrentMoney(money);
                model.setAllMoney(""+Allmoney);
                model.setIndex(i);
                model.setDate(fm.format(cnewdate.getTime()));
                Cmodel.add(model);
                cnewdate.add(Calendar.MONTH, 1);
            }

        }else{

            for(int i = 0; i <= sub&& cnewdate.getTime().getTime() < cur.getTime().getTime();i++){
                Allmoney += Integer.parseInt(money);
                SavingCurModel model = new SavingCurModel();
                model.setCurrentMoney(money);
                model.setAllMoney(""+Allmoney);
                model.setIndex(i);
                model.setDate(fm.format(cnewdate.getTime()));
                Cmodel.add(model);
                cnewdate.add(Calendar.MONTH, 1);
            }
        }


        view.updateView(Cmodel);
    }
}
