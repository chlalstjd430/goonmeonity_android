package com.example.gunmunity.community.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.databinding.ItemCommunityBinding;
import com.example.gunmunity.model.board.BoardInfo;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CommunityMainAdapter extends RecyclerView.Adapter<CommunityMainAdapter.ViewHolder> {
    ArrayList<BoardInfo> lists = new ArrayList<>();
    CommunityMainFragment mFragment;
    ItemCommunityBinding binding;

    public CommunityMainAdapter(CommunityMainFragment fragment) {
        mFragment = fragment;
    }

    public void setData(ArrayList<BoardInfo> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCommunityBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BoardInfo list = lists.get(position);
        holder.bindHolder(list);
    }

    @Override
    public int getItemCount() {
        return lists != null ? lists.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
    ItemCommunityBinding binding;

        public ViewHolder(ItemCommunityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindHolder(final BoardInfo boardInfo) {
            binding.setBoardInfo(boardInfo);
            ArrayList<String> tokenedStr = tokenCreatedDate(boardInfo.getCreatedDate());
            binding.itemTime.setText(tokenedStr.get(0) + "\n" + tokenedStr.get(1));
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFragment.startDetailActivity(boardInfo);
                }
            });
        }
      
        private ArrayList<String> tokenCreatedDate(String time) {
            StringTokenizer stringTokenizer = new StringTokenizer(time, "T");
            ArrayList<String> tokendStr = new ArrayList<>();
            tokendStr.add(stringTokenizer.nextToken());
            tokendStr.add(stringTokenizer.nextToken());

            return tokendStr;
        }
    }
}
