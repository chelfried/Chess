package org.chess.comm;

import org.chess.core.GameBoard;

public class FieldPiece {

    public static String[][] getPieces() {

        String[][] boardString = new String[8][8];
        byte[][] gameBoard = GameBoard.getActiveBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[i][j] == -6) {
                    boardString[i][j] = "assets/img/PB.svg";
                } else if (gameBoard[i][j] == -5) {
                    boardString[i][j] = "assets/img/RB.svg";
                } else if (gameBoard[i][j] == -4) {
                    boardString[i][j] = "assets/img/NB.svg";
                } else if (gameBoard[i][j] == -3) {
                    boardString[i][j] = "assets/img/BB.svg";
                } else if (gameBoard[i][j] == -2) {
                    boardString[i][j] = "assets/img/QB.svg";
                } else if (gameBoard[i][j] == -1) {
                    boardString[i][j] = "assets/img/KB.svg";
                } else if (gameBoard[i][j] == 1) {
                    boardString[i][j] = "assets/img/KW.svg";
                } else if (gameBoard[i][j] == 2) {
                    boardString[i][j] = "assets/img/QW.svg";
                } else if (gameBoard[i][j] == 3) {
                    boardString[i][j] = "assets/img/BW.svg";
                } else if (gameBoard[i][j] == 4) {
                    boardString[i][j] = "assets/img/NW.svg";
                } else if (gameBoard[i][j] == 5) {
                    boardString[i][j] = "assets/img/RW.svg";
                } else if (gameBoard[i][j] == 6) {
                    boardString[i][j] = "assets/img/PW.svg";
                }
            }
        }

        return boardString;
    }

}
