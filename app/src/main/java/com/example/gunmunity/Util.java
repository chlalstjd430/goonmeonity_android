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

        CurMonth += 12 * (cur.get(Calendar.YEAR) - newdate.get(Calendar.YEAR));

        int sub = CurMonth - newdate.get(Calendar.MONTH);
        if(cycle <= cur.get(Calendar.DATE))
            sub++;
        Allmoney = sub * money;
        return Allmoney;
    }
}
