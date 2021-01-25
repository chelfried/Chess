package org.chess.core;

import java.util.List;

import static org.chess.core.GameBoard.*;
import static org.chess.core.MoveGenerator.calcLegalMovesFor;
import static org.chess.core.MoveGenerator.deepCopyBoard;
import static org.chess.core.Rating.calcRating;

public class AlphaBeta {

    static int searchToDepth = 4;

    static private byte[][] alphaBetaBoard = deepCopyBoard(getActiveBoard());

    static Move bestMove;

    public static int alphaBetaMax(byte[][] board, int alpha, int beta, int depth) {

        if (depth == searchToDepth) {
            return calcRating(board) * (getAI() * 2 - 1);
        }

        List<Move> moves = calcLegalMovesFor(board, getAI());


        if (moves.size() == 0) {
            return calcRating(board) * (getAI() * 2 - 1);
        }

        for (Move move : moves) {

            byte[][] tempBoard = deepCopyBoard(board);

            simulateMove(tempBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);

            int score = alphaBetaMin(tempBoard, alpha, beta, depth + 1);

            if (score >= beta) {
                return beta;
            }

            if (score > alpha) {
                alpha = score;
                if (depth == 0) {
                    bestMove = move;
                }
            }

        }

        return alpha;
    }

    public static int alphaBetaMin(byte[][] board, int alpha, int beta, int depth) {

        if (depth == searchToDepth) {
            return calcRating(board) * (getAI() * 2 - 1);
        }

        List<Move> moves = calcLegalMovesFor(board, getHuman());

        if (moves.size() == 0) {
            return calcRating(board) * (getAI() * 2 - 1);
        }

        for (Move move : moves) {

            byte[][] tempBoard = deepCopyBoard(board);

            simulateMove(tempBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);

            int score = alphaBetaMax(tempBoard, alpha, beta, depth + 1);

            if (score <= alpha) {
                return alpha;
            }

            if (score < beta) {
                beta = score;
                if (depth == 0) {
                    bestMove = move;
                }
            }

        }

        return beta;
    }

    public static void resetAlphaBetaBoard() {
        alphaBetaBoard = deepCopyBoard(getActiveBoard());
        bestMove = null;
    }

    public static byte[][] getAlphaBetaBoard() {
        return alphaBetaBoard;
    }
}
