package com.example.gunmunity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.model.Result;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    private List<Result> jsonList;
    private Context context;

    public MainAdapter(Context context){
        this.context = context;
    }

    @NonNull
    public void setData(List<Result> jsonList){
        this.jsonList = new ArrayList<>();
        this.jsonList = jsonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result list = jsonList.get(position);
        holder.userId.setText("userId : "+ String.valueOf(list.getUserId()));
        holder.id.setText("id : " + String.valueOf(list.getId()));
        holder.title.setText("title : " + list.getTitle());
        holder.body.setText("body : " + list.getBody());
    }

    @Override
    public int getItemCount() {
        return jsonList!=null ? jsonList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView userId;
        TextView id;
        TextView title;
        TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userId = itemView.findViewById(R.id.userId);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
