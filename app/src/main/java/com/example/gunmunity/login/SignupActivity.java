package com.example.gunmunity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.gunmunity.GunmunityMainActivity;
import com.example.gunmunity.R;

import java.security.NoSuchAlgorithmException;

public class SignupActivity extends AppCompatActivity {
    EditText textEmail;
    EditText textPassword;
    EditText textNickname;
    TextView buttonSubmit;
    SignupPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setViewBinding();
        setListenerBinding();
        setObserverLiveData();
    }

    private void setObserverLiveData() {
        mPresenter.signupSuccess.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, GunmunityMainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setViewBinding() {
        textEmail = findViewById(R.id.signup_et_email);
        textPassword = findViewById(R.id.signup_et_password);
        textNickname = findViewById(R.id.signup_et_nickname);
        buttonSubmit = findViewById(R.id.signup_submit);

        mPresenter = new SignupPresenter(this);
    }

    private void setListenerBinding() {
        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    mPresenter.registerAccount(textEmail.getText().toString(),
                            textPassword.getText().toString(), textNickname.getText().toString());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
