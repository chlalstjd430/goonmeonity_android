package com.example.gunmunity.login;

public class LoginPresenter {
    LoginActivity mActivity;

    LoginPresenter(LoginActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void checkAllInputed() {
        //이메일 패스워드가 다 입력되있는지 확인
    }

    public void iscorrectInputed() {
        //이메일 패스워드 정규표현식으로 검사하기
        //올바르면 이벤트 활성화
    }
}
