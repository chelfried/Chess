package org.chess.core.pieces;

public class Bishop extends Piece{

    public static boolean[][] calcPseudo(byte[][] board, int row, int col){
        return calcDiagonal(board, row, col);
    }

}
