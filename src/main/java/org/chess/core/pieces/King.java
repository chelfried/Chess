package org.chess.core.pieces;

import org.chess.core.GameBoard;

import static org.chess.core.MoveGenerator.getAttackVectors;

public class King extends _Piece {

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

        if (GameBoard.getHuman() == 1) {
            if (row == 0 && col == 4 && board[row][col] == -1) {
                if (board[8][2] == 0 && board[0][1] == 0 && board[0][2] == 0 && board[0][3] == 0) { // queen side castling black, human playing white
                    boolean[][] attackVectors = getAttackVectors(board, -1);
                    if (!attackVectors[0][2] && !attackVectors[0][3] && !attackVectors[0][4]) {
                        pseudoLegalMoves[0][2] = true;
                    }
                }
                if (board[8][3] == 0 && board[0][5] == 0 && board[0][6] == 0) { // king side castling black, human playing white
                    boolean[][] attackVectors = getAttackVectors(board, -1);
                    if (!attackVectors[0][4] && !attackVectors[0][5] && !attackVectors[0][6]) {
                        pseudoLegalMoves[0][6] = true;
                    }
                }
            } else if (row == 7 && col == 4 && board[row][col] == 1) {
                if (board[8][4] == 0 && board[7][1] == 0 && board[7][2] == 0 && board[7][3] == 0) { // queen side castling white, human playing white
                    boolean[][] attackVectors = getAttackVectors(board, 1);
                    if (!attackVectors[7][2] && !attackVectors[7][3] && !attackVectors[7][4]) {
                        pseudoLegalMoves[7][2] = true;
                    }
                }
                if (board[8][5] == 0 && board[7][5] == 0 && board[7][6] == 0) { // king side castling white, human playing white
                    boolean[][] attackVectors = getAttackVectors(board, 1);
                    if (!attackVectors[7][4] && !attackVectors[7][5] && !attackVectors[7][6]) {
                        pseudoLegalMoves[7][6] = true;
                    }
                }
            }
        } else {
            if (row == 0 && col == 3 && board[row][col] == 1) {
                if (board[8][2] == 0 && board[0][1] == 0 && board[0][2] == 0) { // king side castling white, human playing black
                    boolean[][] attackVectors = getAttackVectors(board, 1);
                    if (!attackVectors[0][1] && !attackVectors[0][2] && !attackVectors[0][3]) {
                        pseudoLegalMoves[0][1] = true;
                    }
                }
                if (board[8][3] == 0 && board[0][4] == 0 && board[0][5] == 0 && board[0][6] == 0) { // queen side castling white, human playing black
                    boolean[][] attackVectors = getAttackVectors(board, 1);
                    if (!attackVectors[0][3] && !attackVectors[0][4] && !attackVectors[0][5]) {
                        pseudoLegalMoves[0][5] = true;
                    }
                }
            } else if (row == 7 && col == 3 && board[row][col] == -1) {
                if (board[8][4] == 0 && board[7][1] == 0 && board[7][2] == 0) { // king side castling black, human playing black
                    boolean[][] attackVectors = getAttackVectors(board, -1);
                    if (!attackVectors[7][1] && !attackVectors[7][2] && !attackVectors[7][3]) {
                        pseudoLegalMoves[7][1] = true;
                    }
                }
                if (board[8][5] == 0 && board[7][4] == 0 && board[7][5] == 0 && board[7][6] == 0) { // queen side castling black, human playing black
                    boolean[][] attackVectors = getAttackVectors(board, -1);
                    if (!attackVectors[7][3] && !attackVectors[7][4] && !attackVectors[7][5]) {
                        pseudoLegalMoves[7][5] = true;
                    }
                }
            }
        }

        return pseudoLegalMoves;
    }

    public static void updateCastlingHistory(byte[][] board, int fromRow, int fromCol, int toRow, int toCol) {

        if (Math.abs(board[toRow][toCol]) == 1 || Math.abs(board[toRow][toCol]) == 5) {
            if (GameBoard.getHuman() == 1) {
                if (board[8][2] == 0) { // queen side castling black, human playing white
                    if ((fromRow == 0 && fromCol == 0 && board[toRow][toCol] == -5) || (fromRow == 0 && fromCol == 4 && board[toRow][toCol] == -1)) {
                        board[8][2] = 1;
                    }
                }
                if (board[8][3] == 0) { // king side castling black, human playing white
                    if ((fromRow == 0 && fromCol == 7 && board[toRow][toCol] == -5) || (fromRow == 0 && fromCol == 4 && board[toRow][toCol] == -1)) {
                        board[8][3] = 1;
                    }
                }
                if (board[8][4] == 0) { // queen side castling white, human playing white
                    if ((fromRow == 7 && fromCol == 0 && board[toRow][toCol] == 5) || (fromRow == 7 && fromCol == 4 && board[toRow][toCol] == 1)) {
                        board[8][4] = 1;
                    }
                }
                if (board[8][5] == 0) { // king side castling white, human playing white
                    if ((fromRow == 7 && fromCol == 7 && board[toRow][toCol] == 5) || (fromRow == 7 && fromCol == 4 && board[toRow][toCol] == 1)) {
                        board[8][5] = 1;
                    }
                }
            } else {
                if (board[8][2] == 0) { // king side castling white, human playing black
                    if ((fromRow == 0 && fromCol == 0 && board[toRow][toCol] == 5) || (fromRow == 0 && fromCol == 3 && board[toRow][toCol] == 1)) {
                        board[8][2] = 1;
                    }
                }
                if (board[8][3] == 0) { // queen side castling white, human playing black
                    if ((fromRow == 0 && fromCol == 7 && board[toRow][toCol] == 5) || (fromRow == 0 && fromCol == 3 && board[toRow][toCol] == 1)) {
                        board[8][3] = 1;
                    }
                }
                if (board[8][4] == 0) { // king side castling black, human playing black
                    if ((fromRow == 7 && fromCol == 0 && board[toRow][toCol] == -5) || (fromRow == 0 && fromCol == 3 && board[toRow][toCol] == -1)) {
                        board[8][4] = 1;
                    }
                }
                if (board[8][5] == 0) { // queen side castling black, human playing black
                    if ((fromRow == 7 && fromCol == 7 && board[toRow][toCol] == -5) || (fromRow == 0 && fromCol == 3 && board[toRow][toCol] == -1)) {
                        board[8][5] = 1;
                    }
                }
            }
        }
    }

    public static void checkForCastling(byte[][] board, int fromRow, int fromCol, int toRow, int toCol) {

        if (Math.abs(board[toRow][toCol]) == 1) {
            if (GameBoard.getHuman() == 1) {
                if (fromRow == 0 && fromCol == 4 && toRow == 0 && toCol == 2){ // queen side castling black, human playing white
                    board[0][0] = 0;
                    board[0][3] = -5;
                }
                if (fromRow == 0 && fromCol == 4 && toRow == 0 && toCol == 6){ // king side castling black, human playing white
                    board[0][7] = 0;
                    board[0][5] = -5;
                }
                if (fromRow == 7 && fromCol == 4 && toRow == 7 && toCol == 2){ // queen side castling white, human playing white
                    board[7][0] = 0;
                    board[7][3] = 5;
                }
                if (fromRow == 7 && fromCol == 4 && toRow == 7 && toCol == 6){ // king side castling white, human playing white
                    board[7][7] = 0;
                    board[7][5] = 5;
                }
            }
            else {
                if (fromRow == 0 && fromCol == 3 && toRow == 0 && toCol == 1){ // king side castling white, human playing black
                    board[0][0] = 0;
                    board[0][2] = 5;
                }
                if (fromRow == 0 && fromCol == 3 && toRow == 0 && toCol == 5){ // queen side castling white, human playing black
                    board[0][7] = 0;
                    board[0][4] = 5;
                }
                if (fromRow == 7 && fromCol == 3 && toRow == 7 && toCol == 1){ // king side castling black, human playing black
                    board[7][0] = 0;
                    board[7][4] = -5;
                }
                if (fromRow == 7 && fromCol == 3 && toRow == 7 && toCol == 5){ // queen side castling black, human playing black
                    board[7][7] = 0;
                    board[7][4] = -5;
                }
            }
        }

        updateCastlingHistory(board, fromRow, fromCol, toRow, toCol);
    }

}


