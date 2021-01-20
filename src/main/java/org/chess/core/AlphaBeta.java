package org.chess.core;

import java.util.List;

import static org.chess.core.GameBoard.*;
import static org.chess.core.MoveGenerator.calcLegalMovesFor;
import static org.chess.core.MoveGenerator.copyBoard;
import static org.chess.core.Rating.calcRating;

public class AlphaBeta {

    static int searchToDepth = 4;

    static private byte[][] alphaBetaBoard = copyBoard(getActiveBoard());

    static Move bestMove;

//    public static int alphaBeta(int depth, int alpha, int beta, int player) {
//
//        List<Move> moves = calcLegalMovesFor(player);
//
//        if (depth == 0) {
//            if (player == getPlayer()) {
//                return Rating.calcRating(alphaBetaBoard, player) * -1;
//            } else {
//                return Rating.calcRating(alphaBetaBoard, player);
//            }
//        }
//
//        for (Move move : moves) {
//
//            byte[][] temp = copyBoard(alphaBetaBoard);
//
//            movePiece(alphaBetaBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);
//
//            int score = alphaBeta(depth - 1, alpha, beta, 1 - player);
//
//            alphaBetaBoard = temp;
//
//            if (player == getPlayer()) {
//                if (score <= beta) {
//                    beta = score;
//                    if (depth == searchToDepth) {
//                        bestMove = move;
//                    }
//                }
//            } else {
//                if (score > alpha) {
//                    alpha = score;
//                    if (depth == searchToDepth) {
//                        bestMove = move;
//                    }
//                }
//            }
//
//            if (alpha >= beta) {
//                if (player == getPlayer()) {
//                    return beta;
//                } else {
//                    return alpha;
//                }
//            }
//
//        }
//
//        if (player == getPlayer()) {
//            return beta;
//        } else {
//            return alpha;
//        }
//
//    }

    public static int alphaBetaMax(byte[][] board, int alpha, int beta, int depth) {

        if (depth == searchToDepth) {
            return calcRating(board) * (getAI() * 2 - 1);
        }

        List<Move> moves = calcLegalMovesFor(board, getAI());


        if (moves.size() == 0) {
            return calcRating(board) * (getAI() * 2 - 1);
        }

        for (Move move : moves) {

            byte[][] tempBoard = copyBoard(board);

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

            byte[][] tempBoard = copyBoard(board);

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
        alphaBetaBoard = copyBoard(getActiveBoard());
        bestMove = null;
    }

    public static byte[][] getAlphaBetaBoard() {
        return alphaBetaBoard;
    }
}
