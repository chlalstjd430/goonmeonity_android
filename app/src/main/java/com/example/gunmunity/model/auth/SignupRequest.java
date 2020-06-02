package com.example.gunmunity.model.auth;

public class SignupRequest {
    private String email;
    private String hashedPassword;
    private String nickname;

    public SignupRequest(String email, String hashedPassword, String nickname) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
