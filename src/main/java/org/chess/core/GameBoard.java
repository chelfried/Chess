package org.chess.core;

import org.chess.controller.SSEController;
import org.chess.core.Pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GameBoard {

    private static byte[][] activeBoard = new byte[8][8];

    private static final List<int[]> history = new ArrayList<>();

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

    public static void resetBoard() {
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
                    makeMove(selectedRow, selectedCol, row, col);
                }

            }
        }
    }

    public static void resetPseudoLegalMoves(){
        pseudoLegalMoves = new boolean[8][8];
    }

    public static boolean[][] getPseudoLegalMoves() {
        return pseudoLegalMoves;
    }

    public static void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (((activeBoard[fromRow][fromCol] < 0 && activeBoard[toRow][toCol] >= 0) || (activeBoard[fromRow][fromCol] > 0 && activeBoard[toRow][toCol] <= 0))
                && pseudoLegalMoves[toRow][toCol]) {
            history.add(new int[]{fromRow, fromCol, toRow, toCol});
            byte piece = activeBoard[fromRow][fromCol];
            activeBoard[fromRow][fromCol] = 0;
            activeBoard[toRow][toCol] = piece;
            resetSelection();
            checkForEnPassant();
            if (turn == Turn.WHITE) {
                turn = Turn.BLACK;
            } else if (turn == Turn.BLACK) {
                turn = Turn.WHITE;
            }
            resetPseudoLegalMoves();
        }
    }

    public static void resetSelection(){
        selectedRow = null;
        selectedCol = null;
    }

    public static void setEnPassant(int[] enPassant) {
        GameBoard.enPassant = enPassant;
    }

    public static List<int[]> getHistory() {
        return history;
    }

    public static void checkForEnPassant(){
        if (Arrays.equals(history.get(history.size() - 1), enPassant)){
            activeBoard[enPassant[0]][enPassant[3]] = 0;
        }
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

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(boolean gameStarted) {
        GameBoard.gameStarted = gameStarted;
    }

    private GameBoard() {
    }
}
