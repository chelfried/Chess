package org.chess.comm;

import org.chess.core.GameBoard;

import static org.chess.core.GameBoard.getHistory;
import static org.chess.core.MoveGenerator.getAttackVectors;
import static org.chess.core.MoveGenerator.verifyForCheck;

public class FieldClass {

    public static String[][] calcFieldClass() {

        String[][] fieldClass = new String[8][8];
        byte[][] board = GameBoard.getActiveBoard();
        boolean[][] posMove = GameBoard.getLegalMoves();
        Integer selRow = GameBoard.getSelectedRow();
        Integer selCol = GameBoard.getSelectedCol();
        GameBoard.setBlackKingChecked(false);
        GameBoard.setWhiteKingChecked(false);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    fieldClass[i][j] = "light";
                } else if (i % 2 != 0 && j % 2 != 0) {
                    fieldClass[i][j] = "light";
                } else if (i % 2 != 0) {
                    fieldClass[i][j] = "dark";
                } else {
                    fieldClass[i][j] = "dark";
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (posMove[i][j]) {
                    if (fieldClass[i][j].equals("light")) {
                        if ((board[i][j] > 0 && board[selRow][selCol] < 0) || (board[i][j] < 0 && board[selRow][selCol] > 0))
                            fieldClass[i][j] = "takeLight";
                        else {
                            fieldClass[i][j] = "moveLight";
                        }
                    } else if (fieldClass[i][j].equals("dark")) {
                        if ((board[i][j] > 0 && board[selRow][selCol] < 0) || (board[i][j] < 0 && board[selRow][selCol] > 0))
                            fieldClass[i][j] = "takeDark";
                        else {
                            fieldClass[i][j] = "moveDark";
                        }
                    }
                }
            }
        }

        if (selRow != null && selCol != null) {
            if (fieldClass[selRow][selCol].equals("light")) {
                fieldClass[selRow][selCol] = "selectedLight";
            } else {
                fieldClass[selRow][selCol] = "selectedDark";
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (verifyForCheck(board, getAttackVectors(board, board[i][j])) &&
                        (board[i][j] == -1 || board[i][j] == 1)) {
                    if (selRow != null && selRow == i && selCol != null && j == selCol) {
                        fieldClass[i][j] = "selectedCheck";
                    } else {
                        fieldClass[i][j] = "check";
                    }
                    if (board[i][j] == 1) {
                        GameBoard.setWhiteKingChecked(true);
                    } else {
                        GameBoard.setBlackKingChecked(true);
                    }
                }
            }
        }

        String history = getHistory();

        if (!history.equals("#")) {

            int fromRow = history.charAt(history.length() - 4) - 48;
            int fromCol = history.charAt(history.length() - 3) - 48;
            int toRow = history.charAt(history.length() - 2) - 48;
            int toCol = history.charAt(history.length() - 1) - 48;

            addLastMove(fieldClass, fromRow, fromCol);
            addLastMove(fieldClass, toRow, toCol);
        }

        return fieldClass;
    }

    private static void addLastMove(String[][] fieldClass, int row, int col) {
        switch (fieldClass[row][col]) {
            case "light":
                fieldClass[row][col] = "lastMoveLight";
                break;
            case "dark":
                fieldClass[row][col] = "lastMoveDark";
                break;
            case "moveLight":
                fieldClass[row][col] = "lastMoveMoveLight";
                break;
            case "moveDark":
                fieldClass[row][col] = "lastMoveMoveDark";
                break;
            case "takeLight":
                fieldClass[row][col] = "lastMoveTakeLight";
                break;
            case "takeDark":
                fieldClass[row][col] = "lastMoveTakeDark";
                break;
        }
    }

}
