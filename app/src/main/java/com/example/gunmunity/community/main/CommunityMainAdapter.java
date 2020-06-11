package com.example.gunmunity.community.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;
import com.example.gunmunity.model.board.BoardInfo;

import java.util.ArrayList;
import java.util.List;

public class CommunityMainAdapter extends RecyclerView.Adapter<CommunityMainAdapter.ViewHoler> {
    ArrayList<BoardInfo> lists = new ArrayList<>();
    CommunityMainFragment mFragment;
    Context context;

    public CommunityMainAdapter(Context context, CommunityMainFragment fragment) {
        this.context = context;
        mFragment = fragment;
    }

    public void setData(ArrayList<BoardInfo> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_community, parent, false);

        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        BoardInfo list = lists.get(position);
        holder.bindHolder(list);
    }

    @Override
    public int getItemCount() {
        return lists != null ? lists.size() : 0;
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView time;
        TextView comment;
        BoardInfo boardInfo;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            content = itemView.findViewById(R.id.item_content);
            time = itemView.findViewById(R.id.item_time);
            comment = itemView.findViewById(R.id.item_comment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFragment.startDetailActivity(boardInfo);
                }
            });
        }

        public void bindHolder(BoardInfo boardInfo) {
            this.boardInfo = boardInfo;

            title.setText(boardInfo.getTitle());
            content.setText(boardInfo.getContent());
            time.setText(boardInfo.getCreatedDate());
            comment.setText(boardInfo.getAuthor());
        }
    }
}
