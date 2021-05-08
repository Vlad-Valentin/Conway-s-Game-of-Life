package com.company;

import game.Game;

import javax.swing.*;

public class Main {
    private static void initUI() {
        JFrame f = new JFrame("Game of Life");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Game());
        f.setSize(517, 540);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::initUI);
    }
}