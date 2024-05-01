package com.salimkhani.minesweeper.models;

import javax.swing.*;

public class Tile extends JButton {
    private final int row;
    private final int col;

    public Tile(int row, int col)
    {
        this.col = col;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
