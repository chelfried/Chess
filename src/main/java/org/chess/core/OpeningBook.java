package org.chess.core;

import java.io.*;

import static java.lang.Integer.parseInt;

public class OpeningBook {

    public static Move findMove() {

        BufferedReader br;

        String boardState;

        if (GameBoard.getAI() == 1) {
            boardState = convertNotation();
        } else {
            boardState = GameBoard.getHistory();
        }

        String line;

        try {
            br = new BufferedReader(new FileReader("openingBook.bin"));
            while ((line = br.readLine()) != null) {
                if (line.contains(boardState)) {
                    int fromRow = line.charAt(boardState.length()) - 48;
                    int fromCol = line.charAt(boardState.length() + 1) - 48;
                    int toRow = line.charAt(boardState.length() + 2) - 48;
                    int toCol = line.charAt(boardState.length() + 3) - 48;
                    System.out.println("Found Move: " + fromRow + fromCol + toRow + toCol);
                    if (GameBoard.getAI() == 1) {
                        fromRow = Math.abs(fromRow - 7);
                        fromCol = Math.abs(fromCol - 7);
                        toRow = Math.abs(toRow - 7);
                        toCol = Math.abs(toCol - 7);
                    }
                    return new Move(fromRow, fromCol, toRow, toCol);
                }
            }
        } catch (IOException e) {
            return null;
        }

        return null;
    }

    public static String convertNotation() {

        String history = GameBoard.getHistory();
        String historyConverted = "+";

        for (int i = 1; i < history.length(); i++) {
            historyConverted = historyConverted.concat(String.valueOf(Math.abs(parseInt(history.substring(i, i + 1)) - 7)));
        }

        return historyConverted;
    }

}
