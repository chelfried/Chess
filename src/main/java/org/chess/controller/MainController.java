package org.chess.controller;

import org.chess.core.GameBoard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView redirect() {
        return new ModelAndView("chess");
    }

    @PostMapping("/reset")
    public ModelAndView resetGame() {
        GameBoard.resetGame();
        SSEController.refreshPage();
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/white")
    public ModelAndView playWhite() {
        GameBoard.setGameStarted(true);
        GameBoard.setPlaying(GameBoard.Playing.WHITE);
        GameBoard.initializeBoard();
        SSEController.refreshPage();
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/black")
    public ModelAndView playBlack() {
        GameBoard.setGameStarted(true);
        GameBoard.setPlaying(GameBoard.Playing.BLACK);
        GameBoard.initializeBoard();
        SSEController.refreshPage();
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/{row}/{col}")
    public ModelAndView makeSelection(@PathVariable int row, @PathVariable int col) {
        GameBoard.makeSelection(row, col);
        SSEController.refreshPage();
        return new ModelAndView("redirect:/");
    }

    @ModelAttribute("board")
    public static String[][] getBoard() {
        byte[][] boardByte = GameBoard.getActiveBoard();
        String[][] boardString = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardByte[i][j] == -6) {
                    boardString[i][j] = "PB.svg";
                } else if (boardByte[i][j] == -5) {
                    boardString[i][j] = "RB.svg";
                } else if (boardByte[i][j] == -4) {
                    boardString[i][j] = "NB.svg";
                } else if (boardByte[i][j] == -3) {
                    boardString[i][j] = "BB.svg";
                } else if (boardByte[i][j] == -2) {
                    boardString[i][j] = "QB.svg";
                } else if (boardByte[i][j] == -1) {
                    boardString[i][j] = "KB.svg";
                } else if (boardByte[i][j] == 1) {
                    boardString[i][j] = "KW.svg";
                } else if (boardByte[i][j] == 2) {
                    boardString[i][j] = "QW.svg";
                } else if (boardByte[i][j] == 3) {
                    boardString[i][j] = "BW.svg";
                } else if (boardByte[i][j] == 4) {
                    boardString[i][j] = "NW.svg";
                } else if (boardByte[i][j] == 5) {
                    boardString[i][j] = "RW.svg";
                } else if (boardByte[i][j] == 6) {
                    boardString[i][j] = "PW.svg";
                }
            }
        }
        return boardString;
    }

    @ModelAttribute("gameStarted")
    static boolean checkIfGameStarted() {
        return GameBoard.isGameStarted();
    }

    @ModelAttribute("playingBlack")
    static boolean checkIfPlayingBlack() {
        return GameBoard.getPlaying() == GameBoard.Playing.BLACK;
    }

    @ModelAttribute("fieldClass")
    static String[][] getFieldClass() {

        byte[][] board = GameBoard.getActiveBoard();
        String[][] fieldClass = new String[8][8];
        boolean[][] posMove = GameBoard.getLegalMoves();
        Integer selRow = GameBoard.getSelectedRow();
        Integer selCol = GameBoard.getSelectedCol();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    fieldClass[i][j] = "light";
                } else if (i % 2 != 0 && j % 2 != 0) {
                    fieldClass[i][j] = "light";
                } else if (i % 2 != 0) {
                    fieldClass[i][j] = "dark";
                } else {
                    fieldClass[i][j] = "dark";
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (posMove[i][j]) {
                    if (fieldClass[i][j].equals("light")) {
                        if ((board[i][j] > 0 && board[selRow][selCol] < 0) || (board[i][j] < 0 && board[selRow][selCol] > 0))
                            fieldClass[i][j] = "takeLight";
                        else {
                            fieldClass[i][j] = "moveLight";
                        }
                    } else if (fieldClass[i][j].equals("dark")) {
                        if ((board[i][j] > 0 && board[selRow][selCol] < 0) || (board[i][j] < 0 && board[selRow][selCol] > 0))
                            fieldClass[i][j] = "takeDark";
                        else {
                            fieldClass[i][j] = "moveDark";
                        }
                    }
                }
            }
        }

        if (selRow != null && selCol != null) {
            fieldClass[selRow][selCol] = "selected";
        }

        return fieldClass;
    }

    private MainController() {
    }

}