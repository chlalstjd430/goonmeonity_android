package com.example.gunmunity.model.auth;

import com.example.gunmunity.model.entity.AccessToken;
import com.example.gunmunity.model.entity.UserInfo;

public class SignupResponse {
    private AccessToken accessToken;
    private String refreshToken;
    private UserInfo userInfo;

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
