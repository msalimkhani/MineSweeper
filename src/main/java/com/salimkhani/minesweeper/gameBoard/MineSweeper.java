package com.salimkhani.minesweeper.gameBoard;

import com.salimkhani.minesweeper.models.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    private MineTile[][] board;
    private ArrayList<MineTile> mineList ;
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
        _textLabel.setText("MineSweeper");
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

        mineList.add(board[2][2]);
        mineList.add(board[2][3]);
        mineList.add(board[5][6]);
        mineList.add(board[3][4]);
        mineList.add(board[1][1]);

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
                        MineTile tile = (MineTile) e.getSource();

                        if(e.getButton() == MouseEvent.BUTTON1)
                        {
                            if (tile.getText().isEmpty())
                            {
                                if (mineList.contains(tile))
                                {
                                    revealMines();
                                }
                            }
                        }
                    }
                });
                _boardPanel.add(tile);
            }
        }
    }

    private void revealMines() {
        for (MineTile tile : mineList) {
            tile.setText("<html>\uD83D\uDCA3</html>");
        }
    }

    public static MineSweeper getInstance(int rows, int cols, String iconPath)
    {
        if (_instance == null)
            _instance = new MineSweeper(rows, cols, iconPath);
        return _instance;
    }
}
