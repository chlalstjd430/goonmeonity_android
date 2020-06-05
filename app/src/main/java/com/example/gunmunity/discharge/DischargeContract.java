package com.example.gunmunity.discharge;

public interface DischargeContract {
    interface View{
        public void updateView(DischargeModel model);
    }

    interface Presenter {
        void setView(View view);
        void createModel();
        void loaditem();
    }
}
