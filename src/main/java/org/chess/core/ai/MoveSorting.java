package org.chess.core.ai;

import javafx.util.Pair;
import org.chess.core.Move;

import java.util.*;

import static org.chess.core.GameBoard.movePiece;
import static org.chess.core.MoveGenerator.deepCopyBoard;
import static org.chess.core.ai.Rating.calcRating;

public class MoveSorting {


    public static List<Move> sortMoves(byte[][] board, List<Move> moves, int player) {

        List<Pair<Integer, Move>> sortedList = new ArrayList<>();

        for (Move move : moves) {
            byte[][] tempBoard = deepCopyBoard(board);
            movePiece(tempBoard, move.fromRow, move.fromCol, move.toRow, move.toCol);
            sortedList.add(new Pair<>(calcRating(tempBoard, 0) * (player * 2 - 1), move));
        }

        sortedList.sort(Comparator.comparingInt(Pair<Integer, Move>::getKey).reversed());

        List<Move> sortedMoves = new ArrayList<>();

        for (int i = 0; i < sortedList.size(); i++){
            sortedMoves.add(sortedList.get(i).getValue());
        }

        return sortedMoves;
    }

}
