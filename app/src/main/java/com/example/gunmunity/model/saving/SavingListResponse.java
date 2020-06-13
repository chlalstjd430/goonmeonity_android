package com.example.gunmunity.model.saving;

import java.util.ArrayList;

public class SavingListResponse {
    ArrayList<list> data;

    class list{
        int amount;
        String enddate;
        int id;
        double interestRate;
        String name;
        int payment;
        int paymentDay;
        String startdate;
    }


}
