package org.chess.core.Pieces;

import static org.chess.core.GameBoard.*;

public class Pawn extends Piece{

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];
        int playing = getHuman();

        if ((playing == 1 && board[row][col] == 6) || (playing == 0 && board[row][col] == -6)) {
            if (row - 1 >= 0) {
                if (board[row - 1][col] == 0) {
                    pseudoLegalMoves[row - 1][col] = true;
                    if (row == 6 && board[row - 2][col] == 0) {
                        pseudoLegalMoves[row - 2][col] = true;
                    }
                }
                if (col - 1 >= 0) {
                    if ((board[row][col] > 0 && board[row - 1][col - 1] < 0) || (board[row][col] < 0 && board[row - 1][col - 1] > 0)) {
                        pseudoLegalMoves[row - 1][col - 1] = true;
                    }
                }
                if (col + 1 <= 7) {
                    if ((board[row][col] > 0 && board[row - 1][col + 1] < 0) || (board[row][col] < 0 && board[row - 1][col + 1] > 0)) {
                        pseudoLegalMoves[row - 1][col + 1] = true;
                    }
                }
            }
        } else if ((playing == 1 && board[row][col] == -6) || (playing == 0 && board[row][col] == 6)) {
            if (row + 1 <= 7) {
                if (board[row + 1][col] == 0) {
                    pseudoLegalMoves[row + 1][col] = true;
                    if (row == 1 && board[row + 2][col] == 0) {
                        pseudoLegalMoves[row + 2][col] = true;
                    }
                }
                if (col - 1 >= 0) {
                    if ((board[row][col] > 0 && board[row + 1][col - 1] < 0) || (board[row][col] < 0 && board[row + 1][col - 1] > 0)) {
                        pseudoLegalMoves[row + 1][col - 1] = true;
                    }
                }
                if (col + 1 <= 7) {
                    if ((board[row][col] > 0 && board[row + 1][col + 1] < 0) || (board[row][col] < 0 && board[row + 1][col + 1] > 0)) {
                        pseudoLegalMoves[row + 1][col + 1] = true;
                    }
                }
            }
        }

        return pseudoLegalMoves;
    }
}

