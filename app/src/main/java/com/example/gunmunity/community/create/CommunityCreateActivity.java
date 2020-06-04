package com.example.gunmunity.community.create;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gunmunity.R;

public class CommunityCreateActivity extends AppCompatActivity {
    private CommunityCreatePresenter mPresenter;
    private Spinner categorySpinner;
    private EditText textTitle;
    private EditText textContent;
    private String category;
    private TextView buttonSubmit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_create);

        mPresenter = new CommunityCreatePresenter(this);

        setDataBinding();
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitArticle();
            }
        });
    }

    private void setDataBinding() {
        categorySpinner = findViewById(R.id.create_category);
        textTitle = findViewById(R.id.create_title);
        textContent = findViewById(R.id.create_content);
        buttonSubmit = findViewById(R.id.create_submit);
    }

    private void submitArticle() {
        mPresenter.createBoardRequest.setBoardCategory(category);
        mPresenter.createBoardRequest.setTitle(textTitle.getText().toString());
        mPresenter.createBoardRequest.setContent(textContent.getText().toString());
        mPresenter.postArticle();
    }
}
