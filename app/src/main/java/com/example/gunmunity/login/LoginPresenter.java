package com.example.gunmunity.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.gunmunity.model.ConstValue;
import com.example.gunmunity.util.HashingUtil;
import com.example.gunmunity.util.SharedPreferenceUtil;
import com.example.gunmunity.util.SingleLiveEvent;
import com.example.gunmunity.model.auth.SigninRequest;
import com.example.gunmunity.model.auth.SigninResponse;
import com.example.gunmunity.network.AuthService;
import com.example.gunmunity.network.RetrofitUtil;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginActivity mActivity;
    private SharedPreferenceUtil mPref;
    MutableLiveData<Boolean> isEmailInputed = new MutableLiveData();
    MutableLiveData<Boolean> isPasswordInputed = new MutableLiveData();
    SingleLiveEvent<Void> loginSuccess = new SingleLiveEvent<>();
    SingleLiveEvent<Void> loginFailure = new SingleLiveEvent<>();

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
                if (response.code()==200) {
                    Log.d("LoginActivity",response.body().getAccessToken().getToken());
                    mPref.setString(mActivity, ConstValue.ACCESS_TOKEN
                            , response.body().getAccessToken().getToken());
                    mPref.setString(mActivity, ConstValue.USERID
                            , response.body().getUserInfo().getId());
                    loginSuccess.call();
                } else {
                    loginFailure.call();
                    Log.d("LoginActivity",response.message());
                }
            }
            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                Log.d("LoginActivity",t.getLocalizedMessage());
                loginFailure.call();
            }
        });
    }
}
