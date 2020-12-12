package org.chess.core.Pieces;

import org.chess.core.GameBoard;

public class King extends Piece {

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];

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

        // Castling

        if (!Piece.checkForPastMove(7, 4) && !Piece.checkForPastMove(7, 7)) {
            if (board[7][5] == 0 && board[7][6] == 0) {
                if (!getAttackVectors(board, board[7][0])[7][5]) {
                    pseudoLegalMoves[7][6] = true;
                }
            }
        }

        if (!Piece.checkForPastMove(0, 4) && !Piece.checkForPastMove(0, 7)) {
            if (board[0][5] == 0 && board[0][6] == 0) {
                if (!getAttackVectors(board, board[0][0])[0][5]) {
                    pseudoLegalMoves[0][6] = true;
                }
            }
        }

        if (!Piece.checkForPastMove(7, 4) && !Piece.checkForPastMove(7, 0)) {
            if (board[7][1] == 0 && board[7][2] == 0 && board[7][3] == 0) {
                if (!getAttackVectors(board, board[7][0])[7][3]) {
                    pseudoLegalMoves[7][2] = true;
                }
            }
        }

        if (!Piece.checkForPastMove(0, 4) && !Piece.checkForPastMove(0, 0)) {
            if (board[0][1] == 0 && board[0][2] == 0 && board[0][3] == 0) {
                if (!getAttackVectors(board, board[0][0])[0][3]) {
                    pseudoLegalMoves[0][2] = true;
                }
            }
        }

        return pseudoLegalMoves;
    }

}
