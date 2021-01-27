package org.chess.core.ai;

import org.chess.core.Move;

import static org.chess.core.GameBoard.getActiveBoard;
import static org.chess.core.MoveGenerator.deepCopyBoard;
import static org.chess.core.ai.AlphaBeta.alphaBetaMax;
import static org.chess.core.ai.MiniMax.max;

public class _Interface {

    static int searchToDepth = 5;
    static Move bestMove;

    static int leafNodesEvaluated;

    public static Move moveSearchAI() {

        bestMove = null;
        leafNodesEvaluated = 0;

        System.out.println("\n*** START OF SEARCH ***");

        startStopWatch();

//        max(deepCopyBoard(getActiveBoard()), 0);
        alphaBetaMax(deepCopyBoard(getActiveBoard()), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

        System.out.printf("\n\n%d LEAF NODES EVALUATED IN\n", leafNodesEvaluated);
        System.out.printf("%d MILLISECONDS\n", endStopWatch());
        System.out.println("\n*** END OF SEARCH ***\n");

        return bestMove;
    }


    static long createdMillis = System.currentTimeMillis();

    public static void startStopWatch() {
        createdMillis = System.currentTimeMillis();
    }

    public static int endStopWatch() {
        long nowMillis = System.currentTimeMillis();
        return (int) ((nowMillis - createdMillis));
    }


}
