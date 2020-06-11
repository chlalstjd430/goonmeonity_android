package com.example.gunmunity.community.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gunmunity.R;
import com.example.gunmunity.community.main.CommunityMainAdapter;
import com.example.gunmunity.model.board.BoardInfo;

public class CommunityDetailActivity extends AppCompatActivity {
    private CommunityDetailPresenter mPresenter;
    private CommunityCommentAdapter commentAdapter;
    private RecyclerView commentList;
    private BoardInfo boardInfo;
    private TextView author;
    private TextView time;
    private TextView title;
    private TextView content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        Intent intent = getIntent();
        boardInfo = (BoardInfo) intent.getSerializableExtra("boardInfo");

        commentAdapter = new CommunityCommentAdapter();
        setBinding();
        bindArticleData();
        mPresenter.getCommentList(boardInfo.getId());

        initRecyclerView();
    }

    private void setBinding() {
        mPresenter = new CommunityDetailPresenter(this, commentAdapter);

        author = findViewById(R.id.community_detail_text_nickname);
        time = findViewById(R.id.community_detail_text_time);
        title = findViewById(R.id.community_detail_text_title);
        content = findViewById(R.id.community_detail_text_content);
        commentList = findViewById(R.id.comment_list);
    }

    public void bindArticleData() {
        author.setText(boardInfo.getAuthor());
        time.setText(boardInfo.getCreatedDate());
        title.setText(boardInfo.getTitle());
        content.setText(boardInfo.getContent());
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        commentList.setAdapter(commentAdapter);
        commentList.setLayoutManager(linearLayoutManager);
    }
}
