package org.chess.core.ai;

import org.chess.core.Move;

import java.util.List;

import static org.chess.core.GameBoard.*;
import static org.chess.core.MoveGenerator.deepCopyBoard;
import static org.chess.core.MoveGenerator.getAllLegalMovesFor;
import static org.chess.core.ai.Rating.calcRating;

public class MiniMax extends _Interface{

        static int max(byte[][] board, int currentDepth) {

        if (currentDepth == searchToDepth) {
            leafNodesEvaluated++;
            return calcRating(board, currentDepth) * (getAI() * 2 - 1);
        }

        List<Move> moves = getAllLegalMovesFor(board, getAI());

        int max = Integer.MIN_VALUE;

        for (Move move : moves) {

            byte[][] tempBoard = deepCopyBoard(board);

            movePiece(tempBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);

            int score = min(tempBoard, currentDepth + 1);

            if (currentDepth == 0) {
                System.out.printf("\n%d%d%d%d: %d (MiniMax)", move.fromRow, move.fromCol, move.toRow, move.toCol, score);
            }

            if (score > max) {
                max = score;
                if (currentDepth == 0) {
                    bestMove = move;
                    System.out.print(" [new best]");
                }
            }
        }
        return max;
    }

    static int min(byte[][] board, int currentDepth) {

        if (currentDepth == searchToDepth) {
            leafNodesEvaluated++;
            return calcRating(board, currentDepth) * (getAI() * 2 - 1);
        }

        List<Move> moves = getAllLegalMovesFor(board, getHuman());

        int min = Integer.MAX_VALUE;

        for (Move move : moves) {

            byte[][] tempBoard = deepCopyBoard(board);

            movePiece(tempBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);

            int score = max(tempBoard, currentDepth + 1);

            if (score < min) {
                min = score;
            }
        }
        return min;
    }

}
