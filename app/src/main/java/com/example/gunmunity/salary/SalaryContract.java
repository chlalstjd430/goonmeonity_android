package com.example.gunmunity.salary;

import java.util.List;

public interface SalaryContract {
    interface View{
        void updateView(SalaryModel model);
        void updateView(List<SalaryListModel> model);
    }

    interface Presenter {
        void setView(View view);
        void createModel();
        int loaditem();
    }
}
