package com.salimkhani.minesweeper.homeScreen;

import com.salimkhani.minesweeper.gameBoard.MineSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home {
    private static Home _instance;
    private JFrame _frame = new JFrame("MineSweeper Home");
    private JPanel headerPanel = new JPanel();
    private JLabel headerText = new JLabel();
    private JPanel contentPanel = new JPanel();
    private JPanel footerPanel = new JPanel();
    private JLabel footerText = new JLabel();
    private JButton btnEight = new JButton();
    private JButton btnTen = new JButton();
    private JButton btnTwelve = new JButton();
    private JButton btnForteen = new JButton();
    private MineSweeper game = null;
    private Home(String iconPath)
    {
        ImageIcon icon = new ImageIcon(iconPath);
        _frame.setSize(500, 550);
        _frame.setResizable(false);
        _frame.setLocationRelativeTo(null);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setLayout(new BorderLayout());
        _frame.setIconImage(icon.getImage());

        headerText.setFont(new Font("Arial",Font.BOLD, 25));
        headerText.setText("Welcome to Minesweeper Game!");
        headerText.setHorizontalAlignment(JLabel.CENTER);
        headerText.setOpaque(true);

        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(headerText);

        footerText.setFont(new Font("Arial",Font.BOLD, 10));
        footerText.setText("By Mahdi Salimkhani");
        footerText.setHorizontalAlignment(JLabel.CENTER);
        footerText.setOpaque(true);

        footerPanel.setLayout(new BorderLayout());
        footerPanel.add(footerText);

        _frame.add(headerPanel, BorderLayout.NORTH);
        _frame.add(footerPanel, BorderLayout.SOUTH);
        btnEight.setFont(new Font("Arial", Font.PLAIN, 30));
        btnTen.setFont(new Font("Arial", Font.PLAIN, 30));
        btnTwelve.setFont(new Font("Arial", Font.PLAIN, 30));
        btnForteen.setFont(new Font("Arial", Font.PLAIN, 30));

        btnEight.setText("8x8");
        btnTen.setText("10x10");
        btnTwelve.setText("12x12");
        btnForteen.setText("14x14");

        btnEight.setFocusable(false);
        btnTen.setFocusable(false);
        btnTwelve.setFocusable(false);
        btnForteen.setFocusable(false);

        contentPanel.setLayout(new GridLayout(2, 2));
        contentPanel.add(btnEight);
        contentPanel.add(btnTen);
        contentPanel.add(btnTwelve);
        contentPanel.add(btnForteen);

        btnEight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game = MineSweeper.getInstance(8, 8, iconPath, _frame);
            }
        });

        btnTen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game = MineSweeper.getInstance(10, 10, iconPath, _frame);
            }
        });

        btnTwelve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game = MineSweeper.getInstance(12, 12, iconPath, _frame);
            }
        });

        btnForteen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game = MineSweeper.getInstance(14, 14, iconPath, _frame);
            }
        });

        _frame.add(contentPanel);
        _frame.setVisible(true);
        //MineSweeper game = MineSweeper.getInstance(8, 8, System.getProperty("user.dir") + "/src/main/resources/minesweeper-icon.png");
    }

    public static Home getInstance(String iconPath) {
        if (_instance == null)
            _instance = new Home(iconPath);
        return _instance;
    }
}
