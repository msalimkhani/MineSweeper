package com.salimkhani.minesweeper.gameBoard;

import com.salimkhani.minesweeper.models.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class MineSweeper {
    private static MineSweeper _instance;
    private class MineTile extends Tile
    {
        public MineTile(int row, int col) {
            super(row, col);
        }
    }
    private final JFrame _frame = new JFrame("MineSweeper");
    private final JLabel _textLabel = new JLabel();
    private final JPanel _textPanel = new JPanel();
    private final JPanel _boardPanel = new JPanel();
    private final int tileSize = 70;
    private final int numRows;
    private final int numCols;
    private final int boardWith;
    private final int boardHeight;
    private final int mineCount = 10;
    private MineTile[][] board;
    private ArrayList<MineTile> mineList ;
    private Random random = new Random();
    private int tilesClicked = 0;
    boolean gameOver = false;
    private MineSweeper(int rows, int cols, String iconPath)
    {
        numRows = rows;
        numCols = cols;
        boardWith = numCols * tileSize;
        boardHeight = numRows * tileSize;
        InitializeComponents(iconPath);
    }

    private void InitializeComponents(String iconPath) {

        ImageIcon icon = new ImageIcon(iconPath);
        _frame.setSize(boardWith, boardHeight);
        _frame.setResizable(false);
        _frame.setLocationRelativeTo(null);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setLayout(new BorderLayout());
        _frame.setIconImage(icon.getImage());

        _textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        _textLabel.setHorizontalAlignment(JLabel.CENTER);
        _textLabel.setText("MineSweeper: " + Integer.toString(mineCount));
        _textLabel.setOpaque(true);

        _textPanel.setLayout(new BorderLayout());
        _textPanel.add(_textLabel);

        _frame.add(_textPanel, BorderLayout.NORTH);

        _boardPanel.setLayout(new GridLayout(numRows, numCols));
        _frame.add(_boardPanel);
        board = new MineTile[numRows][numCols];
        fillBoard();
        _frame.setVisible(true);
        setMines();
    }

    private void setMines() {
        mineList = new ArrayList<>();

        int mineLeft = mineCount;
        while (mineLeft > 0)
        {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numCols);

            MineTile tile = board[row][col];
            if(!mineList.contains(tile))
            {
                mineList.add(tile);
                mineLeft -= 1;
            }
        }

    }

    private void fillBoard() {
        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;

                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));
                //tile.setText("<html>\uD83D\uDCA3</html>");
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (gameOver)
                            return;
                        MineTile tile = (MineTile) e.getSource();

                        if(e.getButton() == MouseEvent.BUTTON1)
                        {
                            if (tile.getText().isEmpty())
                            {
                                if (mineList.contains(tile))
                                    revealMines();
                                else
                                    checkMines(tile.getRow(), tile.getCol());
                            }
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            if(tile.getText().isEmpty() && tile.isEnabled())
                                tile.setText("\uD83D\uDEA9");
                            else if (tile.getText().equals("\uD83D\uDEA9")) {
                                tile.setText("");
                            }
                        }
                    }
                });
                _boardPanel.add(tile);
            }
        }
    }

    private void checkMines(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols)
            return ;
        MineTile tile = board[row][col];
        if(!tile.isEnabled())
            return;
        tile.setEnabled(false);
        tilesClicked += 1;

        int minesFound = 0;

        //top 3
        minesFound += countMine(row - 1, col - 1);
        minesFound += countMine(row - 1, col);
        minesFound += countMine(row - 1, col + 1);

        // left and right
        minesFound += countMine(row , col - 1);
        minesFound += countMine(row, col + 1);

        //bottom 3
        minesFound += countMine(row + 1, col - 1);
        minesFound += countMine(row + 1, col);
        minesFound += countMine(row + 1, col + 1);

        if (minesFound > 0)
            tile.setText(Integer.toString(minesFound));
        else
        {
            tile.setText("");
            checkMines(row - 1, col - 1);
            checkMines(row - 1, col);
            checkMines(row - 1, col + 1);

            // left and right
            checkMines(row , col - 1);
            checkMines(row, col + 1);

            //bottom 3
            checkMines(row + 1, col - 1);
            checkMines(row + 1, col);
            checkMines(row + 1, col + 1);
        }
        if (tilesClicked == numRows * numCols - mineList.size())
        {
            gameOver = true;
            _textLabel.setText("Mines Cleared!");
        }
    }

    private int countMine(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols)
            return 0;
        if(mineList.contains(board[row][col]))
            return 1;
        return 0;
    }

    private void revealMines() {
        for (MineTile tile : mineList) {
            tile.setText("<html>\uD83D\uDCA3</html>");
        }
        gameOver = true;
        _textLabel.setText("Game Over!");
    }

    public static MineSweeper getInstance(int rows, int cols, String iconPath)
    {
        if (_instance == null)
            _instance = new MineSweeper(rows, cols, iconPath);
        return _instance;
    }
}
