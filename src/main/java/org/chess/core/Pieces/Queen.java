package org.chess.core.Pieces;

import org.chess.core.GameBoard;

public class Queen {

    public static boolean[][] calcPseudo(int row, int col) {

        Piece.calcDiagonal(row, col);
        Piece.calcCross(row, col);

        return GameBoard.getPseudoLegalMoves();
    }

}
