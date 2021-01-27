package org.chess.core.ai;

import org.chess.core.Move;

import java.util.List;

import static org.chess.core.GameBoard.*;
import static org.chess.core.MoveGenerator.*;
import static org.chess.core.ai.Rating.calcRating;

public class AlphaBeta extends _Interface {

    public static int alphaBetaMax(byte[][] board, int alpha, int beta, int currentDepth) {

        if (currentDepth == searchToDepth) {
            leafNodesEvaluated++;
            return calcRating(board, currentDepth) * (getAI() * 2 - 1);
        }

        List<Move> moves = getAllLegalMovesFor(board, getAI());
        moves = MoveSorting.sortMoves(deepCopyBoard(board), moves, getAI());

        if (moves.size() == 0) {
            return calcRating(board, currentDepth) * (getAI() * 2 - 1);
        }

        for (Move move : moves) {

            byte[][] tempBoard = deepCopyBoard(board);

            movePiece(tempBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);

            int score = alphaBetaMin(tempBoard, alpha, beta, currentDepth + 1);

            if (score >= beta) {
                return beta;
            }

            if (currentDepth == 0) {
                System.out.printf("\n%d%d%d%d: %d (AlphaBeta)", move.fromRow, move.fromCol, move.toRow, move.toCol, score);
            }

            if (score > alpha) {
                alpha = score;
                if (currentDepth == 0) {
                    bestMove = move;
                    System.out.print(" [new best]");
                }
            }

        }

        return alpha;
    }

    public static int alphaBetaMin(byte[][] board, int alpha, int beta, int currentDepth) {

        if (currentDepth == searchToDepth) {
            leafNodesEvaluated++;
            return calcRating(board, currentDepth) * (getAI() * 2 - 1);
        }

        List<Move> moves = getAllLegalMovesFor(board, getHuman());
        moves = MoveSorting.sortMoves(deepCopyBoard(board), moves, getHuman());

        if (moves.size() == 0) {
            return calcRating(board, currentDepth) * (getAI() * 2 - 1);
        }

        for (Move move : moves) {

            byte[][] tempBoard = deepCopyBoard(board);

            movePiece(tempBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);

            int score = alphaBetaMax(tempBoard, alpha, beta, currentDepth + 1);

            if (score <= alpha) {
                return alpha;
            }

            if (score < beta) {
                beta = score;
            }

        }

        return beta;
    }

}
