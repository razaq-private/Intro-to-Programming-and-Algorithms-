package org.cis1200.mushroom;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class GameTest {

    private static int[][] tiles;

    @Test
    public static void main(String[] args) throws IOException {
        tiles = new int[4][4];
        tiles[3][3] = 16;
        tiles[2][3] = 4;
        tiles[1][3] = 4;
        tiles[0][3] = 4;
        System.out.println(Arrays.deepToString(tiles));
        mergeUp();
        System.out.println(Arrays.deepToString(tiles));

    }
    @Test
    private static void moveUp() {
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

    @Test
    private static void mergeUp() {
        for (int j = 0; j < 4; j++)  {
            for (int i = 0; i < 3; i ++) {
                moveUp();
                int tileAbove = tiles[i + 1][j];
                if (tiles[i][j] == tileAbove && tileAbove != 0) {
                    tiles[i][j] = 2 * tileAbove;
                    tiles[i + 1][j] = 0;


                }
            }
        }
    }

    private static void moveDown() {
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

    private static void mergeDown() {
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i > 0; i--) {
                moveDown();
                int tileBelow = tiles[i - 1][j];
                if (tiles[i][j] == tileBelow && tileBelow != 0) {
                    tiles[i][j] = 2 * tileBelow;
                    tiles[i - 1][j] = 0;


                }
            }
        }
    }

    private static void moveLeft() {
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

    private static void mergeLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                moveLeft();
                int leftTile = tiles[i][j + 1];
                if (tiles[i][j] == leftTile && leftTile != 0) {
                    tiles[i][j] = 2 * leftTile;
                    tiles[i][j + 1] = 0;


                }
            }
        }
    }

    private static void moveRight() {
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

                }
            }
        }
    }

}
