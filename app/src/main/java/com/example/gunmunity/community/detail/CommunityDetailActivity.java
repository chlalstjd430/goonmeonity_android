package com.example.gunmunity.community.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
    private TextView btnSubmit;
    private EditText commentContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        Intent intent = getIntent();
        boardInfo = (BoardInfo) intent.getSerializableExtra("boardInfo");

        commentAdapter = new CommunityCommentAdapter();
        setBinding();
        bindArticleData();
        setObserveLiveData();

        mPresenter.getCommentList(boardInfo.getId());

        initRecyclerView();
        setListenerBinding();
    }

    private void setObserveLiveData() {
        mPresenter.finishCommentRegister.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                Toast.makeText(getApplicationContext(), "작성을 완료했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setListenerBinding() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = commentContent.getText().toString();
                if (content!=null) {
                    mPresenter.postComment(boardInfo.getId(), content);
                }
            }
        });
    }

    private void setBinding() {
        mPresenter = new CommunityDetailPresenter(this, commentAdapter);

        author = findViewById(R.id.community_detail_text_nickname);
        time = findViewById(R.id.community_detail_text_time);
        title = findViewById(R.id.community_detail_text_title);
        content = findViewById(R.id.community_detail_text_content);
        commentList = findViewById(R.id.comment_list);
        btnSubmit = findViewById(R.id.btn_submit_comment);
        commentContent = findViewById(R.id.et_comment);
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

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
        mPresenter.getCommentList(boardInfo.getId());
    }
}
