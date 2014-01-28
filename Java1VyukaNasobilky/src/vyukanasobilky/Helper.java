/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vyukanasobilky;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 *
 * @author svecon
 */
public class Helper {

    public static GridBagConstraints gbc(int x, int y, int width, int fill, int anchor, Insets inset) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = x;
        g.gridy = y;
        g.gridwidth = width;
        g.ipadx = 10;
        g.ipady = 3;
        g.fill = fill;
        g.anchor = anchor;
        g.insets = inset;
        return g;
    }

    public static GridBagConstraints gbc(int x, int y, int width, int fill, int anchor) {
        return gbc(x, y, width, fill, anchor, new Insets(0, 0, 0, 0));
    }

    public static GridBagConstraints gbc(int x, int y, int width, int fill) {
        return gbc(x, y, width, fill, GridBagConstraints.LINE_START, new Insets(0, 0, 0, 0));
    }

    public static GridBagConstraints gbc(int x, int y, int width) {
        return gbc(x, y, width, GridBagConstraints.HORIZONTAL, GridBagConstraints.LINE_START);
    }

    public static GridBagConstraints gbc(int x, int y) {
        return gbc(x, y, 1);
    }

    public static int[] numberToDigits(int num) {
        int length = String.valueOf(num).length();

        int[] arr = new int[length];

        for (int i = length - 1; i >= 0; i--) {
            arr[i] = num % 10;
            num /= 10;
        }

        return arr;
    }

    public static void substractArrays(int[] from, int[] what, int padding) {
        for (int i = 0; i < what.length; i++) {
            from[i + padding] -= what[i];
        }

        for (int i = from.length - 1; i >= 0; i--) {
            if (from[i] < 0) {
                from[i] += 10;
                from[i - 1]--;
            }
        }
    }

    public static void addArrays(int[] to, int[] what, int padding) {
        for (int i = 0; i < what.length; i++) {
            to[i + padding] += what[i];
        }

        for (int i = to.length - 1; i >= 0; i--) {
            if (to[i] >= 10) {
                to[i] -= 10;
                to[i - 1]++;
            }
        }
    }

}
