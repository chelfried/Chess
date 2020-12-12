package org.chess.core.Pieces;

import org.chess.core.GameBoard;

public class Rook {

    public static boolean[][] calcPseudo(int row, int col) {
        return Piece.calcCross(row, col);
    }
}
