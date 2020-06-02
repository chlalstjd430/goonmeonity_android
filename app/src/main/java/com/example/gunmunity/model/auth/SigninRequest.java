package com.example.gunmunity.model.auth;

public class SigninRequest {
    private String email;
    private String hashedPassword;

    public SigninRequest(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
