package com.example.gunmunity.model.comment;

import com.example.gunmunity.model.entity.CommentInfo;

public class CreateCommentResponse {
    private CommentInfo commentInfo;

    public void setCommentInfo(CommentInfo commentInfo) {
        this.commentInfo = commentInfo;
    }

    public CommentInfo getCommentInfo() {
        return commentInfo;
    }
}
