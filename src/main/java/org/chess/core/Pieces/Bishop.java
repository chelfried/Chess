package org.chess.core.Pieces;

import org.chess.core.GameBoard;

public class Bishop {

    public static boolean[][] calcPseudo(int row, int col){
        return Piece.calcDiagonal(row, col);
    }

}
