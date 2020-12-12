package org.chess.core;

import org.chess.controller.SSEController;
import org.chess.core.Pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {

    private static byte[][] activeBoard = new byte[8][8];

    private static List<int[]> history = new ArrayList<>();

    private static boolean[][] pseudoLegalMoves = new boolean[8][8];

    private static boolean gameStarted;

    private static Integer selectedRow;
    private static Integer selectedCol;

    private static Turn turn;

    private static Playing playing;

    private static int[] enPassant = new int[4];

    public enum Turn {
        BLACK,
        WHITE
    }

    public enum Playing {
        BLACK,
        WHITE
    }

    public static void initializeBoard() {
        if (playing == Playing.WHITE) {
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
        } else if (playing == Playing.BLACK) {
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
        turn = Turn.WHITE;
        SSEController.refreshPage();
    }

    private static void resetBoard() {
        activeBoard = new byte[8][8];
    }

    public static byte[][] getActiveBoard() {
        return activeBoard;
    }

    public static void makeSelection(int row, int col) {
        if (col >= 0 && col < 8 && row >= 0 && row < 8) {
            if (selectedRow == null && selectedCol == null) {
                if (activeBoard[row][col] != 0) {
                    if (turn == Turn.WHITE && activeBoard[row][col] > 0) {
                        selectedRow = row;
                        selectedCol = col;
                        Piece.calcPseudo(row, col);
                    } else if (turn == Turn.BLACK && activeBoard[row][col] < 0) {
                        selectedRow = row;
                        selectedCol = col;
                        Piece.calcPseudo(row, col);
                    }
                }
            } else if (selectedRow != null && selectedCol != null) {
                if (selectedRow == row && selectedCol == col) {
                    resetSelection();
                    resetPseudoLegalMoves();
                } else {
                    tryMove(selectedRow, selectedCol, row, col);
                }

            }
        }
    }

    private static void resetPseudoLegalMoves() {
        pseudoLegalMoves = new boolean[8][8];
    }

    public static boolean[][] getPseudoLegalMoves() {
        return pseudoLegalMoves;
    }

    private static void tryMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (((activeBoard[fromRow][fromCol] < 0 && activeBoard[toRow][toCol] >= 0) || (activeBoard[fromRow][fromCol] > 0 && activeBoard[toRow][toCol] <= 0))
                && pseudoLegalMoves[toRow][toCol]) {
            history.add(new int[]{fromRow, fromCol, toRow, toCol});
            movePiece(fromRow, fromCol, toRow, toCol);
            resetSelection();
            tryForEnPassant();
            tryForCastling();
            if (turn == Turn.WHITE) {
                turn = Turn.BLACK;
            } else if (turn == Turn.BLACK) {
                turn = Turn.WHITE;
            }
            resetPseudoLegalMoves();
        }
    }

    private static void movePiece(int fromRow, int fromCol, int toRow, int toCol){
        byte piece = activeBoard[fromRow][fromCol];
        activeBoard[fromRow][fromCol] = 0;
        activeBoard[toRow][toCol] = piece;
    }

    private static void resetSelection() {
        selectedRow = null;
        selectedCol = null;
    }

    public static void setEnPassant(int[] enPassant) {
        GameBoard.enPassant = enPassant;
    }

    public static List<int[]> getHistory() {
        return history;
    }

    private static void tryForEnPassant() {
        if (Arrays.equals(history.get(history.size() - 1), enPassant)) {
            activeBoard[enPassant[0]][enPassant[3]] = 0;
        }
    }

    private static void tryForCastling() {

        int[] lastMove = history.get(history.size() - 1);
        int[] CastlingLowerShort = {7, 4, 7, 6};
        int[] CastlingUpperShort = {0, 4, 0, 6};
        int[] CastlingLowerLong = {7, 4, 7, 2};
        int[] CastlingUpperLong = {0, 4, 0, 2};

        if (Arrays.equals(lastMove, CastlingLowerShort)) {
            movePiece(7, 7, 7, 5);
        }

        if (Arrays.equals(lastMove, CastlingUpperShort)) {
            movePiece(0, 7, 0, 5);
        }

        if (Arrays.equals(lastMove, CastlingLowerLong)) {
            movePiece(7, 0, 7, 3);
        }

        if (Arrays.equals(lastMove, CastlingUpperLong)) {
            movePiece(0, 0, 0, 3);
        }

    }

    public static void resetGame(){
        GameBoard.resetBoard();
        GameBoard.resetPlayingColor();
        GameBoard.setGameStarted(false);
        GameBoard.resetHistory();
        GameBoard.resetPseudoLegalMoves();
        GameBoard.resetSelection();
    }

    public static Integer getSelectedRow() {
        return selectedRow;
    }

    public static Integer getSelectedCol() {
        return selectedCol;
    }

    public static Turn getTurn() {
        return turn;
    }

    public static void setTurn(Turn turn) {
        GameBoard.turn = turn;
    }

    public static Playing getPlaying() {
        return playing;
    }

    public static void setPlaying(Playing playing) {
        GameBoard.playing = playing;
    }

    public static void resetPlayingColor() {
        playing = null;
    }

    public static void resetHistory() {
        history = new ArrayList<>();
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(boolean gameStarted) {
        GameBoard.gameStarted = gameStarted;
    }

    private GameBoard() {
    }
}
