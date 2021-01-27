package org.chess.core.pieces;

import org.chess.core.GameBoard;

import java.util.Arrays;

import static org.chess.core.MoveGenerator.getAttackVectors;

public class King extends Piece {

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];

        if (row + 1 <= 7) {
            if (board[row + 1][col] == 0 ||
                    (board[row][col] > 0 && board[row + 1][col] < 0) ||
                    (board[row][col] < 0 && board[row + 1][col] > 0)) {
                pseudoLegalMoves[row + 1][col] = true;
            }
        }

        if (row - 1 >= 0) {
            if (board[row - 1][col] == 0 ||
                    (board[row][col] > 0 && board[row - 1][col] < 0) ||
                    (board[row][col] < 0 && board[row - 1][col] > 0)) {
                pseudoLegalMoves[row - 1][col] = true;
            }
        }

        if (col + 1 <= 7) {
            if (board[row][col + 1] == 0 ||
                    (board[row][col] > 0 && board[row][col + 1] < 0) ||
                    (board[row][col] < 0 && board[row][col + 1] > 0)) {
                pseudoLegalMoves[row][col + 1] = true;
            }
        }

        if (col - 1 >= 0) {
            if (board[row][col - 1] == 0 ||
                    (board[row][col] > 0 && board[row][col - 1] < 0) ||
                    (board[row][col] < 0 && board[row][col - 1] > 0)) {
                pseudoLegalMoves[row][col - 1] = true;
            }
        }

        if (row + 1 <= 7 && col + 1 <= 7) {
            if (board[row + 1][col + 1] == 0 ||
                    (board[row][col] > 0 && board[row + 1][col + 1] < 0) ||
                    (board[row][col] < 0 && board[row + 1][col + 1] > 0)) {
                pseudoLegalMoves[row + 1][col + 1] = true;
            }
        }

        if (row + 1 <= 7 && col - 1 >= 0) {
            if (board[row + 1][col - 1] == 0 ||
                    (board[row][col] > 0 && board[row + 1][col - 1] < 0) ||
                    (board[row][col] < 0 && board[row + 1][col - 1] > 0)) {
                pseudoLegalMoves[row + 1][col - 1] = true;
            }
        }

        if (row - 1 >= 0 && col + 1 <= 7) {
            if (board[row - 1][col + 1] == 0 ||
                    (board[row][col] > 0 && board[row - 1][col + 1] < 0) ||
                    (board[row][col] < 0 && board[row - 1][col + 1] > 0)) {
                pseudoLegalMoves[row - 1][col + 1] = true;
            }
        }

        if (row - 1 >= 0 && col - 1 >= 0) {
            if (board[row - 1][col - 1] == 0 ||
                    (board[row][col] > 0 && board[row - 1][col - 1] < 0) ||
                    (board[row][col] < 0 && board[row - 1][col - 1] > 0)) {
                pseudoLegalMoves[row - 1][col - 1] = true;
            }
        }

        // Castling

        boolean[][] attackVectorsWhite = getAttackVectors(board, -1);
        boolean[][] attackVectorsBlack = getAttackVectors(board, 1);

        if (GameBoard.getAI() == 0) {
            if (board[8][0] == 0 && board[8][1] == 0 && board[row][col] == -1) {
                if (board[0][1] == 0 && board[0][2] == 0 && board[0][3] == 0) {
                    if (!attackVectorsWhite[0][2] && !attackVectorsWhite[0][3] && !attackVectorsWhite[0][4]) {
                        pseudoLegalMoves[0][2] = true;
                    }
                }
            }
            if (board[8][1] == 0 && board[8][2] == 0 && board[row][col] == -1) {
                if (board[0][5] == 0 && board[0][6] == 0) {
                    if (!attackVectorsWhite[0][4] && !attackVectorsWhite[0][5] && !attackVectorsWhite[0][6]) {
                        pseudoLegalMoves[0][6] = true;
                    }
                }
            }
            if (board[8][3] == 0 && board[8][4] == 0 && board[row][col] == 1) {
                if (board[7][1] == 0 && board[7][2] == 0 && board[7][3] == 0) {
                    if (!attackVectorsBlack[7][2] && !attackVectorsBlack[7][3] && !attackVectorsBlack[7][4]) {
                        pseudoLegalMoves[7][2] = true;
                    }
                }
            }
            if (board[8][4] == 0 && board[8][5] == 0 && board[row][col] == 1) {
                if (board[7][5] == 0 && board[7][6] == 0) {
                    if (!attackVectorsBlack[7][4] && !attackVectorsBlack[7][5] && !attackVectorsBlack[7][6]) {
                        pseudoLegalMoves[7][6] = true;
                    }
                }
            }
        } else {
            if (board[8][0] == 0 && board[8][1] == 0 && board[row][col] == 1) {
                if (board[0][1] == 0 && board[0][2] == 0) {
                    if (!attackVectorsBlack[0][1] && !attackVectorsBlack[0][2] && !attackVectorsBlack[0][3]) {
                        pseudoLegalMoves[0][1] = true;
                    }
                }
            }
            if (board[8][1] == 0 && board[8][2] == 0 && board[row][col] == 1) {
                if (board[0][4] == 0 && board[0][5] == 0 && board[0][6] == 0) {
                    if (!attackVectorsBlack[0][3] && !attackVectorsBlack[0][4] && !attackVectorsBlack[0][5]) {
                        pseudoLegalMoves[0][5] = true;
                    }
                }
            }
            if (board[8][3] == 0 && board[8][4] == 0 && board[row][col] == -1) {
                if (board[7][1] == 0 && board[7][2] == 0) {
                    if (!attackVectorsWhite[7][1] && !attackVectorsWhite[7][2] && !attackVectorsWhite[7][3]) {
                        pseudoLegalMoves[7][1] = true;
                    }
                }
            }
            if (board[8][4] == 0 && board[8][5] == 0 && board[row][col] == -1) {
                if (board[7][4] == 0 && board[7][5] == 0 && board[7][6] == 0) {
                    if (!attackVectorsWhite[7][3] && !attackVectorsWhite[7][4] && !attackVectorsWhite[7][5]) {
                        pseudoLegalMoves[7][5] = true;
                    }
                }
            }
        }

        return pseudoLegalMoves;
    }

    public static void updateCastlingHistory(byte[][] board, int fromRow, int fromCol) {

        if (fromRow == 0 && fromCol == 0) {
            board[8][0] = 1;
        } else if (fromRow == 0 && fromCol == 7) {
            board[8][2] = 1;
        } else if (fromRow == 7 && fromCol == 0) {
            board[8][3] = 1;
        } else if (fromRow == 7 && fromCol == 7) {
            board[8][5] = 1;
        }

        if (GameBoard.getAI() == 0) {
            if (fromRow == 0 && fromCol == 4) {
                board[8][1] = 1;
            } else if (fromRow == 7 && fromCol == 4) {
                board[8][4] = 1;
            }
        } else {
            if (fromRow == 0 && fromCol == 3) {
                board[8][1] = 1;
            } else if (fromRow == 7 && fromCol == 3) {
                board[8][4] = 1;
            }
        }

    }

    public static void checkForCastling(byte[][] board, int fromRow, int fromCol, int toRow, int toCol) {

        updateCastlingHistory(board, fromRow, fromCol);

        int[] lastMove = {fromRow, fromCol, toRow, toCol};

        if (Math.abs(board[toRow][toCol]) == 1) {
            if (GameBoard.getAI() == 0) {
                int[] CastlingUpperLong = {0, 4, 0, 2};
                int[] CastlingUpperShort = {0, 4, 0, 6};
                int[] CastlingLowerLong = {7, 4, 7, 2};
                int[] CastlingLowerShort = {7, 4, 7, 6};
                if (Arrays.equals(lastMove, CastlingUpperLong) && board[8][0] == 0 && board[8][1] == 0) {
                    board[0][0] = 0;
                    board[0][3] = -5;
                } else if (Arrays.equals(lastMove, CastlingUpperShort) && board[8][1] == 0 && board[8][2] == 0) {
                    board[0][7] = 0;
                    board[0][5] = -5;
                } else if (Arrays.equals(lastMove, CastlingLowerLong) && board[8][3] == 0 && board[8][4] == 0) {
                    board[7][0] = 0;
                    board[7][3] = 5;
                } else if (Arrays.equals(lastMove, CastlingLowerShort) && board[8][4] == 0 && board[8][5] == 0) {
                    board[7][7] = 0;
                    board[7][5] = 5;
                }
            } else {
                int[] CastlingUpperShort = {0, 3, 0, 1};
                int[] CastlingUpperLong = {0, 3, 0, 5};
                int[] CastlingLowerShort = {7, 3, 7, 1};
                int[] CastlingLowerLong = {7, 3, 7, 5};
                if (Arrays.equals(lastMove, CastlingUpperShort) && board[8][0] == 0 && board[8][1] == 0) {
                    board[0][0] = 0;
                    board[0][2] = 5;
                } else if (Arrays.equals(lastMove, CastlingUpperLong) && board[8][1] == 0 && board[8][2] == 0) {
                    board[0][7] = 0;
                    board[0][4] = 5;
                } else if (Arrays.equals(lastMove, CastlingLowerShort) && board[8][3] == 0 && board[8][4] == 0) {
                    board[7][0] = 0;
                    board[7][2] = -5;
                } else if (Arrays.equals(lastMove, CastlingLowerLong) && board[8][4] == 0 && board[8][5] == 0) {
                    board[7][7] = 0;
                    board[7][4] = -5;
                }
            }
        }

    }

}
