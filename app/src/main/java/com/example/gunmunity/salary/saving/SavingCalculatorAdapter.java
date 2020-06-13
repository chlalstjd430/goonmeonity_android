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
        this.list.clear();
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
        SavingCurModel savingCurModel = list.get(list.size() -position-1);
        holder.payment_date.setText(savingCurModel.getDate());
        holder.payment_allmoney.setText(savingCurModel.getAllMoney());
        holder.payment_money.setText(savingCurModel.getCurrentMoney());
        holder.phase.setText(String.format("%d",list.size()-position) +"회차");
        holder.phase_text.setText(savingCurModel.getDate().substring(0,7)+" 월분");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        public TextView phase;
        public TextView phase_text;
        public TextView payment_date;
        public TextView payment_money;
        public TextView payment_allmoney;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            phase = itemView.findViewById(R.id.phase);
            phase_text = itemView.findViewById(R.id.phase_text);
            payment_allmoney = itemView.findViewById(R.id.payment_allmoney);
            payment_date = itemView.findViewById(R.id.payment_date);
            payment_money = itemView.findViewById(R.id.payment_money);

        }
    }
}
