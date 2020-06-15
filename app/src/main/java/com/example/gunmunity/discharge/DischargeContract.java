package com.example.gunmunity.discharge;

import java.io.IOException;

public interface DischargeContract {
    interface View{
        public void updateView(DischargeModel model);
    }

    interface Presenter {
        void setView(View view);
        void createModel();
        int cadre_loaditem();
        int loaditem();
        int saveitem(DischargeSaveData data) throws IOException;
        int cadre_saveitem(DischargeSaveData data) throws IOException;
    }
}
