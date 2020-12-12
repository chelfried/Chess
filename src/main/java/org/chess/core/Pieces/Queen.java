package org.chess.core.Pieces;

public class Queen extends Piece {

    public static boolean[][] calcPseudo(byte[][] board, int row, int col) {

        boolean[][] pseudoLegalMoves = new boolean[8][8];

        appendPseudoMoves(calcDiagonal(board, row, col), pseudoLegalMoves);
        appendPseudoMoves(calcCross(board, row, col), pseudoLegalMoves);

        return pseudoLegalMoves;
    }

}
