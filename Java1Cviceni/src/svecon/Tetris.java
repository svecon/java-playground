/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zapoctak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author jethro
 */
public  class Tetris implements ActionListener{
    final JButton [][] plan;
    JFrame frame;
    JButton left;
    JButton right;
    long lastClick;
    int xPos;
    int yPos;
    Type type;
    boolean active;
    Random rnd;
    ActionListener timerFire;

    enum Type{LINE,SQUARE}


    private  void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Simple Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        Container game = new Container();
        game.setLayout(new GridLayout(10,10));
        Container buttons = new Container();
        buttons.setLayout(new GridLayout(1,2));

        for (int j=0;j<10;j++){
            for (int i=0;i<10;i++){
                plan[i][j]= new JButton();
                plan[i][j].setEnabled(false);
                plan[i][j].setBackground(Color.GRAY);
                game.add(plan[i][j]);
            }
        }

        left = new JButton("<-");
        right = new JButton("->");

        left.addActionListener(this);
        right.addActionListener(this);

        buttons.add(left);
        buttons.add(right);


        JButton start;
        start = new JButton("Jdu do toho!");
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                javax.swing.Timer timer;
                timer = new Timer(300, timerFire);
                timer.setRepeats(true);
                timer.start();
                ((JButton)ae.getSource()).setEnabled(false);

            }
        });

        pane.add(game,BorderLayout.NORTH);
        pane.add(buttons,BorderLayout.CENTER);
        pane.add(start,BorderLayout.SOUTH);

        frame.pack();
       // frame.setPreferredSize(new Dimension(500,500));
        frame.setVisible(true);
    }

    boolean canDown(Type t,int xPos,int yPos){
        if (yPos==9) return false;
        if (t.equals(Type.LINE)){
            for (int i=0;i<4;i++){
                if (!plan[xPos+i][yPos+1].getBackground().equals(Color.GRAY))
                    return false;
            }
        } else {
            if (!plan[xPos][yPos+1].getBackground().equals(Color.GRAY)) return false;
            if (!plan[xPos+1][yPos+1].getBackground().equals(Color.GRAY)) return false;
        }
        return true;
    }

    boolean canLeft(Type t,int xPos,int yPos){
        if (xPos==0) return false;
        if (t.equals(Type.LINE)){
            if (!plan[xPos-1][yPos].getBackground().equals(Color.GRAY)) return false;
        } else{
            if (!plan[xPos-1][yPos].getBackground().equals(Color.GRAY)) return false;
            if (!plan[xPos-1][yPos-1].getBackground().equals(Color.GRAY)) return false;
        }
        return true;
    }

    boolean canRight(Type t,int xPos,int yPos){
        if (t.equals(Type.LINE)){
            if (xPos==6) return false;
            if (!plan[xPos+4][yPos].getBackground().equals(Color.GRAY)) return false;
        } else{
            if (xPos==8) return false;
            if (!plan[xPos+2][yPos].getBackground().equals(Color.GRAY)) return false;
            if (!plan[xPos+2][yPos-1].getBackground().equals(Color.GRAY)) return false;
        }
        return true;
    }

    boolean canAdd(Type t){
        if (t.equals(Type.LINE)){
            for (int i=0;i<4;i++){
                if (!plan[3+i][0].getBackground().equals(Color.GRAY)) return false;
            }
        } else {

            if (!plan[4][0].getBackground().equals(Color.GRAY)) return false;
            if (!plan[4][1].getBackground().equals(Color.GRAY)) return false;
            if (!plan[5][0].getBackground().equals(Color.GRAY)) return false;
            if (!plan[5][1].getBackground().equals(Color.GRAY)) return false;
        }
        return true;
    }

    void moveDown(Type t, int xPos, int yPos){
        if (t.equals(Type.LINE)){
            for (int i=0;i<4;i++){
                plan[xPos+i][yPos].setBackground(Color.GRAY);
                plan[xPos+i][yPos+1].setBackground(Color.MAGENTA);
            }
        } else {
            plan[xPos][yPos-1].setBackground(Color.GRAY);
            plan[xPos][yPos+1].setBackground(Color.YELLOW);
            plan[xPos+1][yPos-1].setBackground(Color.GRAY);
            plan[xPos+1][yPos+1].setBackground(Color.YELLOW);
        }
    }

    void moveLeft(Type t, int xPos, int yPos){
        if (t.equals(Type.LINE)){
                plan[xPos+3][yPos].setBackground(Color.GRAY);
                plan[xPos-1][yPos].setBackground(Color.MAGENTA);
        } else {
            plan[xPos+1][yPos].setBackground(Color.GRAY);
            plan[xPos-1][yPos].setBackground(Color.YELLOW);
            plan[xPos+1][yPos-1].setBackground(Color.GRAY);
            plan[xPos-1][yPos-1].setBackground(Color.YELLOW);
        }
    }

    void moveRight(Type t, int xPos, int yPos){
        if (t.equals(Type.LINE)){
                plan[xPos][yPos].setBackground(Color.GRAY);
                plan[xPos+4][yPos].setBackground(Color.MAGENTA);
        } else {
            plan[xPos][yPos].setBackground(Color.GRAY);
            plan[xPos+2][yPos].setBackground(Color.YELLOW);
            plan[xPos][yPos-1].setBackground(Color.GRAY);
            plan[xPos+2][yPos-1].setBackground(Color.YELLOW);
        }
    }

    void add(Type t){
        if (t.equals(Type.LINE)){
            for (int i=0;i<4;i++){
                plan[3+i][0].setBackground(Color.MAGENTA);
            }
        }else{
            plan[4][0].setBackground(Color.yellow);
            plan[4][1].setBackground(Color.yellow);
            plan[5][0].setBackground(Color.yellow);
            plan[5][1].setBackground(Color.yellow);
        }
    }

    void clean(){
        for (int y=0;y<10;y++){
            boolean clean=true;
            for (int x=0;x<10;x++){
                if (plan[x][y].getBackground().equals(Color.GRAY)) {
                    clean=false;
                    break;
                }
            }
            if (clean){
                for (int y1 = y-1;y1 >= 0;y1--){
                    for (int x1 = 0;x1 < 10;x1++){
                        plan[x1][y1+1].setBackground(plan[x1][y1].getBackground());
                        plan[x1][y1].setBackground(Color.gray);
                    }
                }
            }
        }
    }


    public Tetris() {
        plan = new JButton[10][10];
        rnd = new Random();
        timerFire = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!active){
                    Type t;
                    t = (rnd.nextBoolean())?Type.LINE:Type.SQUARE;
                    if (canAdd(t)){
                        add(t);
                        type = t;
                        if (t.equals(Type.LINE)){
                            xPos = 3;
                            yPos = 0;
                        }else {
                            xPos = 4;
                            yPos = 1;
                        }
                        active = true;
                        return;
                    } else {
                        JOptionPane.showMessageDialog(frame,"Prohral jsi, salate!");
                        System.exit(0);
                    }
                }

                synchronized (plan) {
                    if (canDown(type, xPos, yPos)){
                        moveDown(type, xPos, yPos);
                        yPos++;
                    } else{
                        active=false;
                        clean();
                    }
                }

            }
        };

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!active) return;
        if ((System.nanoTime()-lastClick)<(100*1000*1000)) return;
        lastClick = System.nanoTime();
        if (ae.getSource().equals(left)){
            synchronized (plan){
                if (canLeft(type, xPos, yPos)) {
                    moveLeft(type, xPos, yPos);
                    xPos--;
                }
            }
        }else{
            synchronized (plan){
                if (canRight(type, xPos, yPos)) {
                    moveRight(type, xPos, yPos);
                    xPos++;
                }
            }
        }
    }



}
