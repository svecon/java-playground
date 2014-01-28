/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less12;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author svecon
 */
public class Mines {

    static class MineClicked implements ActionListener {

        int x;
        int y;
        
        public MineClicked(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            minesB[x][y].setEnabled(false);
        }
        
    }

    static int[][] mines;
    static JButton[][] minesB;

    private static void createAndShowGUI() {
//        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Mines");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(label);
//        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
        
        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);
        
        GridLayout grid = new GridLayout(20, 20);
        JPanel gridPanel = new JPanel(grid);
        frame.add(gridPanel, BorderLayout.CENTER);

        mines = new int[20][20];
        minesB = new JButton[20][20];

        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines[i].length; j++) {
                JButton button = new JButton();
                button.addActionListener(new MineClicked(i, j));
                minesB[i][j] = button;
                gridPanel.add(button);
            }
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
