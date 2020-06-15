package com.example.gunmunity.salary.saving;

import java.util.List;

public interface SavinglistContract {
    interface View{
        public void updateView(List<SavinglistModel> model, int num);
    }

    interface Presenter {
        void setView(View view);
        void createModel();
        int loaditem();
        int getnamecode() throws Exception;
        int removeitem(int code);
    }
}
