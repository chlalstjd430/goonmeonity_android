package com.example.gunmunity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gunmunity.GunmunityMainActivity;
import com.example.gunmunity.R;

public class LoginActivity extends AppCompatActivity {
    LoginPresenter mPresenter;
    TextView btnSubmit;
    TextView inputEmail;
    TextView inputPassword;
    boolean isEmailInputed = false;
    boolean isPasswordInputed = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);

        setDataBinding();
        setListenerBinding();
    }

    private void setDataBinding() {
        btnSubmit = findViewById(R.id.login_submit);
        inputEmail = findViewById(R.id.login_et_email);
        inputPassword = findViewById(R.id.login_et_password);
        mPresenter = new LoginPresenter(this);
    }

    private void setListenerBinding() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.checkAllInputed();
            }
        });

        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    isEmailInputed = true;
                } else {
                    isEmailInputed = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, GunmunityMainActivity.class);
        startActivity(intent);
        finish();
    }
}
