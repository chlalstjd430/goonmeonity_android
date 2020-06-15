package com.example.gunmunity.discharge;

public class DischargeSaveData {
    String endlidate;
    String dischargedate;
    int _class;
    int army;

    //getter
    public String getEndlidate() {
        return endlidate;
    }
    public String getDischargedate(){return dischargedate;}

    public int get_class() {
        return _class;
    }

    public int getArmy() {
        return army;
    }

    //setter

    public void setendlidate(String endlidate) {
        this.endlidate = endlidate;
    }

    public void setDischargedate(String dischargedate){
        this.dischargedate = dischargedate;
    }

    public void set_class(int _class) {
        this._class = _class;
    }

    public void setArmy(int army) {
        this.army = army;
    }
}
