package com.example.gunmunity.community.create;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

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
        setObserveLiveData();
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0 :
                        category = "FREE";
                        break;
                    case 1 :
                        category = "COUNSEL";
                        break;
                    case 2 :
                        category = "INFORMATION";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitArticle();
            }
        });
    }

    private void setObserveLiveData() {
        mPresenter.successCreateCall.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                Toast mToast = Toast.makeText(getApplicationContext(), "작성을 완료했습니다.", Toast.LENGTH_LONG);
                mToast.show();

                onBackPressed();
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
