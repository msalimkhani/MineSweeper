package com.salimkhani.minesweeper;

import com.salimkhani.minesweeper.gameBoard.MineSweeper;
import com.salimkhani.minesweeper.homeScreen.Home;

import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        Home home = Home.getInstance(System.getProperty("user.dir") + "/src/main/resources/minesweeper-icon.png");
    }
}
