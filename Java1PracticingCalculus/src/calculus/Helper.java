package calculus;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Helper class containing useful methods to achieve cleaner and shorter code.
 *
 * @author svecon
 */
public class Helper {

    /**
     * GridBagConstraints creation with some predefined values.
     *
     * @param x x-position in grid
     * @param y y-position in grid
     * @param width x-overflow
     * @param fill fill mode
     * @param anchor alignement
     * @param inset padding
     * @return GridBagConstraints
     */
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

    /**
     * GridBagConstraints creation with some predefined values.
     *
     * @param x x-position in grid
     * @param y y-position in grid
     * @param width x-overflow
     * @param fill fill mode
     * @param anchor alignement
     * @return GridBagConstraints
     */
    public static GridBagConstraints gbc(int x, int y, int width, int fill, int anchor) {
        return gbc(x, y, width, fill, anchor, new Insets(0, 0, 0, 0));
    }

    /**
     * GridBagConstraints creation with some predefined values.
     *
     * @param x x-position in grid
     * @param y y-position in grid
     * @param width x-overflow
     * @param fill fill mode
     * @return GridBagConstraints
     */
    public static GridBagConstraints gbc(int x, int y, int width, int fill) {
        return gbc(x, y, width, fill, GridBagConstraints.LINE_START, new Insets(0, 0, 0, 0));
    }

    /**
     * GridBagConstraints creation with some predefined values.
     *
     * @param x x-position in grid
     * @param y y-position in grid
     * @param width x-overflow
     * @return GridBagConstraints
     */
    public static GridBagConstraints gbc(int x, int y, int width) {
        return gbc(x, y, width, GridBagConstraints.HORIZONTAL, GridBagConstraints.LINE_START);
    }

    /**
     * GridBagConstraints creation with some predefined values.
     *
     * @param x x-position in grid
     * @param y y-position in grid
     * @return GridBagConstraints
     */
    public static GridBagConstraints gbc(int x, int y) {
        return gbc(x, y, 1);
    }

    /**
     * Takes a number and converts it into an array of digits
     *
     * @param num number to convert
     * @return array of digits
     */
    public static int[] numberToDigits(long num) {
        int length = String.valueOf(num).length();

        int[] arr = new int[length];

        for (int i = length - 1; i >= 0; i--) {
            arr[i] = (int) (num % 10);
            num /= 10;
        }

        return arr;
    }

    /**
     * Subtract one array of digits from another
     *
     * @param from Array to subtract from
     * @param what Array to subtract with
     * @param padding
     */
    public static void substractArrays(int[] from, int[] what, int padding) {
        padding = padding + 1 - what.length;

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

    /**
     * Sum two array of digits
     *
     * @param to one array
     * @param what seconds array
     * @param padding how much is seconds array shifted from the first
     */
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

    /**
     * Correct number of digits by shifting overflows to left
     *
     * @param arr number of digits
     */
    public static void shiftOverflowLeft(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }
    }

    /**
     * Converts a number to array of digits as strings
     *
     * @param num number to convert
     * @return array of string digits
     */
    public static String[] numberToStringArray(long num) {
        return String.valueOf(num).split("(?<=.)");
    }

}
