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

    public long getBoardCount() {
        return boardCount;
    }

    public List<BoardInfo> getBoardInfo() {
        return boardInfo;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }
}
