package org.chess.core.ai;

import static org.chess.core.GameBoard.*;
import static org.chess.core.MoveGenerator.*;

public class Rating {

    protected static int[][] pawnTable = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    protected static int[][] knightTable = {
            {-50, -40, -30, -30, -30, -30, -40, -50},
            {-40, -20, 0, 5, 5, 0, -20, -40},
            {-30, 5, 10, 15, 15, 10, 5, -30},
            {-30, 0, 15, 20, 20, 15, 0, -30},
            {-30, 5, 15, 20, 20, 15, 5, -30},
            {-30, 0, 10, 15, 15, 10, 0, -30},
            {-40, -20, 0, 0, 0, 0, -20, -40},
            {-50, -40, -30, -30, -30, -30, -40, -50}
    };

    protected static int[][] bishopTable = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10, 5, 0, 0, 0, 0, 5, -10},
            {-10, 10, 10, 10, 10, 10, 10, -10},
            {-10, 0, 10, 10, 10, 10, 0, -10},
            {-10, 5, 5, 10, 10, 5, 5, -10},
            {-10, 0, 5, 10, 10, 5, 0, -10},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}
    };

    protected static int[][] rookTable = {
            {0, 0, 0, 5, 5, 0, 0, 0},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {5, 10, 10, 10, 10, 10, 10, 5},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    protected static int[][] queenTable = {
            {-20, -10, -10, -5, -5, -10, -10, -20},
            {-10, 0, 5, 0, 0, 0, 0, -10},
            {-10, 5, 5, 5, 5, 5, 0, -10},
            {0, 0, 5, 5, 5, 5, 0, -5},
            {-5, 0, 5, 5, 5, 5, 0, -5},
            {-10, 0, 5, 5, 5, 5, 0, -10},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-20, -10, -10, -5, -5, -10, -10, -20}
    };

    protected static int[][] kingTable = {
            {20, 30, 10, 0, 0, 10, 30, 20},
            {20, 20, 0, 0, 0, 0, 20, 20},
            {-10, -20, -20, -20, -20, -20, -20, -10},
            {-20, -30, -30, -40, -40, -30, -30, -20},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30}
    };

    public static int calcRating(byte[][] board, int totalMoves, int depth) {

        int rating = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int piece = Math.abs(board[row][col]);
                if (piece == 6) {
                    rating += calcPositional(pawnTable, row, col, board[row][col]);
                    if (board[row][col] > 0) {
                        rating += 100;
                    } else {
                        rating -= 100;
                    }
                } else if (piece == 5) {
                    rating += calcPositional(rookTable, row, col, board[row][col]);
                    if (board[row][col] > 0) {
                        rating += 500;
                    } else {
                        rating -= 500;
                    }
                } else if (piece == 4) {
                    rating += calcPositional(knightTable, row, col, board[row][col]);
                    if (board[row][col] > 0) {
                        rating += 320;
                    } else {
                        rating -= 320;
                    }
                } else if (piece == 3) {
                    rating += calcPositional(bishopTable, row, col, board[row][col]);
                    if (board[row][col] > 0) {
                        rating += 330;
                    } else {
                        rating -= 330;
                    }
                } else if (piece == 2) {
                    rating += calcPositional(queenTable, row, col, board[row][col]);
                    if (board[row][col] > 0) {
                        rating += 900;
                    } else {
                        rating -= 900;
                    }
                } else if (piece == 1) {
                    rating += calcPositional(kingTable, row, col, board[row][col]);
                }
            }
        }

        if (totalMoves == 0) {
            if (checkForMate(board)) {
                if (depth == 0) {
                    rating -= 1000000;
                } else {
                    rating -= 1000000 / depth;
                }
            }
        }

        return rating;
    }

    public static int calcPositional(int[][] posBoard, int row, int col, int piece) {

        int rating = 0;

        if (piece < 0) {
            if (getAI() == 0)
                rating -= posBoard[row][col];
            else {
                rating -= posBoard[(row - 7) * -1][col];
            }
        } else if (piece > 0) {
            if (getAI() == 0)
                rating += posBoard[row][col];
            else {
                rating += posBoard[(row - 7) * -1][col];
            }
        }

        return rating;
    }

}
