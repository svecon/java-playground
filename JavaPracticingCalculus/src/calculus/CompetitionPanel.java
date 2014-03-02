package calculus;

import calculus.listeners.ActionListenerChangeCard;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.*;

/**
 * Abstract class with default behaviour for testing calculation
 *
 * @author svecon
 */
public abstract class CompetitionPanel extends JPanel {

    /**
     * Main container for the program [used to switch cards]
     */
    final Container main;
    /**
     * Panel with GridBagLayout for calculation
     */
    JPanel computation;
    /**
     * Reference to settings object created earlier
     */
    final Settings settings;
    /**
     * Matrix of digits: precalculation for given problem
     */
    int[][] matrix;

    /**
     * Number of total mistakes during testing
     */
    int mistakes;
    /**
     * Time when the testing started
     */
    Date start;
    /**
     * Number of current problem
     */
    int currentProblem;
    /**
     * Number of problems during which there were mistakes
     */
    int wrongProblems;
    /**
     * Reference for cursor label
     */
    JLabel cursor;
    /**
     * Reference for status bar label
     */
    JLabel statusBar;
    /**
     * List of correct numbers to complete a problem
     */
    LinkedList<HiddenNumber> numberSequence;
    /**
     * Was there already a mistake in current problem?
     */
    boolean isMistakeInCurrent;

    /**
     * Initialize Competition
     *
     * @param main Reference to container with Card layout
     * @param settings Settings wrapper
     */
    public CompetitionPanel(Container main, Settings settings) {
        setLayout(new BorderLayout());

        this.main = main;
        this.settings = settings;

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (numberSequence.isEmpty()) {
                    if (currentProblem == CompetitionPanel.this.settings.get("count")) {
                        paintResults();
                    } else {
                        initializeNextProblem();
                    }
                    return;
                }

                HiddenNumber hn = numberSequence.peekFirst();

                if (hn.label.equals(String.valueOf(e.getKeyChar()))) {
                    JLabel lDigit = new JLabelXXL(hn.label);
                    computation.add(lDigit, Helper.gbc(hn.x, hn.y));
                    computation.updateUI();

                    numberSequence.pollFirst();
                    moveCursor(false);
                } else {
                    cursor.setText(String.valueOf(e.getKeyChar()));
                    cursor.setBackground(Color.pink);
                    cursor.setForeground(Color.red);

                    mistakes++;
                    if (isMistakeInCurrent == false) {
                        wrongProblems++;
                    }
                    isMistakeInCurrent = true;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
    }

    /**
     * Resets counters for new competition
     */
    public void reset() {
        mistakes = 0;
        start = new Date();
        currentProblem = 0;
        wrongProblems = 0;

        initializeNextProblem();
    }

    /**
     * Resets and initializes everything for next problem
     */
    void initializeNextProblem() {
        currentProblem++;
        isMistakeInCurrent = false;

        removeAll();
        if (computation != null) {
            computation.removeAll();
        }

        computation = new JPanel(new GridBagLayout());
        this.add(computation, BorderLayout.CENTER);

        generateNewProblem();
        solveMatrix();
        prepareNumberSequence();
        createGUI();
        moveCursor(true);
        requestFocus();

        if (statusBar != null) {
            remove(statusBar);
        }
        statusBar = statusBar = new JLabel("Příklad " + currentProblem + "/" + settings.get("count"));
        this.add(statusBar, BorderLayout.PAGE_END);
    }

    /**
     * Solves problem and prepares matrix of digits for evaluation
     */
    abstract void solveMatrix();

    /**
     * Creates a list with correct sequence of digits to solve current problem
     */
    abstract void prepareNumberSequence();

    /**
     * Randomly generate new problem with given Settings
     */
    abstract void generateNewProblem();

    /**
     * Creates Grid, labels and buttons
     */
    abstract void createGUI();

    /**
     * Initialize Results pane with current statistics
     */
    void paintResults() {
        remove(computation);
        add(new ResultsPanel(settings.get("count"), start, mistakes, wrongProblems), BorderLayout.CENTER);

        this.updateUI();
    }

    /**
     * Draws a horizontal line made of dashes in a GridBagLayout
     *
     * @param length horizontal width
     * @param y y-coordinate
     */
    void drawLine(int length, int y) {
        for (int i = 0; i < length; i++) {
            computation.add(new JLabelXXL("—"), Helper.gbc(i, y));
        }
    }

    /**
     * Move cursor one step in a NumberSequence
     *
     * @param init is this a first step?
     */
    void moveCursor(boolean init) {

        if (numberSequence.isEmpty()) {
            computation.remove(cursor);
            return;
        }

        if (init) {
            cursor = new JLabelXXL("_");
            cursor.setOpaque(true);
        }

        HiddenNumber hn = numberSequence.peekFirst();

        if (hn instanceof HiddenLine) {
            drawLine(hn.x, hn.y);
            numberSequence.pollFirst();
            hn = numberSequence.peekFirst();
        }

        cursor.setBackground(Color.white);
        cursor.setForeground(Color.white);
        cursor.setText("_");

        if (!init) {
            computation.remove(cursor);
        }

        computation.add(cursor, Helper.gbc(hn.x, hn.y));

        computation.updateUI();

    }

    /**
     * Classic JLabel with predefined Font
     */
    static class JLabelXXL extends JLabel {

        public JLabelXXL(String text) {
            super(text);

            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("Tahoma", Font.BOLD, 24));
        }
    }

    /**
     * Results pane showing various statistics
     */
    class ResultsPanel extends JPanel {

        /**
         * Creates a result Panel with GridBagLayout
         *
         * @param total Total number of problems solved
         * @param start Time when the computation started
         * @param mistakes Number of mistakes
         * @param problemsWrong Number of problems where mistakes were
         */
        public ResultsPanel(int total, Date start, int mistakes, int problemsWrong) {
            setLayout(new GridBagLayout());

            Date now = new Date();
            long totalMili = now.getTime() - start.getTime();
            double totalSeconds = (1.0 * totalMili) / 1000;

            add(new JLabel("Počet příkladů: "), Helper.gbc(0, 0));
            add(new JLabelXXL("" + total), Helper.gbc(1, 0));

            add(new JLabel("Počet chybných příkladů: "), Helper.gbc(0, 1));
            add(new JLabelXXL("" + problemsWrong), Helper.gbc(1, 1));

            add(new JLabel("Počet chyb celkem: "), Helper.gbc(0, 2));
            add(new JLabelXXL("" + mistakes), Helper.gbc(1, 2));

            DecimalFormat df2 = new DecimalFormat("#.##");
            add(new JLabel("Úspěšnost: "), Helper.gbc(0, 3));
            add(new JLabelXXL("" + df2.format((1.0 * (total - problemsWrong)) / total * 100) + "%"), Helper.gbc(1, 3));

            DecimalFormat df1 = new DecimalFormat("#.#");
            add(new JLabel("Čas na příklad: "), Helper.gbc(0, 4));
            add(new JLabelXXL("" + df1.format(totalSeconds / total) + "s"), Helper.gbc(1, 4));

            add(new JLabel("Celkový čas: "), Helper.gbc(0, 5));
            add(new JLabelXXL("" + df1.format(totalSeconds) + "s"), Helper.gbc(1, 5));

            JButton b = new JButton("Nová hra");
            b.setFont(b.getFont().deriveFont(17f));
            b.addActionListener(new ActionListenerChangeCard(main, Calculus.CardStartScreen));
            add(b, Helper.gbc(0, 6, 2, GridBagConstraints.BOTH, GridBagConstraints.LINE_START, new Insets(25, 0, 0, 0)));
        }

    }

    /**
     * Wrapper class for correct Number Sequence List
     */
    static class HiddenNumber {

        public int x;
        public int y;
        public String label;

        public HiddenNumber(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }
    }

    /**
     * Wrapper class for Horizontal line made of dashes
     */
    static class HiddenLine extends HiddenNumber {

        public HiddenLine(int x, int y, String label) {
            super(x, y, label);
        }
    }

}
