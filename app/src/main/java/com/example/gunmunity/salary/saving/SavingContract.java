package com.example.gunmunity.salary.saving;

public interface SavingContract {

    interface View{
        public void updateView(SavingModel model);
        public void updateView(SavingCurModel model);
    }

    interface Presenter {
        void setView(View view);
        void createModel();
        void loaditem();
    }
}
