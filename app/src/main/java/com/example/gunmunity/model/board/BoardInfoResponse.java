package com.example.gunmunity.model.board;

public class BoardInfoResponse {
    private BoardInfo boardInfo;

    BoardInfoResponse(BoardInfo boardInfo) {
        this.boardInfo = boardInfo;
    }

    public BoardInfo getBoardInfo() {
        return boardInfo;
    }
}
