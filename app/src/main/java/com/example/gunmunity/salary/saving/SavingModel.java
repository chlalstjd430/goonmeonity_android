package com.example.gunmunity.salary.saving;

public class SavingModel {
    String newDate;
    String MaturityDate;
    String Nickname;
    String SavingNmae;
    String InterestRate;
    String RemainDate;

    //Getter
    public String getNewDate() {
        return newDate;
    }

    public String getMaturityDate() {
        return MaturityDate;
    }

    public String getNickname() {
        return Nickname;
    }

    public String getSavingNmae() {
        return SavingNmae;
    }

    public String getInterestRate() {
        return InterestRate;
    }

    public String getRemainDate() {
        return RemainDate;
    }

    //Setter
    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public void setMaturityDate(String maturityDate) {
        MaturityDate = maturityDate;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public void setSavingNmae(String savingNmae) {
        SavingNmae = savingNmae;
    }

    public void setInterestRate(String interestRate) {
        InterestRate = interestRate;
    }

    public void setRemainDate(String remainDate) {
        RemainDate = remainDate;
    }
}
