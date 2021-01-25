package org.chess.core;

import org.chess.controller.SSEController;

import java.util.Arrays;

import static org.chess.core.AlphaBeta.*;
import static org.chess.core.Castling.resetCastlingHistory;
import static org.chess.core.Castling.updateCastlingHistory;
import static org.chess.core.MoveGenerator.*;

public class GameBoard {

    private static byte[][] activeBoard = new byte[8][8];

    private static boolean[][] legalMoves = new boolean[8][8];

    private static boolean gameStarted;

    private static Integer selectedRow;
    private static Integer selectedCol;

    private static int human;
    private static int ai;

    public static int turn;

    private static boolean whitePromoting;
    private static boolean blackPromoting;

    private static boolean whiteKingChecked;
    private static boolean blackKingChecked;

    private static boolean isAIThinking;

    private static String history = "#";

    public static void initializeBoard() {
        if (human == 1) {
            activeBoard = (new byte[][]{
                    {-5, -4, -3, -2, -1, -3, -4, -5},
                    {-6, -6, -6, -6, -6, -6, -6, -6},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 6, 6, 6, 6, 6, 6, 6},
                    {5, 4, 3, 2, 1, 3, 4, 5},
            });
        } else if (human == 0) {
            activeBoard = (new byte[][]{
                    {5, 4, 3, 2, 1, 3, 4, 5},
                    {6, 6, 6, 6, 6, 6, 6, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {-6, -6, -6, -6, -6, -6, -6, -6},
                    {-5, -4, -3, -2, -1, -3, -4, -5}
            });
        }
        selectedRow = null;
        selectedCol = null;
        turn = 1;
    }

    private static void resetBoard() {
        activeBoard = new byte[8][8];
    }

    public static byte[][] getActiveBoard() {
        return activeBoard;
    }

    public static void makeSelection(int row, int col) {
        if ((!whitePromoting || !blackPromoting) && col >= 0 && col < 8 && row >= 0 && row < 8 && !checkForMate(activeBoard)) {
            if (selectedRow == null && selectedCol == null) {
                if (activeBoard[row][col] != 0) {
                    if ((human == 1 && turn == 1 && activeBoard[row][col] > 0) || (human == 0 && turn == 0 && activeBoard[row][col] < 0)) {
                        legalMoves = calcLegal(activeBoard, row, col);
                        if (checkIfNoLegalMove(legalMoves)) {
                            resetSelection();
                        } else {
                            selectedRow = row;
                            selectedCol = col;
                        }
                    }
                }
            } else if (selectedRow != null && selectedCol != null) {
                if (selectedRow == row && selectedCol == col) {
                    resetSelection();
                    resetLegalMoves();
                } else if ((activeBoard[selectedRow][selectedCol] > 0 && activeBoard[row][col] > 0) ||
                        (activeBoard[selectedRow][selectedCol] < 0 && activeBoard[row][col] < 0)) {
                    if (!checkIfNoLegalMove(calcLegal(activeBoard, row, col))) {
                        selectedRow = row;
                        selectedCol = col;
                        legalMoves = calcLegal(activeBoard, row, col);
                    }
                } else {
                    checkMove(selectedRow, selectedCol, row, col);
                }

            }
        }

    }

    private static boolean checkIfNoLegalMove(boolean[][] legalMoves) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (legalMoves[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void resetLegalMoves() {
        legalMoves = new boolean[8][8];
    }

    private static void checkMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (legalMoves[toRow][toCol]) {
            movePiece(activeBoard, fromRow, fromCol, toRow, toCol);
            resetSelection();
            if (!whitePromoting && !blackPromoting) {
                turn = 1 - turn;
            }
            resetLegalMoves();
            moveByAI();
        }
    }

    public static void moveByAI() {
        isAIThinking = true;
        SSEController.refreshPage();
        if (turn == 1 && ai == 1 && history.equals("+")) {
            wait(3000);
            movePiece(activeBoard, 1, 3, 3, 3);
            turn = 0;
        }
        if ((turn == 0 && ai == 0) || (turn == 1 && ai == 1)) {
            Move move = OpeningBook.findMove();
            if (move != null) {
                wait(2000);
                movePiece(activeBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);
            } else {
                resetAlphaBetaBoard();
                alphaBetaMax(deepCopyBoard(activeBoard), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
                movePiece(activeBoard, bestMove.fromRow, bestMove.fromCol, bestMove.toRow, bestMove.toCol);
            }
            turn = 1 - turn;
        }
        isAIThinking = false;
        SSEController.refreshPage();
    }

    public static void movePiece(byte[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        history = history.concat(String.valueOf(fromRow) + fromCol + toRow + toCol);
        byte piece = board[fromRow][fromCol];
        board[fromRow][fromCol] = 0;
        board[toRow][toCol] = piece;
        tryForCastling(board, fromRow, fromCol, toRow, toCol);
        updateCastlingHistory(fromRow, fromCol);
        tryForPromotion(board);
    }

    public static void simulateMove(byte[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        byte piece = board[fromRow][fromCol];
        board[fromRow][fromCol] = 0;
        board[toRow][toCol] = piece;
        tryForCastling(board, fromRow, fromCol, toRow, toCol);
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private static void resetSelection() {
        selectedRow = null;
        selectedCol = null;
    }

    private static void tryForPromotion(byte[][] board) {
        for (int i = 0; i < 8; i++) {
            if (board[0][i] == 6 || board[7][i] == 6) {
                if (board == activeBoard && human == 1) {
                    whitePromoting = true;
                } else {
                    if (board[0][i] == 6) {
                        board[0][i] = 2;
                    } else if (board[7][i] == 6) {
                        board[7][i] = 2;
                    }
                }
            }
            if (board[0][i] == -6 || board[7][i] == -6) {
                if (board == activeBoard && human == 0) {
                    blackPromoting = true;
                } else if (board == activeBoard && ai == 0) {
                    if (board[0][i] == -6) {
                        board[0][i] = -2;
                    } else if (board[7][i] == -6) {
                        board[7][i] = -2;
                    }
                }
            }
        }
    }

    private static void tryForCastling(byte[][] board, int fromRow, int fromCol, int toRow, int toCol) {

        int[] lastMove = {fromRow, fromCol, toRow, toCol};
        int[] CastlingLowerShort = {7, 4, 7, 6};
        int[] CastlingUpperShort = {0, 4, 0, 6};
        int[] CastlingLowerLong = {7, 4, 7, 2};
        int[] CastlingUpperLong = {0, 4, 0, 2};

        if (Arrays.equals(lastMove, CastlingLowerShort)) {
            board[7][7] = 0;
            board[7][5] = 5;
        }

        if (Arrays.equals(lastMove, CastlingUpperShort)) {
            board[0][7] = 0;
            board[0][5] = 5;
        }

        if (Arrays.equals(lastMove, CastlingLowerLong)) {
            board[7][0] = 0;
            board[7][3] = 5;
        }

        if (Arrays.equals(lastMove, CastlingUpperLong)) {
            board[0][0] = 0;
            board[0][3] = 5;
        }
    }

    public static void promote(int piece) {
        if (whitePromoting || blackPromoting) {
            for (int i = 0; i < 8; i++) {
                if ((activeBoard[0][i] == 6 && piece > 0) || (activeBoard[0][i] == -6 && piece < 0)) {
                    activeBoard[0][i] = (byte) piece;
                } else if ((activeBoard[7][i] == 6 && piece > 0) || (activeBoard[7][i] == -6 && piece < 0)) {
                    activeBoard[7][i] = (byte) piece;
                }
            }
            whitePromoting = false;
            blackPromoting = false;
            turn = 1 - turn;
        }
    }

    public static boolean checkForMate(byte[][] board) {
        if (whiteKingChecked) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (activeBoard[i][j] > 0) {
                        if (canMove(board, i, j)) {
                            return false;
                        }
                    }
                }
            }
        } else if (blackKingChecked) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (activeBoard[i][j] < 0) {
                        if (canMove(board, i, j)) {
                            return false;
                        }
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static void resetGame() {
        setGameStarted(false);
        resetBoard();
        resetPlayingColor();
        resetLegalMoves();
        resetSelection();
        resetChecks();
        resetPromotions();
        resetCastlingHistory();
        resetAlphaBetaBoard();
        history = "#";
    }


    public static void resetChecks() {
        whiteKingChecked = false;
        blackKingChecked = false;
    }

    public static void resetPromotions() {
        whitePromoting = false;
        blackPromoting = false;
    }

    public static void resetPlayingColor() {
        human = -1;
    }

    public static Integer getSelectedRow() {
        return selectedRow;
    }

    public static Integer getSelectedCol() {
        return selectedCol;
    }

    public static boolean isWhitePromoting() {
        return whitePromoting;
    }

    public static boolean isBlackPromoting() {
        return blackPromoting;
    }

    public static int getHuman() {
        return human;
    }

    public static int getAI() {
        return ai;
    }

    public static int getTurn() {
        return turn;
    }

    public static boolean[][] getLegalMoves() {
        return legalMoves;
    }

    public static void setPlayer(int player) {
        GameBoard.human = player;
        GameBoard.ai = 1 - player;
    }

    public static String getHistory() {
        return history;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(boolean gameStarted) {
        GameBoard.gameStarted = gameStarted;
    }

    public static boolean isWhiteKingChecked() {
        return whiteKingChecked;
    }

    public static void setWhiteKingChecked(boolean whiteKingChecked) {
        GameBoard.whiteKingChecked = whiteKingChecked;
    }

    public static boolean isBlackKingChecked() {
        return blackKingChecked;
    }

    public static void setBlackKingChecked(boolean blackKingChecked) {
        GameBoard.blackKingChecked = blackKingChecked;
    }

    public static boolean isAIThinking() {
        return isAIThinking;
    }

    private GameBoard() {
    }
}
