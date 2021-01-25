package org.chess.core;

public class Castling {

    private static boolean hasRook00Moved;
    private static boolean hasRook07Moved;
    private static boolean hasRook70Moved;
    private static boolean hasRook77Moved;
    private static boolean hasKing04Moved;
    private static boolean hasKing74Moved;

    public static void updateCastlingHistory(int fromRow, int fromCol) {

        if (fromRow == 0 && fromCol == 0){
            hasRook00Moved = true;
        } else if (fromRow == 0 && fromCol == 7){
            hasRook07Moved = true;
        } else if (fromRow == 7 && fromCol == 0){
            hasRook70Moved = true;
        } else if (fromRow == 7 && fromCol == 7){
            hasRook77Moved = true;
        } else if (fromRow == 0 && fromCol == 4){
            hasKing04Moved = true;
        } else if (fromRow == 7 && fromCol == 4){
            hasKing74Moved = true;
        }

    }

    public static void resetCastlingHistory(){
        hasRook00Moved = false;
        hasRook07Moved = false;
        hasRook70Moved = false;
        hasRook77Moved = false;
        hasKing04Moved = false;
        hasKing74Moved = false;
    }

    public static boolean hasRook00Moved() {
        return hasRook00Moved;
    }

    public static boolean hasRook07Moved() {
        return hasRook07Moved;
    }

    public static boolean hasRook70Moved() {
        return hasRook70Moved;
    }

    public static boolean hasRook77Moved() {
        return hasRook77Moved;
    }

    public static boolean hasKing04Moved() {
        return hasKing04Moved;
    }

    public static boolean hasKing74Moved() {
        return hasKing74Moved;
    }
}
