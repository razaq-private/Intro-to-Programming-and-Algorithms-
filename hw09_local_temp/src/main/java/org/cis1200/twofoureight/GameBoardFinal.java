package org.cis1200.twofoureight;


import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GameBoardFinal extends JPanel {
    private TwoFourEight tfe;
    private JLabel status;
    private int[][] tiles;
    private int score;


    private LinkedList<int[][]> boardState;
    private LinkedList<Integer> savedScore;

    // Game constants
    public static final int BOARD_WIDTH = 300;
    public static final int BOARD_HEIGHT = 300;


    public GameBoardFinal(JLabel status) {
        setBorder(BorderFactory.createLineBorder((Color.BLACK)));
        setFocusable(true);

        tfe = new TwoFourEight();
        status = status;

        //initialize the board
        tiles = new int[4][4];
        score = 0;
        boardState = new LinkedList<int[][]>();
        savedScore = new LinkedList<Integer>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = 0;
            }
        }

        //find a random number between 1-4
        int iRand = (int)(Math.random() * 3);
        int jRand = (int)(Math.random() * 3);

        //need to start with a tile that has the number 2;
        tiles[iRand][jRand] = 2;

        //check if the random tiles are generated in place that already have tiles placed
        int a = iRand;
        int b = jRand;

        //generate new position for th tile
        while (a == iRand && b == jRand) {
            a = (int)(Math.random() * 3);
            b = (int)(Math.random() * 3);
        }
        //create the second tile

        tiles[a][b] = 2;

        setLayout(new GridLayout(4,4));

        getUpdatedBoard();


        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    mergeLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    mergeRight();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    mergeDown();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    mergeUp();
                }
                boolean emptySpace = true;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (tiles[i][j] != boardState.getLast()[i][j]) {
                            emptySpace = false;
                        }
                    }
                }

                if (!emptySpace) {
                    randomTiles();
                }

                getUpdatedBoard();
            }

            public void keyReleased(KeyEvent e) {

            }
        });
        this.status = status;
    }

    private void getUpdatedBoard() {
        //create the board with the tiles
        int[][] updatedBoard = new int[4][4];
        int newScore = score;
        removeAll();
        int counter = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                updatedBoard[i][j] = tiles[i][j];
                JLabel t;
                //add the number into the tiles
                if (tiles[i][j] != 0) {
                    t = new JLabel(Integer.toString(tiles[i][j]), JLabel.CENTER);
                } else {
                    t = new JLabel("", JLabel.CENTER);
                }
                t.setPreferredSize(new Dimension(50, 50));
                t.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                t.setOpaque(true);
                t.setBackground(getTileColor(tiles[i][j]));
                add(t);
                counter++;
            }
        }
        boardState.add(updatedBoard);
        savedScore.add(newScore);
        revalidate();
        repaint();
    }

    /* Functions to move Tiles **/
    //strategy is to move up if there is an empty slot and if it's at border keep resetting
    private void moveUp() {
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                while (tiles[i - 1][j] == 0 && tiles[i][j] != 0) {
                    int temp = tiles[i][j];
                    tiles[i - 1][j] = temp;
                    tiles[i][j] = 0;
                    if (i >= 3) {
                        i--;
                    }
                }
            }
        }
    }

    private void mergeUp() {
        for (int j = 0; j < 4; j++)  {
            for (int i = 0; i < 3; i ++) {
                moveUp();
                int tileAbove = tiles[i + 1][j];
                if (tiles[i][j] == tileAbove && tileAbove != 0) {
                    tiles[i][j] = 2 * tileAbove;
                    tiles[i + 1][j] = 0;
                    score += 2 * tileAbove;

                }
            }
        }
    }

    private void moveDown() {
        for (int j = 0; j < 4; j++) {
            for (int i = 2; i > -1; i--) {
                while (tiles[i + 1][j] == 0 && tiles[i][j] != 0) {
                    int temp = tiles[i][j];
                    tiles[i + 1][j] = temp;
                    tiles [i][j] = 0;
                    if (i < 2) {
                        i++;
                    }
                }
            }
        }
    }

    private void mergeDown() {
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i > 0; i--) {
                moveDown();
                int tileBelow = tiles[i - 1][j];
                if (tiles[i][j] == tileBelow && tileBelow != 0) {
                    tiles[i][j] = 2 * tileBelow;
                    tiles[i - 1][j] = 0;
                    score += 2 * tileBelow;

                }
            }
        }
    }

    private void moveLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                while (tiles[i][j - 1] == 0 && tiles[i][j] != 0) {
                    int temp = tiles[i][j];
                    tiles[i][j - 1] = temp;
                    tiles[i][j] = 0;
                    if (j > 2) {
                        j--;
                    }
                }
            }
        }
    }

    private void mergeLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                moveLeft();
                int leftTile = tiles[i][j + 1];
                if (tiles[i][j] == leftTile && leftTile != 0) {
                    tiles[i][j] = 2 * leftTile;
                    tiles[i][j + 1] = 0;
                    score += 2 * leftTile;

                }
            }
        }
    }

    private void moveRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j > -1; j--) {
                while (tiles[i][j + 1] == 0 && tiles[i][j] != 0) {
                    int temp = tiles[i][j];
                    tiles[i][j + 1] = temp;
                    tiles[i][j] = 0;
                    if (j > 2) {
                        j--;
                    }
                }
            }
        }
    }

    private void mergeRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                moveRight();
                int rightTile = tiles[i][j - 1];
                if (tiles[i][j] == rightTile && rightTile != 0) {
                    tiles[i][j] = 2 * rightTile;
                    tiles[i][j - 1] = 0;
                    score += 2 * rightTile;

                }
            }
        }
    }

    //Generate random tiles after a person moves
    private void randomTiles() {
        int iRand = (int)(Math.random() * 4);
        int jRand = (int)(Math.random() * 4);

        while (tiles[iRand][jRand] != 0) {
            iRand = (int)(Math.random() * 4);
            jRand = (int)(Math.random() * 4);
        }
        tiles[iRand][jRand] = 2;

    }

    public void reset() {
        tiles = new int[4][4];
        score = 0;
        boardState = new LinkedList<int[][]>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = 0;
            }
        }

        //find a random number between 1-4
        int iRand = (int)(Math.random() * 3);
        int jRand = (int)(Math.random() * 3);

        //need to start with a tile that has the number 2;
        tiles[iRand][jRand] = 2;

        //check if the random tiles are generated in place that already have tiles placed
        int a = iRand;
        int b = jRand;

        //generate new position for th tile
        while (a == iRand && b == jRand) {
            a = (int)(Math.random() * 3);
            b = (int)(Math.random() * 3);
        }
        //create the second tile

        tiles[a][b] = 2;
        getUpdatedBoard();

        // Make sure that this component has the keyboard focus
        setFocusable(true);
        requestFocusInWindow();
    }

    public void undo() {

        boardState.removeLast();
        tiles = boardState.getLast();

        savedScore.removeLast();
        score = savedScore.getLast();

        getUpdatedBoard();
        setFocusable(true);
        requestFocusInWindow();

    }

    public void save(String file) {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            tiles = boardState.getLast();

            bw.write("" + Arrays.deepToString(tiles));
            bw.newLine();
            bw.write("" + getScore());
            bw.close();

        } catch (Exception e) {
            throw new NoSuchElementException();
        }

        setFocusable(true);
        requestFocusInWindow();
    }

    public void load(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String tempScore = br.readLine();

            //score = Integer.parseInt(tempScore);
            //System.out.print(score);

        } catch (Exception e) {
            throw new NoSuchElementException();
        }
        setFocusable(true);
        requestFocusInWindow();
    }

    public int getScore() {

        return savedScore.getLast();

    }

    private Color getTileColor(int i) {
        if (i == 0) {
            return new Color(191, 175, 160);
        }

        if (i == 2) {
            return new Color(238, 209, 10);
        }

        if (i == 4) {
            return new Color(234, 226, 60);
        }

        if (i == 8) {
            return new Color(242, 177, 50);
        }

        if (i == 16) {
            return new Color(230, 142, 60);
        }

        if (i == 32) {
            return new Color(180, 131, 90);
        }

        if (i == 64) {
            return new Color(190, 89, 55);
        }

        if (i == 128) {
            return new Color(200, 160, 60);
        }

        if (i == 256) {
            return new Color(244, 207, 70);
        }

        if (i == 512) {
            return new Color(0, 196, 34);
        }

        if (i == 1028) {
            return new Color(20, 193, 10);
        }

        if (i == 2048) {
            return new Color(30, 90, 0);
        }
        return new Color(255, 255, 255);

    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
