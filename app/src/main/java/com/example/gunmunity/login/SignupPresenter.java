package com.example.gunmunity.login;

import android.util.Log;

import com.example.gunmunity.model.ConstValue;
import com.example.gunmunity.util.HashingUtil;
import com.example.gunmunity.util.SharedPreferenceUtil;
import com.example.gunmunity.util.SingleLiveEvent;
import com.example.gunmunity.model.auth.SignupRequest;
import com.example.gunmunity.model.auth.SignupResponse;
import com.example.gunmunity.network.AuthService;
import com.example.gunmunity.network.RetrofitUtil;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresenter {
    private SignupActivity mActivity;
    private AuthService authService;
    SharedPreferenceUtil mPref;

    SingleLiveEvent<Void> signupSuccess = new SingleLiveEvent<>();

    SignupPresenter(SignupActivity mActivity) {
        this.mActivity = mActivity;
        authService = RetrofitUtil.getRetrofit().create(AuthService.class);
    }

    public void registerAccount(String email, String password, String nickname) throws NoSuchAlgorithmException {
        String hashedPassword = HashingUtil.hashPassword(password);
        SignupRequest signupRequest = new SignupRequest(email, hashedPassword, nickname);
        authService.registerAccount(signupRequest)
                .enqueue(new Callback<SignupResponse>() {
                    @Override
                    public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                        if (response.code()==201) {
                            Log.d("SignupActivity", response.body().toString());
                            mPref.setString(mActivity, ConstValue.ACCESS_TOKEN
                                    , response.body().getAccessToken().getToken());
                            signupSuccess.call();
                        } else {
                            Log.d("SignupActivity", response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<SignupResponse> call, Throwable t) {
                        Log.d("SignupActivity", t.getLocalizedMessage());
                    }
                });
    }
}
