package com.example.gunmunity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.gunmunity.GunmunityMainActivity;
import com.example.gunmunity.R;

import java.security.NoSuchAlgorithmException;

public class SignupActivity extends AppCompatActivity {
    private EditText textEmail;
    private EditText textPassword;
    private EditText textNickname;
    private TextView buttonSubmit;
    private SignupPresenter mPresenter;
    private boolean emailCheck = false;
    private boolean passwordCheck = false;
    private boolean nicknameCheck = false;
    private boolean buttonState = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setViewBinding();
        setListenerBinding();
        setObserverLiveData();
        checkCorrectInput();
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

    private void checkCorrectInput() {
        textEmail.addTextChangedListener(new correctInputListener(1));
        textPassword.addTextChangedListener(new correctInputListener(2));
        textNickname.addTextChangedListener(new correctInputListener(3));
    }

    private void setListenerBinding() {
        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    if (buttonState) {
                        mPresenter.registerAccount(textEmail.getText().toString(),
                                textPassword.getText().toString(), textNickname.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "필수 항목들을 입력해주십시오.", Toast.LENGTH_LONG).show();
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class correctInputListener implements TextWatcher {
        private int state = 0;

        public correctInputListener(int state) {
            this.state = state;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            switch (state) {
                case 1 :
                    String emailRegExp = "^[a-zA-Z0-9._%^-]{4,12}+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
                    if (s.toString().matches(emailRegExp)){
                        emailCheck = true;
                    }
                    break;
                case 2 :
                    String passwordRegExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[0-9]).{8,20}$";
                    if (s.toString().matches(passwordRegExp)){
                        passwordCheck = true;
                    }
                    break;
                case 3 :
                    String nicknameRegExp = "^[a-zA-Z0-9가-힣].{2,16}$";
                    if (s.toString().matches(nicknameRegExp)){
                        nicknameCheck = true;
                    }
                    break;
            }

            if (emailCheck == true && passwordCheck == true && nicknameCheck == true) {
                buttonState = true;
                buttonSubmit.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                buttonSubmit.setTextColor(getResources().getColor(R.color.colorWhite));
            } else {
                buttonState = false;
                buttonSubmit.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                buttonSubmit.setTextColor(getResources().getColor(R.color.colorDimGrey));
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }
    }
}
