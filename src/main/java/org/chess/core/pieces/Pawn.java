package org.chess.core.pieces;

import static org.chess.core.GameBoard.*;

public class Pawn extends Piece {

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];
        int human = getHuman();

        if ((human == 1 && board[row][col] == 6) || (human == 0 && board[row][col] == -6)) {
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
                    if (board[8][6] == row - 1 && board[8][7] == col - 1) {
                        pseudoLegalMoves[row - 1][col - 1] = true;
                    }
                }
                if (col + 1 <= 7) {
                    if ((board[row][col] > 0 && board[row - 1][col + 1] < 0) || (board[row][col] < 0 && board[row - 1][col + 1] > 0)) {
                        pseudoLegalMoves[row - 1][col + 1] = true;
                    }
                    if (board[8][6] == row - 1 && board[8][7] == col + 1) {
                        pseudoLegalMoves[row - 1][col + 1] = true;
                    }
                }
            }
        } else if ((human == 1 && board[row][col] == -6) || (human == 0 && board[row][col] == 6)) {
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
                    if (board[8][6] == row + 1 && board[8][7] == col - 1) {
                        pseudoLegalMoves[row + 1][col - 1] = true;
                    }
                }
                if (col + 1 <= 7) {
                    if ((board[row][col] > 0 && board[row + 1][col + 1] < 0) || (board[row][col] < 0 && board[row + 1][col + 1] > 0)) {
                        pseudoLegalMoves[row + 1][col + 1] = true;
                    }
                    if (board[8][6] == row + 1 && board[8][7] == col + 1) {
                        pseudoLegalMoves[row + 1][col + 1] = true;
                    }
                }
            }
        }

        return pseudoLegalMoves;
    }

    public static void updateEnPassant(byte[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        if (Math.abs(board[toRow][toCol]) == 6) {
            if (fromRow == 1 && toRow == 3) {
                board[8][6] = (byte) (toRow - 1);
                board[8][7] = (byte) toCol;
            } else if (fromRow == 6 && toRow == 4) {
                board[8][6] = (byte) (toRow + 1);
                board[8][7] = (byte) toCol;
            }
        } else {
            board[8][6] = 0;
            board[8][7] = 0;
        }
    }

    public static void checkForEnPassant(byte[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        Pawn.updateEnPassant(board, fromRow, fromCol, toRow, toCol);
        if (Math.abs(board[toRow][toCol]) == 6 && board[8][6] == toRow && board[8][7] == toCol) {
            if (toRow == 2) {
                board[toRow + 1][toCol] = 0;
            } else if (toRow == 5) {
                board[toRow - 1][toCol] = 0;
            }
        }
    }

    public static void checkForPromotion(byte[][] board) {
        for (int i = 0; i < 8; i++) {
            if (board[0][i] == 6 || board[7][i] == 6) {
                if (board == getActiveBoard() && getHuman() == 1) {
                    setWhitePromoting(true);
                } else if (board == getActiveBoard() && getAI() == 1) {
                    setWhitePromoting(true);
                    promote(2);
                    turn = 1 - turn;
                }
            }
            if (board[0][i] == -6 || board[7][i] == -6) {
                if (board == getActiveBoard() && getHuman() == 0) {
                    setBlackPromoting(true);
                } else if (board == getActiveBoard() && getAI() == 0) {
                    setBlackPromoting(true);
                    promote(-2);
                    turn = 1 - turn;
                }
            }
        }
    }

}

