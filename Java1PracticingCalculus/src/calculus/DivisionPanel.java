package calculus;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

/**
 * Panel for exercising division
 *
 * @author svecon
 */
public class DivisionPanel extends CompetitionPanel {

    /**
     * Current Divident
     */
    private long divident;
    /**
     * Current factor
     */
    private long factor;
    /**
     * Current result
     */
    private long result;

    /**
     * Initial padding for division - how many digits to take for first digit
     */
    private int initialPadding;

    /**
     * Constructor
     *
     * @param main Container with CardLayout
     * @param settings Setting wrapper
     */
    public DivisionPanel(Container main, Settings settings) {
        super(main, settings);
    }

    @Override
    void generateNewProblem() {
        while ((factor = NumberGenerator.get(settings.get("factor"))) == 1) {
        }
        divident = NumberGenerator.get(settings.get("divident"));

        result = divident / factor;
        divident = result * factor; // discard residue
    }

    @Override
    void solveMatrix() {

        int resultLength = String.valueOf(result).length();
        int dividentLength = String.valueOf(divident).length();

        matrix = new int[resultLength][dividentLength];
        int[] valueRemainsA = Helper.numberToDigits(divident);

        // find initial padding
        initialPadding = 0;
        int tempPart = 0;
        for (int i = 0; i < dividentLength; i++) {
            tempPart = tempPart * 10 + valueRemainsA[i];
            if (tempPart >= factor) {
                initialPadding = i;
                break;
            }
        }

        // compute remainders
        for (int y = 0; y < resultLength; y++) {

            int smallestPart = 0;
            for (int i = 0; i < initialPadding + y + 1; i++) {
                matrix[y][i] += valueRemainsA[i];
                smallestPart = smallestPart * 10 + valueRemainsA[i];

                if (smallestPart >= factor) {
                    long tempResult = smallestPart / factor;
                    Helper.substractArrays(valueRemainsA, Helper.numberToDigits(tempResult * factor), i);
                    break;
                }
            }
        }
    }

    @Override
    void prepareNumberSequence() {
        numberSequence = new LinkedList<>();
        LinkedList<HiddenNumber> hiddenNumbersResult = new LinkedList<>();

        String[] factorA = Helper.numberToStringArray(factor);
        String[] resultA = Helper.numberToStringArray(result);
        for (int i = 0; i < resultA.length; i++) {
            hiddenNumbersResult.add(new HiddenNumber(i + String.valueOf(divident).length() + factorA.length + 2, 0, resultA[i]));
        }

        // add to queue
        for (int i = 1; i < matrix.length; i++) {
            // take result num
            numberSequence.add(hiddenNumbersResult.pollFirst());

            for (int j = initialPadding + i - 1; j >= 0; j--) {
                if (matrix[i][j] == 0 && j < i) {
                    break;
                }

                numberSequence.add(new HiddenNumber(j, i, String.valueOf(matrix[i][j])));
            }
            // rewrite last num one down
            numberSequence.add(new HiddenNumber(initialPadding + i, i, String.valueOf(matrix[i][initialPadding + i])));
        }
        // take the last result num
        numberSequence.add(hiddenNumbersResult.pollFirst());

        // write 0 at the bottom
        numberSequence.add(new HiddenNumber(matrix[0].length - 1, matrix.length, "0"));
    }

    @Override
    void createGUI() {
        // String.valueOf\(([a-z]+)\).split\("\(\?<=\.\)"\);

        String[] dividentA = Helper.numberToStringArray(divident);
        JLabel lDigit;
        for (int i = 0; i < dividentA.length; i++) {
            lDigit = new JLabelXXL(dividentA[i]);
            computation.add(lDigit, Helper.gbc(i, 0));
        }

        lDigit = new JLabelXXL(":");
        computation.add(lDigit, Helper.gbc(dividentA.length, 0));

        String[] factorA = Helper.numberToStringArray(factor);
        for (int i = 0; i < factorA.length; i++) {
            lDigit = new JLabelXXL(factorA[i]);
            computation.add(lDigit, Helper.gbc(i + dividentA.length + 1, 0));
        }

        lDigit = new JLabelXXL("=");
        computation.add(lDigit, Helper.gbc(dividentA.length + factorA.length + 1, 0));
    }
}
