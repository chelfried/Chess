package org.chess.core.ai;

import org.chess.core.Move;

import java.util.List;

import static org.chess.core.GameBoard.*;
import static org.chess.core.MoveGenerator.*;
import static org.chess.core.ai.MoveSorting.sortMoves;
import static org.chess.core.ai.Rating.calcRating;

public class AlphaBeta extends _Interface {

    public static int alphaBetaMax(byte[][] board, int alpha, int beta, int currentDepth) {

        if (currentDepth == searchToDepth) {
            leafNodesEvaluated++;
            return calcRating(board) * (getAI() * 2 - 1);
        }

        List<Move> moves = sortMoves(deepCopyBoard(board), getAllLegalMovesFor(board, getAI()), getAI());

        if (moves.size() == 0) {
            if (checkForMate(board)){
                return Integer.MIN_VALUE / (currentDepth + 1);
            } else {
                return calcRating(board) * (getAI() * 2 - 1);
            }
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
            return calcRating(board) * (getAI() * 2 - 1);
        }

        List<Move> moves = sortMoves(deepCopyBoard(board), getAllLegalMovesFor(board, getHuman()), getHuman());

        if (moves.size() == 0) {
            if (checkForMate(board)){
                return Integer.MAX_VALUE / (currentDepth + 1);
            } else {
                return calcRating(board) * (getAI() * 2 - 1);
            }
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
