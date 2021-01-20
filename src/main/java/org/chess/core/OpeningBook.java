package org.chess.core;

import java.io.*;
import java.util.Scanner;

public class OpeningBook {

    public static Move findMove() {

        String boardState = GameBoard.getHistory();
        BufferedReader br;

        if (GameBoard.getAI() == 1) {
            boardState = convertNotation();
        }

        System.out.println("History: " + boardState);

        String line;

        try {
            br = new BufferedReader(new FileReader("openingBook.txt"));
            while ((line = br.readLine()) != null) {
                if (line.contains(boardState)) {
                    System.out.println("Opening Book: " + line);
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

        StringBuilder history = new StringBuilder(GameBoard.getHistory());

        for (int i = 0; i < history.length(); i++) {
            history.setCharAt(i, Character.forDigit(Math.abs(history.charAt(i) - 7), 10));
        }

        return history.toString();
    }

}
