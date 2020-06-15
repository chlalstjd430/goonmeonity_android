package com.example.gunmunity.salary.saving;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gunmunity.R;
import com.example.gunmunity.discharge.dischargeCadreFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class SavingListAdapter extends  RecyclerView.Adapter<SavingListAdapter.ViewHoler>  {
    Context context;
    Fragment view;
    private List<SavinglistModel> list = new ArrayList<SavinglistModel>();

    public SavingListAdapter(Context context, Fragment view) {
        this.context = context;
        this.view = view;
    }

    public void setData(List<SavinglistModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SavingListAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_savinglist, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavingListAdapter.ViewHoler holder, int position) {
        final SavinglistModel model = list.get(position);
        holder.savinglist_title.setText(model.getName());
        holder.list_allmoney.setText(model.getMoney());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getFragmentManager().beginTransaction().replace(R.id.main_container,new SavingCaculatorFragment(model.getCode())).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        TextView savinglist_title;
        TextView list_allmoney;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            savinglist_title = itemView.findViewById(R.id.savinglist_title);
            list_allmoney = itemView.findViewById(R.id.list_allmoney);


        }
    }
}
