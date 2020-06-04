package com.example.gunmunity.model.board;

public class BoardInfo {
    private String author;
    private BoardCategory boardCategory;
    private String content;
    private String createdDate;
    private long id;
    private String modifiedDate;
    private String title;

    public BoardInfo(String author, BoardCategory boardCategory,
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

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public BoardCategory getBoardCategory() {
        return boardCategory;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public long getId() {
        return id;
    }
}
