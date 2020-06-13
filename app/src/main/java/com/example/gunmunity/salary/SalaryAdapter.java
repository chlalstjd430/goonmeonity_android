package com.example.gunmunity.salary;

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

public class SalaryAdapter extends  RecyclerView.Adapter<SalaryAdapter.ViewHoler> {

    private final Context context;
    private List<SalaryListModel> list = new ArrayList<SalaryListModel>();

    public SalaryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SalaryListModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SalaryAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_salary, parent, false);
        return new SalaryAdapter.ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryAdapter.ViewHoler holder, int position) {
        SalaryListModel salarycurmodel = list.get(list.size() -position-1);
        holder.payment_date.setText(salarycurmodel.getPayment_date());
        holder.payment_allmoney.setText(salarycurmodel.getPayment_allmoney());
        holder.payment_money.setText(salarycurmodel.getPayment_money());
        holder.phase.setText(String.format("%d",list.size()-position) +"회차");
        holder.phase_text.setText(salarycurmodel.getPhase_text().substring(0,7)+" 월분");
        holder.curclass.setText(salarycurmodel.getCurclass());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        TextView phase;
        TextView phase_text;
        TextView curclass;
        TextView payment_date;
        TextView payment_money;
        TextView payment_allmoney;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            phase = itemView.findViewById(R.id.phase);
            phase_text = itemView.findViewById(R.id.phase_text);
            curclass = itemView.findViewById(R.id.curclass);
            payment_date = itemView.findViewById(R.id.payment_date);
            payment_money = itemView.findViewById(R.id.payment_money);
            payment_allmoney = itemView.findViewById(R.id.payment_allmoney);
        }
    }
}
