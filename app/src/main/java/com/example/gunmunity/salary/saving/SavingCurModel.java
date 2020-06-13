package com.example.gunmunity.salary.saving;

public class SavingCurModel {

    String CurrentMoney;
    String AllMoney;
    String date;
    int index;

    //setter
    public void setCurrentMoney(String currentMoney) {
        CurrentMoney = currentMoney;
    }

    public void setAllMoney(String allMoney) {
        AllMoney = allMoney;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //getter
    public String getCurrentMoney() {
        return CurrentMoney;
    }

    public String getAllMoney() {
        return AllMoney;
    }

    public String getDate() {
        return date;
    }

    public int getIndex() {
        return index;
    }
}
