package org.chess.core.Pieces;

import org.chess.core.GameBoard;

import java.util.List;

public class Piece {

    public static boolean[][] calcPseudo(int row, int col) {

        boolean[][] pseudoLegalMoves = GameBoard.getPseudoLegalMoves();
        byte[][] board = GameBoard.getActiveBoard();

        if (board[row][col] == 6 || board[row][col] == -6) {
            pseudoLegalMoves = Pawn.calcPseudo(row, col);
        } else if (board[row][col] == 5 || board[row][col] == -5) {
            pseudoLegalMoves = Rook.calcPseudo(row, col);
        } else if (board[row][col] == 4 || board[row][col] == -4) {
            pseudoLegalMoves = Knight.calcPseudo(row, col);
        } else if (board[row][col] == 3 || board[row][col] == -3) {
            pseudoLegalMoves = Bishop.calcPseudo(row, col);
        } else if (board[row][col] == 2 || board[row][col] == -2) {
            pseudoLegalMoves = Queen.calcPseudo(row, col);
        } else if (board[row][col] == 1 || board[row][col] == -1) {
            pseudoLegalMoves = King.calcPseudo(row, col);
        }

        return pseudoLegalMoves;
    }

    public static boolean[][] calcDiagonal(int row, int col) {

        boolean[][] pseudoLegalMoves = GameBoard.getPseudoLegalMoves();
        byte[][] board = GameBoard.getActiveBoard();

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

    public static boolean[][] calcCross(int row, int col) {

        boolean[][] pseudoLegalMoves = GameBoard.getPseudoLegalMoves();
        byte[][] board = GameBoard.getActiveBoard();

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

    public static boolean checkForPastMove(int row, int col){

        List<int[]> history = GameBoard.getHistory();

        for (int i = 0; i < history.size(); i++){
            if (history.get(i)[0] == row && history.get(i)[1] == col){
                return true;
            }
        }

        return false;
    }


}
