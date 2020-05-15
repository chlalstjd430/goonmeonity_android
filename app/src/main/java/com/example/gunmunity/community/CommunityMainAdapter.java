package com.example.gunmunity.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;
import com.example.gunmunity.model.CommunityListModel;

import java.util.ArrayList;
import java.util.List;

public class CommunityMainAdapter extends RecyclerView.Adapter<CommunityMainAdapter.ViewHoler> {
    ArrayList<CommunityListModel> lists = new ArrayList<>();
    Context context;

    public CommunityMainAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CommunityListModel> lists) {
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_community,parent,false);

        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        CommunityListModel list = lists.get(position);
        holder.title.setText("title : " + list.getTitle());
        holder.content.setText("content : " + list.getContent());
        holder.time.setText("time : " + list.getTime());
        holder.comment.setText("comment : " + list.getComment());
    }

    @Override
    public int getItemCount() {
        return lists !=null ? lists.size() : 0;
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView time;
        TextView comment;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            content = itemView.findViewById(R.id.item_content);
            time = itemView.findViewById(R.id.item_time);
            comment = itemView.findViewById(R.id.item_comment);
        }
    }
}
