package org.chess.core.Pieces;

public class Knight extends Piece{

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];

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
