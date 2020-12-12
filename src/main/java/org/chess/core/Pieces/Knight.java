package org.chess.core.Pieces;

import org.chess.core.GameBoard;

public class Knight {

    public static boolean[][] calcPseudo(int row, int col) {

        boolean[][] pseudoLegalMoves = GameBoard.getPseudoLegalMoves();
        byte[][] board = GameBoard.getActiveBoard();

        if (row + 2 <= 7 && col - 1 >= 0) {
            if (board[row + 2][col - 1] == 0 ||
                    (board[row][col] > 0 && board[row + 2][col - 1] < 0) ||
                    (board[row][col] < 0 && board[row + 2][col - 1] > 0)) {
                pseudoLegalMoves[row + 2][col - 1] = true;
            }
        }

        if (row + 2 <= 7 && col + 1 <= 7) {
            if (board[row + 2][col + 1] == 0 ||
                    (board[row][col] > 0 && board[row + 2][col + 1] < 0) ||
                    (board[row][col] < 0 && board[row + 2][col + 1] > 0)) {
                pseudoLegalMoves[row + 2][col + 1] = true;
            }
        }

        if (row - 2 >= 0 && col + 1 <= 7) {
            if (board[row - 2][col + 1] == 0 ||
                    (board[row][col] > 0 && board[row - 2][col + 1] < 0) ||
                    (board[row][col] < 0 && board[row - 2][col + 1] > 0)) {
                pseudoLegalMoves[row - 2][col + 1] = true;
            }
        }

        if (row - 2 >= 0 && col - 1 >= 0) {
            if (board[row - 2][col - 1] == 0 ||
                    (board[row][col] > 0 && board[row - 2][col - 1] < 0) ||
                    (board[row][col] < 0 && board[row - 2][col - 1] > 0)) {
                pseudoLegalMoves[row - 2][col - 1] = true;
            }
        }

        if (row + 1 <= 7 && col - 2 >= 0) {
            if (board[row + 1][col - 2] == 0 ||
                    (board[row][col] > 0 && board[row + 1][col - 2] < 0) ||
                    (board[row][col] < 0 && board[row + 1][col - 2] > 0)) {
                pseudoLegalMoves[row + 1][col - 2] = true;
            }
        }

        if (row + 1 <= 7 && col + 2 <= 7) {
            if (board[row + 1][col + 2] == 0 ||
                    (board[row][col] > 0 && board[row + 1][col + 2] < 0) ||
                    (board[row][col] < 0 && board[row + 1][col + 2] > 0)) {
                pseudoLegalMoves[row + 1][col + 2] = true;
            }
        }

        if (row - 1 >= 0 && col + 2 <= 7) {
            if (board[row - 1][col + 2] == 0 ||
                    (board[row][col] > 0 && board[row - 1][col + 2] < 0) ||
                    (board[row][col] < 0 && board[row - 1][col + 2] > 0)) {
                pseudoLegalMoves[row - 1][col + 2] = true;
            }
        }

        if (row - 1 >= 0 && col - 2 >= 0) {
            if (board[row - 1][col - 2] == 0 ||
                    (board[row][col] > 0 && board[row - 1][col - 2] < 0) ||
                    (board[row][col] < 0 && board[row - 1][col - 2] > 0)) {
                pseudoLegalMoves[row - 1][col - 2] = true;
            }
        }

        return pseudoLegalMoves;
    }

}
