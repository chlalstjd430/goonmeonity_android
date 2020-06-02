package com.example.gunmunity.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.gunmunity.HashingUtil;
import com.example.gunmunity.SingleLiveEvent;
import com.example.gunmunity.model.auth.SigninRequest;
import com.example.gunmunity.model.auth.SigninResponse;
import com.example.gunmunity.network.AuthService;
import com.example.gunmunity.network.RetrofitUtil;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    LoginActivity mActivity;
    MutableLiveData<Boolean> isEmailInputed = new MutableLiveData();
    MutableLiveData<Boolean> isPasswordInputed = new MutableLiveData();
    SingleLiveEvent<Void> loginSuccess = new SingleLiveEvent<>();

    private AuthService authService;

    LoginPresenter(LoginActivity mActivity) {
        this.mActivity = mActivity;
        isEmailInputed.setValue(false);
        isPasswordInputed.setValue(false);

        authService = RetrofitUtil.getRetrofit().create(AuthService.class);
    }

    public void isRegisteredUser(String email, String password) throws NoSuchAlgorithmException {
        String hashedPassword = HashingUtil.hashPassword(password);
        SigninRequest signinRequest = new SigninRequest(email, hashedPassword);
        authService.isRegisteredAccount(signinRequest)
                .enqueue(new Callback<SigninResponse>() {
            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                Log.d("LoginActivity",response.message());
                loginSuccess.call();
            }

            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                Log.d("LoginActivity",t.getLocalizedMessage());
            }
        });
    }

    public void iscorrectInputed() {
        //이메일 패스워드 정규표현식으로 검사하기
        //올바르면 이벤트 활성화
    }
}
