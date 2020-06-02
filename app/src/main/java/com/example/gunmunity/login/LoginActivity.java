package com.example.gunmunity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.gunmunity.GunmunityMainActivity;
import com.example.gunmunity.R;

import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    LoginPresenter mPresenter;
    TextView btnSubmit;
    TextView inputEmail;
    TextView inputPassword;
    TextView btnSignup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setDataBinding();
        setListenerBinding();
        setObserveLiveData();
    }

    private void setObserveLiveData() {
        mPresenter.isEmailInputed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (mPresenter.isEmailInputed.getValue()==true
                        && mPresenter.isPasswordInputed.getValue()==true) {
                    btnSubmit.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    btnSubmit.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        mPresenter.loginSuccess.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                startMainActivity();
            }
        });
    }

    private void setDataBinding() {
        btnSubmit = findViewById(R.id.login_submit);
        inputEmail = findViewById(R.id.login_et_email);
        inputPassword = findViewById(R.id.login_et_password);
        btnSignup = findViewById(R.id.login_question_signup);
        mPresenter = new LoginPresenter(this);
    }

    private void setListenerBinding() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPresenter.isRegisteredUser(inputEmail.getText().toString(), inputPassword.getText().toString());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    mPresenter.isEmailInputed.setValue(true);
                } else {
                    mPresenter.isEmailInputed.setValue(false);
                }
            }
        });

        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    mPresenter.isPasswordInputed.setValue(true);
                } else {
                    mPresenter.isPasswordInputed.setValue(false);
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignupActivity();
            }
        });

    }

    private void startMainActivity() {
        Intent intent = new Intent(this, GunmunityMainActivity.class);
        startActivity(intent);
        finish();
    }

    private void startSignupActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}
