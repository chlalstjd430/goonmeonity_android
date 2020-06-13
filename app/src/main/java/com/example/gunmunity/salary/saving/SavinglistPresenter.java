package com.example.gunmunity.salary.saving;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.gunmunity.model.ConstValue;
import com.example.gunmunity.model.saving.SavingListResponse;
import com.example.gunmunity.network.RetrofitUtil;
import com.example.gunmunity.network.SavingService;
import com.example.gunmunity.util.SharedPreferenceUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavinglistPresenter implements SavinglistContract.Presenter {
    SavinglistContract.View view;
    List<SavinglistModel> modelList;
    SavingService savingservice;
    Context mActivity;
    private SharedPreferenceUtil mPref;


    public SavinglistPresenter(){
        this.savingservice = RetrofitUtil.getRetrofit().create(SavingService.class);
    }

    @Override
    public void setView(SavinglistContract.View view) {
        this.view = view;
        mActivity = ((Fragment)view).getContext();
    }

    @Override
    public void createModel() {
        modelList = new ArrayList<SavinglistModel>();
    }

    @Override
    public int loaditem() {
        createModel();
        try {
            if(load_data() < 0)
                return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    private int load_data() throws IOException {
        FileInputStream fis = null;
        BufferedReader buf = null;
        int i;
        List<String> filelist = null;
        String str;
        filelist = FileList("saving");
        File directory = ((Fragment)view).getContext().getFileStreamPath("saving");

        for(i = 0;i<filelist.size();i++){
            fis = new FileInputStream(directory.getAbsolutePath() +"/"+i+".dat");
            buf = new BufferedReader(new InputStreamReader(fis));
            SavinglistModel model = new SavinglistModel();
            if((str = buf.readLine()) == null){
                continue;
            }

            model.setName(str);
            if((str = buf.readLine()) == null){
                continue;
            }

            model.setMoney(str);
            model.setCode(i);
            modelList.add(model);
            buf.close();
            fis.close();
        }
        view.updateView(modelList,filelist.size());
        return 0;
    }

    public int getnamecode() throws Exception{
        String str;
        int i = 0;
        FileInputStream fis = null;
        BufferedReader buf = null;
        List<String> filelist = new ArrayList<String>();

        filelist = FileList("saving");



        for(i = 0;i<filelist.size();i++){
            String compare = i+".dat";
            if(!filelist.get(i).equals(compare))
                break;
        }

        return i;
    }

    private List<String>  FileList(String strFolderName)
    {

        File directory = ((Fragment)view).getContext().getFileStreamPath(strFolderName);
        File[] files = directory.listFiles();

        List<String> filesNameList = new ArrayList<>();

        try{
            int i =files.length;
        }catch(NullPointerException e){
            e.printStackTrace();
            directory.mkdir();
            files = directory.listFiles();

        }

        for (int i=0; i< files.length; i++) {
            filesNameList.add(files[i].getName());
        }

        return  filesNameList;
    }


}
