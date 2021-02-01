package org.chess.controller;

import org.chess.comm.FieldClass;
import org.chess.comm.FieldPiece;
import org.chess.core.GameBoard;
import org.chess.comm.GameMessage;
import org.chess.core.pieces.Pawn;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://86.121.142.39")
@RequestMapping(value = "/api")
public class MainController {

    @PostMapping("/reset")
    public void resetGame() {
        GameBoard.resetGame();
        SSEController.refreshPage();
    }

    @PostMapping("/white")
    public void playWhite() {
        GameBoard.setGameStarted(true);
        GameBoard.setPlayer(1);
        GameBoard.initializeBoard();
        SSEController.refreshPage();
    }

    @PostMapping("/black")
    public void playBlack() {
        GameBoard.setGameStarted(true);
        GameBoard.setPlayer(0);
        GameBoard.initializeBoard();
        GameBoard.moveByAI();
        SSEController.refreshPage();
    }

    @PostMapping("/{row}/{col}")
    public void makeSelection(@PathVariable int row, @PathVariable int col) {
        GameBoard.makeSelection(row, col);
        SSEController.refreshPage();
    }

    @GetMapping("/board")
    public static String[][] getPieces() {
        return FieldPiece.getPieces();
    }

    @GetMapping("/gameStarted")
    static boolean checkIfGameStarted() {
        return GameBoard.isGameStarted();
    }

    @GetMapping("/playingBlack")
    static boolean checkIfPlayingBlack() {
        return GameBoard.getHuman() == 0;
    }

    @GetMapping("/gameMessage")
    static String getGameMessage() {
        return JSONObject.quote(GameMessage.getMessage());
    }

    @GetMapping("/fieldClass")
    static String[][] getFieldClasses() {
        return FieldClass.calcFieldClass();
    }

    @GetMapping("/whitePromoting")
    static boolean isWhitePromoting() {
        return GameBoard.isWhitePromoting();
    }

    @GetMapping("/blackPromoting")
    static boolean isBlackPromoting() {
        return GameBoard.isBlackPromoting();
    }

    @PostMapping("/promote/{piece}")
    public void promote(@PathVariable int piece) {
        Pawn.promotePawn(GameBoard.getActiveBoard(), piece);
        SSEController.refreshPage();
    }

    private MainController() {
    }


}