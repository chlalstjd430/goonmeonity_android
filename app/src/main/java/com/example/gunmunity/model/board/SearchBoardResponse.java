package com.example.gunmunity.model.board;

import java.util.List;

public class SearchBoardResponse {
    private long boardCount;
    private List<BoardInfo> boardInfo;
    private int currentPage;
    private int totalPage;

    public SearchBoardResponse(long boardCount, List<BoardInfo> boardInfo,
                               int currentPage, int totalPage) {
        this.boardCount = boardCount;
        this.boardInfo = boardInfo;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public void setBoardCount(long boardCount) {
        this.boardCount = boardCount;
    }

    public long getBoardCount() {
        return boardCount;
    }

    public void setBoardInfo(List<BoardInfo> boardInfo) {
        this.boardInfo = boardInfo;
    }

    public List<BoardInfo> getBoardInfo() {
        return boardInfo;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }
}
