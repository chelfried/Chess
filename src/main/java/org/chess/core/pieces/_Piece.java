package org.chess.core.pieces;

public class _Piece {

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

}
