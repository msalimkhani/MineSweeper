package com.salimkhani.minesweeper;
import com.salimkhani.minesweeper.homeScreen.Home;

public class Main {
    public static void main(String[] args) {
        Home.getInstance(System.getProperty("user.dir") + "/src/main/resources/minesweeper-icon.png");
    }
}
