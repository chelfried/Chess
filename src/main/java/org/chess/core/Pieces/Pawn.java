package org.chess.core.Pieces;

import org.chess.core.GameBoard;

import java.util.Arrays;
import java.util.List;

public class Pawn {

    public static boolean[][] calcPseudo(int row, int col) {

        boolean[][] pseudoLegalMoves = GameBoard.getPseudoLegalMoves();
        byte[][] board = GameBoard.getActiveBoard();
        List<int[]> history = GameBoard.getHistory();
        GameBoard.Playing playing = GameBoard.getPlaying();

        if ((playing == GameBoard.Playing.WHITE && board[row][col] == 6) || (playing == GameBoard.Playing.BLACK && board[row][col] == -6)) {
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
                    if (((board[row][col] > 0 && board[row][col - 1] < 0) || (board[row][col] < 0 && board[row][col - 1] > 0)) && board[row - 1][col - 1] == 0) {
                        if (row == 3 && Arrays.equals(history.get(history.size() - 1), new int[]{1, col - 1, 3, col - 1})) {
                            pseudoLegalMoves[row - 1][col - 1] = true;
                            int[] enPassant = {row, col, row - 1, col - 1};
                            GameBoard.setEnPassant(enPassant);
                        }
                    }
                }
                if (col + 1 <= 7) {
                    if ((board[row][col] > 0 && board[row - 1][col + 1] < 0) || (board[row][col] < 0 && board[row - 1][col + 1] > 0)) {
                        pseudoLegalMoves[row - 1][col + 1] = true;
                    }
                    if (((board[row][col] > 0 && board[row][col + 1] < 0) || (board[row][col] < 0 && board[row][col + 1] > 0)) && board[row - 1][col + 1] == 0) {
                        if (row == 3 && Arrays.equals(history.get(history.size() - 1), new int[]{1, col + 1, 3, col + 1})) {
                            pseudoLegalMoves[row - 1][col + 1] = true;
                            int[] enPassant = {row, col, row - 1, col + 1};
                            GameBoard.setEnPassant(enPassant);
                        }
                    }
                }
            }
        } else if ((playing == GameBoard.Playing.WHITE && board[row][col] == -6) || (playing == GameBoard.Playing.BLACK && board[row][col] == 6)) {
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
                    if (((board[row][col] > 0 && board[row][col - 1] < 0) || (board[row][col] < 0 && board[row][col - 1] > 0)) && board[row + 1][col - 1] == 0) {
                        if (row == 4 && Arrays.equals(history.get(history.size() - 1), new int[]{6, col - 1, 4, col - 1})) {
                            pseudoLegalMoves[row + 1][col - 1] = true;
                            int[] enPassant = {row, col, row + 1, col - 1};
                            GameBoard.setEnPassant(enPassant);
                        }
                    }
                }
                if (col + 1 <= 7) {
                    if ((board[row][col] > 0 && board[row + 1][col + 1] < 0) || (board[row][col] < 0 && board[row + 1][col + 1] > 0)) {
                        pseudoLegalMoves[row + 1][col + 1] = true;
                    }
                    if (((board[row][col] > 0 && board[row][col + 1] < 0) || (board[row][col] < 0 && board[row][col + 1] > 0)) && board[row + 1][col + 1] == 0) {
                        if (row == 4 && Arrays.equals(history.get(history.size() - 1), new int[]{6, col + 1, 4, col + 1})) {
                            pseudoLegalMoves[row + 1][col + 1] = true;
                            int[] enPassant = {row, col, row + 1, col + 1};
                            GameBoard.setEnPassant(enPassant);
                        }
                    }
                }
            }
        }

        return pseudoLegalMoves;
    }
}

