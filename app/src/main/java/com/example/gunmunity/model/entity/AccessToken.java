package com.example.gunmunity.model.entity;

public class AccessToken {
    private long expire;
    private String token;

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public long getExpire() {
        return expire;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
