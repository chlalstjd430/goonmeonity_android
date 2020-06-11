package com.example.gunmunity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.gunmunity.GunmunityMainActivity;
import com.example.gunmunity.R;

import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    private LoginPresenter mPresenter;
    private TextView btnSubmit;
    private TextView inputEmail;
    private TextView inputPassword;
    private TextView btnSignup;
    private boolean emailCheck = false;
    private boolean passwordCheck = false;
    private boolean buttonState = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setDataBinding();
        setListenerBinding();
        setObserveLiveData();
    }

    private void setObserveLiveData() {
        mPresenter.loginSuccess.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                startMainActivity();
            }
        });

        mPresenter.loginFailure.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                Toast.makeText(getApplicationContext(), "이메일과 패스워드를 올바르게 입력해주십시오.", Toast.LENGTH_LONG).show();
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

        inputEmail.addTextChangedListener(new correctInputListener(2));

        inputPassword.addTextChangedListener(new correctInputListener(1));

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState) {
                    startSignupActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "필수 항목들을 입력해주십시오.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setButtonColor() {
        if (emailCheck == true && passwordCheck == true){
            btnSubmit.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnSubmit.setTextColor(getResources().getColor(R.color.colorBlack));
        } else {
            btnSubmit.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
            btnSubmit.setTextColor(getResources().getColor(R.color.colorDimGrey));
        }
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

    class correctInputListener implements TextWatcher {
        private int state = 0;

        public correctInputListener(int state) {
            this.state = state;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            switch (state) {
                case 1 :
                    if (s.length()!=0) {
                        emailCheck = true;
                    } else {
                        emailCheck = false;
                    }

                    setButtonColor();
                    buttonState = true;
                    break;
                case 2 :
                    if (s.length()!=0) {
                        passwordCheck = true;
                    } else {
                        passwordCheck = false;
                    }

                    setButtonColor();
                    buttonState = true;
                    break;
            }
        }
    }
}
