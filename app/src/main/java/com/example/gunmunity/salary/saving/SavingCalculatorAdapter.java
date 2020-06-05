package com.example.gunmunity.salary.saving;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;

import java.util.ArrayList;
import java.util.List;

public class SavingCalculatorAdapter extends  RecyclerView.Adapter<SavingCalculatorAdapter.ViewHoler> {

    Context context;
    List<SavingCurModel> list;

    public SavingCalculatorAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<SavingCurModel>();
    }

    public void setData(List<SavingCurModel> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SavingCalculatorAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_saving, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavingCalculatorAdapter.ViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        public TextView phase;
        public TextView phase_text;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            //phase = itemView.findViewById(R.id.phase);
            // phase = itemView.findViewById(R.id.phase_text);
        }
    }
}
