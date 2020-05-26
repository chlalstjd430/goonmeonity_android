package com.example.gunmunity.model;

public class CommunityList {
    private String title;
    private String content;
    private String time;
    private String nickname;
    private String comment;

    public CommunityList() {
        title = "포상휴가 어떻게 받아요?";
        content = "코로나 때문에 휴가 못나가고 있는데 포상휴가 받고 싶다 포상휴가 받는 방법 좀";
        time = "2020.5.12 16:00";
        nickname = "무적해병";
        comment = "2";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
