package org.chess.core.pieces;

public class Rook extends _Piece {

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {
        return calcCross(board, row, col);
    }
}
