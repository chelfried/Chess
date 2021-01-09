package org.chess.core;

import java.util.ArrayList;
import java.util.List;

import static org.chess.core.GameBoard.*;
import static org.chess.core.Pieces.Piece.calcPseudo;

public class MoveGenerator {

    public static boolean[][] calcLegal(byte[][] board, int row, int col) {

        boolean[][] legalMoves = calcPseudo(board, row, col);
        boolean[][] pseudoLegalMoves = calcPseudo(board, row, col);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                byte[][] tempBoard = copyBoard(board);
                if (pseudoLegalMoves[i][j]) {
                    simulateMove(tempBoard, row, col, i, j);
                    if (verifyForCheck(tempBoard, getAttackVectors(tempBoard, tempBoard[i][j]))) {
                        legalMoves[i][j] = false;
                    }
                    if (tempBoard[i][j] == 1 || tempBoard[i][j] == -1) {
                        if (kingChecksKing(tempBoard, i, j)) {
                            legalMoves[i][j] = false;
                        }
                    }
                }
            }
        }

        return legalMoves;
    }

    public static List<Move> calcLegalMovesFor(byte[][] board, int player) {

        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (player == 0 && board[i][j] < 0) {
                    moves.addAll(convertLegalMoves(board, i, j));
                }
                if (player == 1 && board[i][j] > 0) {
                    moves.addAll(convertLegalMoves(board, i, j));
                }
            }
        }

        return moves;
    }

    public static List<Move> convertLegalMoves(byte[][] board, int fromRow, int fromCol) {

        List<Move> moves = new ArrayList<>();

        boolean[][] legalMoves = calcLegal(board, fromRow, fromCol);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (legalMoves[i][j]) {
                    moves.add(new Move(fromRow, fromCol, i, j));
                }
            }
        }

        return moves;
    }

    public static boolean kingChecksKing(byte[][] board, int row, int col) {
        if (row + 1 <= 7 && (board[row + 1][col] == 1 || board[row + 1][col] == -1)) {
            return true;
        } else if (row - 1 >= 0 && (board[row - 1][col] == 1 || board[row - 1][col] == -1)) {
            return true;
        } else if (col + 1 <= 7 && (board[row][col + 1] == 1 || board[row][col + 1] == -1)) {
            return true;
        } else if (col - 1 >= 0 && (board[row][col - 1] == 1 || board[row][col - 1] == -1)) {
            return true;
        } else if (row + 1 <= 7 && col + 1 <= 7 && (board[row + 1][col + 1] == 1 || board[row + 1][col + 1] == -1)) {
            return true;
        } else if (row + 1 <= 7 && col - 1 >= 0 && (board[row + 1][col - 1] == 1 || board[row + 1][col - 1] == -1)) {
            return true;
        } else if (row - 1 >= 0 && col + 1 <= 7 && (board[row - 1][col + 1] == 1 || board[row - 1][col + 1] == -1)) {
            return true;
        } else
            return row - 1 >= 0 && col - 1 >= 0 && (board[row - 1][col - 1] == 1 || board[row - 1][col - 1] == -1);
    }

    public static boolean[][] getAttackVectors(byte[][] board, int movedPiece) {

        boolean[][] attackVectors = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (movedPiece > 0 && board[i][j] < 0 || movedPiece < 0 && board[i][j] > 0) {
                    if (board[i][j] != -1 && board[i][j] != 1) {
                        appendPseudoMoves(calcPseudo(board, i, j), attackVectors);
                    }
                }
            }
        }

        return attackVectors;
    }

    public static boolean verifyForCheck(byte[][] board, boolean[][] attackVectors) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((board[i][j] == -1 || board[i][j] == 1) && attackVectors[i][j]) {
                    return true;
                }
            }
        }

        return false;
    }


    public static byte[][] copyBoard(byte[][] board) {

        byte[][] copiedBoard = new byte[8][8];

        for (int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, copiedBoard[i], 0, 8);
        }

        return copiedBoard;
    }

    public static boolean[][] appendPseudoMoves(boolean[][] from, boolean[][] to) {

        boolean[][] pseudoMoves = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (from[i][j]) {
                    to[i][j] = true;
                }
            }
        }

        return pseudoMoves;
    }

    static boolean canMove(byte[][] board, int i, int j) {
        boolean[][] possibleMoves = calcLegal(board, i, j);
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if (possibleMoves[k][l]) {
                    return true;
                }
            }
        }
        return false;
    }

}
