package org.chess.core.Pieces;

import org.chess.core.GameBoard;

public class King {

    public static boolean[][] calcPseudo(int row, int col) {

        boolean[][] pseudoLegalMoves = GameBoard.getPseudoLegalMoves();
        byte[][] board = GameBoard.getActiveBoard();

        {

            if (row + 1 <= 7) {
                if (board[row + 1][col] == 0 ||
                        (board[row][col] > 0 && board[row + 1][col] < 0) ||
                        (board[row][col] < 0 && board[row + 1][col] > 0)) {
                    pseudoLegalMoves[row + 1][col] = true;
                }
            }

            if (row - 1 >= 0) {
                if (board[row - 1][col] == 0 ||
                        (board[row][col] > 0 && board[row - 1][col] < 0) ||
                        (board[row][col] < 0 && board[row - 1][col] > 0)) {
                    pseudoLegalMoves[row - 1][col] = true;
                }
            }

            if (col + 1 <= 7) {
                if (board[row][col + 1] == 0 ||
                        (board[row][col] > 0 && board[row][col + 1] < 0) ||
                        (board[row][col] < 0 && board[row][col + 1] > 0)) {
                    pseudoLegalMoves[row][col + 1] = true;
                }
            }

            if (col - 1 >= 0) {
                if (board[row][col - 1] == 0 ||
                        (board[row][col] > 0 && board[row][col - 1] < 0) ||
                        (board[row][col] < 0 && board[row][col - 1] > 0)) {
                    pseudoLegalMoves[row][col - 1] = true;
                }
            }

            if (row + 1 <= 7 && col + 1 <= 7) {
                if (board[row + 1][col + 1] == 0 ||
                        (board[row][col] > 0 && board[row + 1][col + 1] < 0) ||
                        (board[row][col] < 0 && board[row + 1][col + 1] > 0)) {
                    pseudoLegalMoves[row + 1][col + 1] = true;
                }
            }

            if (row + 1 <= 7 && col - 1 >= 0) {
                if (board[row + 1][col - 1] == 0 ||
                        (board[row][col] > 0 && board[row + 1][col - 1] < 0) ||
                        (board[row][col] < 0 && board[row + 1][col - 1] > 0)) {
                    pseudoLegalMoves[row + 1][col - 1] = true;
                }
            }

            if (row - 1 >= 0 && col + 1 <= 7) {
                if (board[row - 1][col + 1] == 0 ||
                        (board[row][col] > 0 && board[row - 1][col + 1] < 0) ||
                        (board[row][col] < 0 && board[row - 1][col + 1] > 0)) {
                    pseudoLegalMoves[row - 1][col + 1] = true;
                }
            }

            if (row - 1 >= 0 && col - 1 >= 0) {
                if (board[row - 1][col - 1] == 0 ||
                        (board[row][col] > 0 && board[row - 1][col - 1] < 0) ||
                        (board[row][col] < 0 && board[row - 1][col - 1] > 0)) {
                    pseudoLegalMoves[row - 1][col - 1] = true;
                }
            }

        }

        if (!Piece.checkForPastMove(7, 4) && !Piece.checkForPastMove(7, 7)){
            if (board[7][5] == 0 && board[7][6] == 0){
                pseudoLegalMoves[7][6] = true;
            }
        }

        if (!Piece.checkForPastMove(0, 4) && !Piece.checkForPastMove(0, 7)){
            if (board[0][5] == 0 && board[0][6] == 0){
                pseudoLegalMoves[0][6] = true;
            }
        }

        if (!Piece.checkForPastMove(7, 4) && !Piece.checkForPastMove(7, 0)){
            if (board[7][1] == 0 && board[7][2] == 0 && board[7][3] == 0){
                pseudoLegalMoves[7][2] = true;
            }
        }

        if (!Piece.checkForPastMove(0, 4) && !Piece.checkForPastMove(0, 0)){
            if (board[0][1] == 0 && board[0][2] == 0 && board[0][3] == 0){
                pseudoLegalMoves[0][2] = true;
            }
        }

        return pseudoLegalMoves;
    }

}
