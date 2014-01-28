/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vyukanasobilky;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author svecon
 */
public class DivisionPanel extends JPanel {

    private static class JLabelXXL extends JLabel
    {
        public JLabelXXL(String text) {
            super(text);
            
            setFont(new Font("Tahoma", Font.BOLD, 24));
        }
    }

    private final Settings settings;
    private int divident;
    private int factor;
    private int result;

    public DivisionPanel(Settings settings) {
        this.settings = settings;
    }

    public void initialize() {
        factor = NumberGenerator.getLargeNumber(settings.get("factor"));
        divident = NumberGenerator.getLargeNumber(settings.get("divident"));
        
        result = divident / factor;
        divident = result * factor; // discard residue
        
        createGUI();
        requestFocus();

        addKeyListener(new KeyListener() {

            /**
             * Handle the key typed event from the text field.
             */
            public void keyTyped(KeyEvent e) {
                displayInfo(e, "KEY TYPED: ");
            }

            /**
             * Handle the key-pressed event from the text field.
             */
            public void keyPressed(KeyEvent e) {
                displayInfo(e, "KEY PRESSED: ");
                Debugger.log("key pressed");
            }

            /**
             * Handle the key-released event from the text field.
             */
            public void keyReleased(KeyEvent e) {
                displayInfo(e, "KEY RELEASED: ");
            }

            private void displayInfo(KeyEvent e, String keyStatus) {

                //You should only rely on the key char if the event
                //is a key typed event.
                int id = e.getID();
                String keyString;
                if (id == KeyEvent.KEY_TYPED) {
                    char c = e.getKeyChar();
                    keyString = "key character = '" + c + "'";
                } else {
                    int keyCode = e.getKeyCode();
                    keyString = "key code = " + keyCode
                            + " ("
                            + KeyEvent.getKeyText(keyCode)
                            + ")";
                }

                Debugger.log(keyStatus + keyString);

                int modifiersEx = e.getModifiersEx();
                String modString = "extended modifiers = " + modifiersEx;
                String tmpString = KeyEvent.getModifiersExText(modifiersEx);
                if (tmpString.length() > 0) {
                    modString += " (" + tmpString + ")";
                } else {
                    modString += " (no extended modifiers)";
                }

                String actionString = "action key? ";
                if (e.isActionKey()) {
                    actionString += "YES";
                } else {
                    actionString += "NO";
                }

                String locationString = "key location: ";
                int location = e.getKeyLocation();
                if (location == KeyEvent.KEY_LOCATION_STANDARD) {
                    locationString += "standard";
                } else if (location == KeyEvent.KEY_LOCATION_LEFT) {
                    locationString += "left";
                } else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
                    locationString += "right";
                } else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
                    locationString += "numpad";
                } else { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
                    locationString += "unknown";
                }
            }
        });
    }

    private void createGUI() {
        this.removeAll();

        setLayout(new BorderLayout());

        JPanel computation = new JPanel(new GridBagLayout());
        this.add(computation, BorderLayout.CENTER);

        String[] dividentA = String.valueOf(divident).split("(?<=.)");
        JLabel lDigit;
        for (int i = 0; i < dividentA.length; i++) {
            lDigit = new JLabelXXL(dividentA[i]);
            computation.add(lDigit, Helper.gbc(i, 0));
        }

        lDigit = new JLabelXXL(":");
        computation.add(lDigit, Helper.gbc(dividentA.length, 0));

        String[] factorA = String.valueOf(factor).split("(?<=.)");
        for (int i = 0; i < factorA.length; i++) {
            lDigit = new JLabelXXL(factorA[i]);
            computation.add(lDigit, Helper.gbc(i + dividentA.length + 1, 0));
        }

        lDigit = new JLabelXXL("=");
        computation.add(lDigit, Helper.gbc(dividentA.length + factorA.length + 1, 0));

        String[] resultA = String.valueOf(result).split("(?<=.)");
        for (int i = 0; i < resultA.length; i++) {
            lDigit = new JLabelXXL(resultA[i]);
            computation.add(lDigit, Helper.gbc(i + dividentA.length + factorA.length + 2, 0));
        }

        /////// remainders
        int[][] remainders = new int[resultA.length][dividentA.length];
        int[] valueRemainsA = Helper.numberToDigits(divident);

        // find initial padding
        int initialPadding = 0;
        int tempPart = 0;
        for (int i = 0; i < dividentA.length; i++) {
            tempPart = tempPart * 10 + valueRemainsA[i];
            if (tempPart >= factor) {
                initialPadding = i;
                break;
            }
        }

        for (int y = 0; y < resultA.length; y++) {

            int smallestPart = 0;
            int padding = 0;
            for (int i = 0; i < initialPadding + y + 1; i++) {

                remainders[y][i] += valueRemainsA[i];
                smallestPart = smallestPart * 10 + valueRemainsA[i];
                if (smallestPart == 0) {
                    padding++;
                }

            }

            if (smallestPart >= factor) {
                int tempResult = smallestPart / factor;
                Helper.substractArrays(valueRemainsA, Helper.numberToDigits(tempResult * factor), padding);
            }

        }
        // 1766320:80=22079
        // -166
        // --063
        // ---632
        // ----720
        //
        for (int i = 1; i < remainders.length; i++) {
            boolean skippingLeadingZeros = true;
            for (int j = 0; j < initialPadding + i + 1; j++) {
                if (skippingLeadingZeros && remainders[i][j] == 0 && j < i) {
                    continue;
                } else if (skippingLeadingZeros) {
                    skippingLeadingZeros = false;
                }

                lDigit = new JLabelXXL(String.valueOf(remainders[i][j]));
                computation.add(lDigit, Helper.gbc(j, i));
            }
        }

    }

}
