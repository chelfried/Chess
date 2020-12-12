package org.chess.core.Pieces;

import org.chess.core.GameBoard;

import java.util.List;

public class Piece {

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];

        if (board[row][col] == 6 || board[row][col] == -6) {
            pseudoLegalMoves = Pawn.calcPseudo(board, row, col);
        } else if (board[row][col] == 5 || board[row][col] == -5) {
            pseudoLegalMoves = Rook.calcPseudo(board, row, col);
        } else if (board[row][col] == 4 || board[row][col] == -4) {
            pseudoLegalMoves = Knight.calcPseudo(board, row, col);
        } else if (board[row][col] == 3 || board[row][col] == -3) {
            pseudoLegalMoves = Bishop.calcPseudo(board, row, col);
        } else if (board[row][col] == 2 || board[row][col] == -2) {
            pseudoLegalMoves = Queen.calcPseudo(board, row, col);
        } else if (board[row][col] == 1 || board[row][col] == -1) {
            pseudoLegalMoves = King.calcPseudo(board, row, col);
        }

        return pseudoLegalMoves;
    }

    public static boolean[][] calcLegal(int row, int col) {

        boolean[][] legalMoves = calcPseudo(GameBoard.getActiveBoard(), row, col);
        boolean[][] pseudoLegalMoves = calcPseudo(GameBoard.getActiveBoard(), row, col);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                byte[][] tempBoard = copyBoard(GameBoard.getActiveBoard());
                if (pseudoLegalMoves[i][j]) {
                    GameBoard.movePiece(tempBoard, row, col, i, j);
                    if (verifyForCheck(tempBoard, getAttackVectors(tempBoard, tempBoard[i][j]))) {
                        legalMoves[i][j] = false;
                    }
                    List<int[]> history = GameBoard.getHistory();
                    history.remove(history.size() - 1);
                }
            }
        }


        return legalMoves;
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
            for (int j = 0; j < 8; j++) {
                copiedBoard[i][j] = board[i][j];
            }
        }

        return copiedBoard;

    }

    public static boolean[][] calcDiagonal(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];

        for (int i = 1; i < 8; i++) {
            if (row + i <= 7 && col + i <= 7) {
                if (board[row + i][col + i] == 0) {
                    pseudoLegalMoves[row + i][col + i] = true;
                } else if ((board[row][col] > 0 && board[row + i][col + i] < 0) ||
                        (board[row][col] < 0 && board[row + i][col + i] > 0)) {
                    pseudoLegalMoves[row + i][col + i] = true;
                    break;
                } else break;
            } else break;
        }

        for (int i = 1; i < 8; i++) {
            if (row - i >= 0 && col - i >= 0) {
                if (board[row - i][col - i] == 0) {
                    pseudoLegalMoves[row - i][col - i] = true;
                } else if ((board[row][col] > 0 && board[row - i][col - i] < 0) ||
                        (board[row][col] < 0 && board[row - i][col - i] > 0)) {
                    pseudoLegalMoves[row - i][col - i] = true;
                    break;
                } else break;
            } else break;
        }

        for (int i = 1; i < 8; i++) {
            if (row + i <= 7 && col - i >= 0) {
                if (board[row + i][col - i] == 0) {
                    pseudoLegalMoves[row + i][col - i] = true;
                } else if ((board[row][col] > 0 && board[row + i][col - i] < 0) ||
                        (board[row][col] < 0 && board[row + i][col - i] > 0)) {
                    pseudoLegalMoves[row + i][col - i] = true;
                    break;
                } else break;
            } else break;
        }

        for (int i = 1; i < 8; i++) {
            if (row - i >= 0 && col + i <= 7) {
                if (board[row - i][col + i] == 0) {
                    pseudoLegalMoves[row - i][col + i] = true;
                } else if ((board[row][col] > 0 && board[row - i][col + i] < 0) ||
                        (board[row][col] < 0 && board[row - i][col + i] > 0)) {
                    pseudoLegalMoves[row - i][col + i] = true;
                    break;
                } else break;
            } else break;
        }

        return pseudoLegalMoves;
    }

    public static boolean[][] calcCross(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];

        for (int i = 1; i < 8; i++) {
            if (row + i <= 7) {
                if (board[row + i][col] == 0) {
                    pseudoLegalMoves[row + i][col] = true;
                } else if ((board[row][col] > 0 && board[row + i][col] < 0) ||
                        (board[row][col] < 0 && board[row + i][col] > 0)) {
                    pseudoLegalMoves[row + i][col] = true;
                    break;
                } else break;
            } else break;
        }

        for (int i = 1; i < 8; i++) {
            if (row - i >= 0) {
                if (board[row - i][col] == 0) {
                    pseudoLegalMoves[row - i][col] = true;
                } else if ((board[row][col] > 0 && board[row - i][col] < 0) ||
                        (board[row][col] < 0 && board[row - i][col] > 0)) {
                    pseudoLegalMoves[row - i][col] = true;
                    break;
                } else break;
            } else break;
        }

        for (int i = 1; i < 8; i++) {
            if (col + i <= 7) {
                if (board[row][col + i] == 0) {
                    pseudoLegalMoves[row][col + i] = true;
                } else if ((board[row][col] > 0 && board[row][col + i] < 0) ||
                        (board[row][col] < 0 && board[row][col + i] > 0)) {
                    pseudoLegalMoves[row][col + i] = true;
                    break;
                } else break;
            } else break;
        }

        for (int i = 1; i < 8; i++) {
            if (col - i >= 0) {
                if (board[row][col - i] == 0) {
                    pseudoLegalMoves[row][col - i] = true;
                } else if ((board[row][col] > 0 && board[row][col - i] < 0) ||
                        (board[row][col] < 0 && board[row][col - i] > 0)) {
                    pseudoLegalMoves[row][col - i] = true;
                    break;
                } else break;
            } else break;
        }

        return pseudoLegalMoves;
    }

    public static boolean checkForPastMove(int row, int col) {

        List<int[]> history = GameBoard.getHistory();

        for (int i = 0; i < history.size(); i++) {
            if (history.get(i)[0] == row && history.get(i)[1] == col) {
                return true;
            }
        }

        return false;
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


}
