package com.example.gunmunity.discharge;

public class DischargeModel {
    String mon_text1;
    String mon_text2;
    String remain_text1;
    String remain_text2;
    String DischargeDay;
    String curclass;
    String specialist;
    String EnlistmentDay;
    String _dischargeDay;
    String Nickname;
    String class_text;
    String service_text;
    String remain_service_text;
    String promotion_text;

    //getter
    public String get_dischargeDay() {
        return _dischargeDay;
    }

    public String getMon_text1() {
        return mon_text1;
    }

    public String getMon_text2() {
        return mon_text2;
    }

    public String getRemain_text1() {
        return remain_text1;
    }

    public String getRemain_text2() {
        return remain_text2;
    }

    public String getDischargeDay() {
        return DischargeDay;
    }

    public String getNickname() {
        return Nickname;
    }

    public String getClass_text() {
        return class_text;
    }

    public String getService_text() {
        return service_text;
    }

    public String getRemain_service_text() {
        return remain_service_text;
    }

    public String getPromotion_text() {
        return promotion_text;
    }

    public String getCurclass() {
        return curclass;
    }

    public String getSpecialist() {
        return specialist;
    }

    public String getEnlistmentDay() {
        return EnlistmentDay;
    }

    //setter

    public void set_dischargeDay(String _dischargeDay) {
        this._dischargeDay = _dischargeDay;
    }

    public void setMon_text1(String mon_text1) {
        this.mon_text1 = mon_text1;
    }

    public void setMon_text2(String mon_text2) {
        this.mon_text2 = mon_text2;
    }

    public void setRemain_text1(String remain_text1) {
        this.remain_text1 = remain_text1;
    }

    public void setRemain_text2(String remain_text2) {
        this.remain_text2 = remain_text2;
    }

    public void setDischargeDay(String dischargeDay) {
        DischargeDay = dischargeDay;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public void setClass_text(String class_text) {
        this.class_text = class_text;
    }

    public void setService_text(String service_text) {
        this.service_text = service_text;
    }

    public void setRemain_service_text(String remain_service_text) {
        this.remain_service_text = remain_service_text;
    }

    public void setPromotion_text(String promotion_text) {
        this.promotion_text = promotion_text;
    }

    public void setCurclass(String curclass) {
        this.curclass = curclass;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public void setEnlistmentDay(String enlistmentDay) {
        EnlistmentDay = enlistmentDay;
    }
}
