package org.chess.core.Pieces;

public class Bishop extends Piece{

    public static boolean[][] calcPseudo(byte[][] board, int row, int col){
        return calcDiagonal(board, row, col);
    }

}
