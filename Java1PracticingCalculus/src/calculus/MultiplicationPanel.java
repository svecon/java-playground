package calculus;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

/**
 * Panel for exercising Multiplication
 *
 * @author svecon
 */
public class MultiplicationPanel extends CompetitionPanel {

    /**
     * Current Multiple (the top one)
     */
    private long multiple1;
    /**
     * Current Multiple (the bottom one)
     */
    private long multiple2;
    /**
     * The result of multiplication
     */
    private long result;

    /**
     * Constructor
     *
     * @param main Container with CardLayout
     * @param settings Setting wrapper
     */
    public MultiplicationPanel(Container main, Settings settings) {
        super(main, settings);
    }

    @Override
    void generateNewProblem() {
        result = 0;
        while (result < 10) {
            multiple1 = NumberGenerator.get(settings.get("multiple"));
            while ((multiple2 = NumberGenerator.get(settings.get("multiplebottom"))) == 1) {
            }

            result = multiple1 * multiple2;
        }
    }

    @Override
    void solveMatrix() {
        //     222
        //    *333
        //   -----
        //     666
        //    666
        //   666
        //   -----
        //   73926

        int[] multiple1A = Helper.numberToDigits(multiple1);
        int[] multiple2A = Helper.numberToDigits(multiple2);
        int resultLength = String.valueOf(result).length();

        matrix = new int[multiple2A.length][resultLength];

        int j = 0;
        for (int x = multiple2A.length - 1; x >= 0; x--) {
            int i = j;
            for (int y = multiple1A.length - 1; y >= 0; y--) {
                matrix[j][resultLength - 1 - i] += multiple1A[y] * multiple2A[x];
                i++;
            }
            Helper.shiftOverflowLeft(matrix[j]);
            j++;
        }
    }

    @Override
    void prepareNumberSequence() {
        numberSequence = new LinkedList<>();

        String[] multiple1A = Helper.numberToStringArray(multiple1);
        String[] multiple2A = Helper.numberToStringArray(multiple2);
        String[] resultA = Helper.numberToStringArray(result);

        for (int y = 0; y < multiple2A.length; y++) {

            int findLatestIndex = 0;

            for (int x = 0; x < resultA.length; x++) {
                if (matrix[y][x] != 0) {
                    break;
                }
                findLatestIndex++;
            }

            for (int x = resultA.length - 1 - y; x >= findLatestIndex; x--) {
                numberSequence.add(new HiddenNumber(x, 3 + y, String.valueOf(matrix[y][x])));
            }
        }

        if (multiple2 < 10) {
            return; // dont create adding step
        }

        numberSequence.add(new HiddenLine(resultA.length, 3 + multiple1A.length, ""));

        for (int i = resultA.length - 1; i >= 0; i--) {
            numberSequence.add(new HiddenNumber(i, 4 + multiple1A.length, String.valueOf(resultA[i])));
        }

    }

    @Override
    void createGUI() {

        String[] multiple1A = Helper.numberToStringArray(multiple1);
        String[] multiple2A = Helper.numberToStringArray(multiple2);
        int resultLength = String.valueOf(result).length();

        JLabel lDigit;
        for (int i = 0; i < multiple1A.length; i++) {
            lDigit = new JLabelXXL(multiple1A[i]);
            computation.add(lDigit, Helper.gbc(resultLength - multiple1A.length + i, 0));
        }

        for (int i = 0; i < multiple2A.length; i++) {
            lDigit = new JLabelXXL(multiple2A[i]);
            computation.add(lDigit, Helper.gbc(resultLength - multiple2A.length + i, 1));
        }

        drawLine(resultLength, 2);

        computation.add(new JLabelXXL("*"), Helper.gbc(resultLength - multiple2A.length - 1, 1));

    }
}
