package com.example.gunmunity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Util {

    public static int total_money(Calendar newdate, Calendar maturitydate, int cycle, int money){
        Calendar cur = new GregorianCalendar();

        if(maturitydate.getTime().getTime() < cur.getTime().getTime())
            cur = maturitydate;

        int Allmoney = 0;

        int CurMonth = cur.get(Calendar.MONTH);

        if(CurMonth <= newdate.get(Calendar.MONTH) && newdate.get(Calendar.YEAR) < cur.get(Calendar.YEAR)){
            CurMonth += 12;
        }

        int sub = CurMonth - newdate.get(Calendar.MONTH);
        if(cycle < cur.get(Calendar.DATE))
            sub += (cur.get(Calendar.YEAR) - newdate.get(Calendar.YEAR) - 1) * 12;
        Allmoney = sub * money;
        return Allmoney;
    }
}
