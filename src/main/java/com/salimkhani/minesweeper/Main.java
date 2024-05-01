package com.salimkhani.minesweeper;

import com.salimkhani.minesweeper.gameBoard.MineSweeper;

import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        MineSweeper game = MineSweeper.getInstance(8, 8, System.getProperty("user.dir") + "/src/main/resources/minesweeper-icon.png");
    }
}
