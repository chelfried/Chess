package org.chess.comm;

import org.chess.core.GameBoard;

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

        return fieldClass;
    }

}
