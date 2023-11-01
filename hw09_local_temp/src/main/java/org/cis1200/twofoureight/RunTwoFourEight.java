package org.cis1200.twofoureight;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class RunTwoFourEight implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);

        // Status panel

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        // Game board
        final GameBoardFinal board = new GameBoardFinal(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        //add the score
        JLabel score = new JLabel(String.valueOf(board.getScore()));
        control_panel.add(score);

        board.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT
                        || k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT
                        || k == KeyEvent.VK_W || k == KeyEvent.VK_DOWN
                        || k == KeyEvent.VK_S || k == KeyEvent.VK_UP) {
                    score.setText(String.valueOf(board.getScore()));
                }
            }
            public void keyReleased(KeyEvent e) {

            }
        });

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> board.undo());
        control_panel.add(undo);

        final JButton save = new JButton("Save");
        save.addActionListener(e -> board.save("src/main/java/org/cis1200/" +
                "twofoureight/savedGame.txt"));
        control_panel.add(save);

        final JButton load = new JButton("Load");
        load.addActionListener(e -> board.load("src/main/java/org/cis1200/" +
                "twofoureight/savedGame.txt"));
        control_panel.add(load);



        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        board.setFocusable(true);
        board.requestFocusInWindow();


        // Start the game
        board.reset();
        JOptionPane.showMessageDialog(frame, "Hi this is 2048! The game works " +
                "by moving your mouse keys in order to interact with the board. The " +
                "aim of this game is to get the highest score. Good Luck!");
    }

}
