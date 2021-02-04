package org.chess.comm;

import org.chess.core.GameBoard;

public class GameMessage {

    public static String getMessage() {
        String message;
        if (!GameBoard.isGameStarted()) {
            message = "Please pick a color.";
        } else {
            if (GameBoard.getTurn() == 1) {
                message = "White's turn.";
            } else {
                message = "Black's turn.";
            }
        }

        if (GameBoard.isWhiteKingChecked()) {
            message += " White in check.";
        } else if (GameBoard.isBlackKingChecked()) {
            message += " Black in check.";
        }

        if (GameBoard.isWhitePromoting() || GameBoard.isBlackPromoting()) {
            message += " Pick a piece to promote to.";
        }

        if (GameBoard.isAIThinking()) {
            message += " AI is thinking.";
        }


        if (GameBoard.checkForMate(GameBoard.getActiveBoard())) {
            message = "Checkmate.";
        }

        return message;
    }

}
