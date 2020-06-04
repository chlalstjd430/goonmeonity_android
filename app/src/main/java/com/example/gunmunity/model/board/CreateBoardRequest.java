package com.example.gunmunity.model.board;

public class CreateBoardRequest {
    private String boardCategory;
    private String content;
    private String title;

    public CreateBoardRequest(String boardCategory, String content, String title) {
        this.boardCategory = boardCategory;
        this.content = content;
        this.title = title;
    }

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
