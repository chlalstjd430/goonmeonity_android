package com.example.gunmunity.model.board;

public class BoardInfo {
    private String author;
    private String boardCategory;
    private String content;
    private String createdDate;
    private long id;
    private String modifiedDate;
    private String title;

    public BoardInfo(String author, String boardCategory,
                     String content, String createdDate,
                     long id, String modifiedDate, String title) {
        this.author = author;
        this.boardCategory = boardCategory;
        this.content = content;
        this.createdDate = createdDate;
        this.id = id;
        this.modifiedDate = modifiedDate;
        this.title = title;
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

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
