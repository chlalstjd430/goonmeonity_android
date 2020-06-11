package com.example.gunmunity.community.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;
import com.example.gunmunity.model.entity.CommentInfo;

import java.util.ArrayList;

public class CommunityCommentAdapter extends RecyclerView.Adapter<CommunityCommentAdapter.CommentViewHolder> {
    ArrayList<CommentInfo> commentInfos;

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);

        return new CommunityCommentAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentInfo commentInfo = commentInfos.get(position);
        holder.bindViewHolder(commentInfo);
    }

    @Override
    public int getItemCount() {
        return commentInfos != null ? commentInfos.size() : 0;
    }

    public void setCommentInfos(ArrayList<CommentInfo> commentInfos) {
        this.commentInfos = commentInfos;
        notifyDataSetChanged();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private CommentInfo commentInfo;
        private TextView author;
        private TextView content;
        private TextView date;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            author = itemView.findViewById(R.id.comment_author);
            content = itemView.findViewById(R.id.comment_content);
            date = itemView.findViewById(R.id.comment_created_time);
        }

        public void bindViewHolder(CommentInfo commentInfo) {
            this.commentInfo = commentInfo;

            author.setText(commentInfo.getAuthor());
            content.setText(commentInfo.getContent());
            date.setText(commentInfo.getCreatedDate());
        }
    }
}
