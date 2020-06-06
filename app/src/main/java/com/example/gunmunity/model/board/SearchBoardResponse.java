package com.example.gunmunity.model.board;

import java.util.ArrayList;

public class SearchBoardResponse {
    private long boardsCount;
    private ArrayList<BoardInfo> boardsInfo;
    private int currentPage;
    private int totalPage;

//    public SearchBoardResponse(long boardCount, List<BoardInfo> boardInfo,
//                               int currentPage, int totalPage) {
//        this.boardCount = boardCount;
//        this.boardInfo = boardInfo;
//        this.currentPage = currentPage;
//        this.totalPage = totalPage;
//    }

    public void setBoardsCount(long boardsCount) {
        this.boardsCount = boardsCount;
    }

    public long getBoardsCount() {
        return boardsCount;
    }

    public void setBoardInfo(ArrayList<BoardInfo> boardInfo) {
        this.boardsInfo = boardInfo;
    }

    public ArrayList<BoardInfo> getBoardInfo() {
        return boardsInfo;
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
