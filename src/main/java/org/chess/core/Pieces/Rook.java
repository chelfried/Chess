package org.chess.core.Pieces;

public class Rook extends Piece{

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {
        return calcCross(board, row, col);
    }
}
