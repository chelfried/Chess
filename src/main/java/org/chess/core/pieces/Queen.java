package org.chess.core.pieces;

import static org.chess.core.MoveGenerator.appendPseudoMoves;

public class Queen extends _Piece {

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];

        appendPseudoMoves(calcDiagonal(board, row, col), pseudoLegalMoves);
        appendPseudoMoves(calcCross(board, row, col), pseudoLegalMoves);

        return pseudoLegalMoves;
    }

}
