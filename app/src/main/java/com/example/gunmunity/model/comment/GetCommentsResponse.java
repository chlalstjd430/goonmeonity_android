package com.example.gunmunity.model.comment;

import com.example.gunmunity.model.entity.CommentInfo;

import java.util.ArrayList;

public class GetCommentsResponse {
    private long commentsCount;
    private ArrayList<CommentInfo> commentsInfo;

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsInfo(ArrayList<CommentInfo> commentsInfo) {
        this.commentsInfo = commentsInfo;
    }

    public ArrayList<CommentInfo> getCommentsInfo() {
        return commentsInfo;
    }
}
