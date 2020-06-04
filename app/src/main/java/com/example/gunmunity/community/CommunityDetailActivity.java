package com.example.gunmunity.community;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gunmunity.R;
import com.example.gunmunity.model.CommunityList;

public class CommunityDetailActivity extends AppCompatActivity implements CommunityDetailContract.View {
    CommunityDetailPresenter mPresenter;
    TextView nickname;
    TextView time;
    TextView title;
    TextView content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        setBinding();
    }

    private void setBinding() {
        mPresenter = new CommunityDetailPresenter(this);

        nickname = findViewById(R.id.community_detail_text_nickname);
        time = findViewById(R.id.community_detail_text_time);
        title = findViewById(R.id.community_detail_text_title);
        content = findViewById(R.id.community_detail_text_content);

        mPresenter.getArticleData();
    }

    @Override
    public void bindArticleData(CommunityList communityList) {
        nickname.setText(communityList.getNickname());
        time.setText(communityList.getTime());
        title.setText(communityList.getTitle());
        content.setText(communityList.getContent());
    }
}
