package game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game extends JPanel {
    private final int resolution;
    private int row;
    private int col;
    private int[][] board;

    public Game() {
        board = new int[row][col];
        resolution = 5;
        row = 500 / resolution;
        col = 500 / resolution;

        board = createBoard();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);

        for (int rowIndex = 0; rowIndex < row; ++rowIndex) {
            for (int colIndex = 0; colIndex < col; ++colIndex) {
                //g.setColor(new Color(0,0,0,100));
                //g.drawRect(rowIndex * resolution, colIndex * resolution, resolution, resolution);

                switch (board[rowIndex][colIndex]) {
                    case 0 -> {
                        g.setColor(Color.BLACK);
                        g.fillRect(rowIndex * resolution, colIndex * resolution, resolution, resolution);
                    }
                    case 1 -> {
                        g.setColor(Color.WHITE);
                        g.fillRect(rowIndex * resolution, colIndex * resolution, resolution, resolution);
                    }
                    default -> {
                        g.setColor(Color.RED);
                        g.fillOval(rowIndex * resolution, colIndex * resolution, resolution, resolution);
                    }
                }
            }
        }

        int[][] nextBoard = createBoard();
        int state;
        int neighbours;

        for (int rowIndex = 0; rowIndex < row; ++rowIndex) {
            for (int colIndex = 0; colIndex < col; ++colIndex) {
                state = board[rowIndex][colIndex];
                neighbours = countNeighbours(rowIndex, colIndex);

                if (state == 0 && neighbours == 3) {
                    nextBoard[rowIndex][colIndex] = 1;
                } else if (state == 1 && neighbours < 2 || neighbours > 3) {
                    nextBoard[rowIndex][colIndex] = 0;
                } else {
                    nextBoard[rowIndex][colIndex] = state;
                }
            }
        }

        board = nextBoard;

        try {
            TimeUnit.MILLISECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateUI();
    }

    public int[][] createBoard() {
        int[][] genericBoard = new int[row][col];
        Random random = new Random();

        for (int rowIndex = 0; rowIndex < row; ++rowIndex) {
            for (int colIndex = 0; colIndex < col; ++colIndex) {
                genericBoard[rowIndex][colIndex] = random.nextInt(2);
            }
        }

        return genericBoard;
    }

    public int countNeighbours(int thisRow, int thisCol) {
        int numberOfNeighbours = 0;

        for (int rowOffset = -1; rowOffset < 2; ++rowOffset) {
            for (int colOffset = -1; colOffset < 2; ++colOffset) {
                numberOfNeighbours += board[(thisRow + rowOffset + row) % row][(thisCol + colOffset + col) % col];
            }
        }
        numberOfNeighbours -= board[thisRow][thisCol];
        return numberOfNeighbours;
    }
}