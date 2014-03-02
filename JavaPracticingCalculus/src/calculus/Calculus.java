/**
 * This program has been developed to help children in primary school exercise
 * their division and multiplication skills.
 *
 * This program features: - division exercise - multiplication exercise -
 * settings for however high or customised numbers so children may specify their
 * ability level - results page where children may see their success rate and
 * more
 *
 */
/**
 * Basic package containing executable JFrame, one JPanel for multiplication and
 * one for division
 *
 * @author Ondřej Švec
 * @version 1.0.0
 * @java JDK 7 or higher
 */
package calculus;

import calculus.listeners.ActionListenerChangeCard;
import calculus.listeners.ChangeListenerSettingsSlider;
import calculus.listeners.ActionListenerSettings;
import calculus.listeners.DocumentListenerSettingsText;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Main application JFrame
 *
 * @author svecon
 */
public class Calculus extends JFrame {

    /**
     * Container with CardsLayout
     */
    final private Container container;

    /**
     * Card constant for About page
     */
    public static final String CardAbout = "cardabout";
    /**
     * Card constant for StartScreen page
     */
    public static final String CardStartScreen = "cardstartscreen";
    /**
     * Card constant for Settings of Division page
     */
    public static final String CardSettingsDivision = "cardsettingsdivision";
    /**
     * Card constant for Settings of Multiplication page
     */
    public static final String CardSettingsMultiplication = "cardsettingsmultiplication";
    /**
     * Card constant for Division page
     */
    public static final String CardDivision = "carddivision";
    /**
     * Card constant for Multiplication page
     */
    public static final String CardMultiplication = "cardmultiplication";

    /**
     * Constructor - creates a default program window
     */
    public Calculus() {
        super("Výuka násobení a dělení");

        ImageIcon icon = new ImageIcon(getClass().getResource("divide-icon.png"));
        setIconImage(icon.getImage());

        container = this.getContentPane();

        this.setSize(600, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new CardLayout());

        createAndShowGUI();
    }

    /**
     * Creates all menus, buttons and setting radios
     */
    private void createAndShowGUI() {
        /**
         * MENUBAR
         */
        JMenuBar menuBar = new JMenuBar();

        JMenu menuStart = new JMenu("Program");
        menuBar.add(menuStart);

        JMenuItem menuNewGame = new JMenuItem("Nová hra");
        menuStart.add(menuNewGame);
        menuNewGame.addActionListener(new ActionListenerChangeCard(container, CardStartScreen));
        menuNewGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));

        JMenuItem menuAbout = new JMenuItem("O autorovi");
        menuStart.add(menuAbout);
        menuAbout.addActionListener(new ActionListenerChangeCard(container, CardAbout));
        menuAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));

        JMenuItem menuQuit = new JMenuItem("Konec");
        menuStart.add(menuQuit);
        menuQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menuQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));

        Font fMenu = menuStart.getFont().deriveFont(17f);
        menuStart.setFont(fMenu);
        menuNewGame.setFont(fMenu);
        menuAbout.setFont(fMenu);
        menuQuit.setFont(fMenu);

        this.setJMenuBar(menuBar);

        /**
         * START SCREEN
         */
        JPanel pStartScreen = new JPanel(new GridBagLayout());

        JButton bStartDivision = new JButton("Dělení");
        pStartScreen.add(bStartDivision, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(25, 0, 25, 0), 25, 50));
        bStartDivision.addActionListener(new ActionListenerChangeCard(container, CardSettingsDivision));
        JButton bStartMultiplication = new JButton("Násobení");
        pStartScreen.add(bStartMultiplication, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(25, 0, 25, 0), 25, 50));
        bStartMultiplication.addActionListener(new ActionListenerChangeCard(container, CardSettingsMultiplication));

        Font fButton = bStartDivision.getFont().deriveFont(17f);
        bStartDivision.setFont(fButton);
        bStartMultiplication.setFont(fButton);

        container.add(pStartScreen, CardStartScreen);

        /**
         * SETTINGS INIT
         */
        JPanel pDivision = new JPanel();
        JLabel lDivident = new JLabel();
        JLabel lFactor = new JLabel();
        JRadioButton chdTens = new JRadioButton();
        JRadioButton chfUnits = new JRadioButton();
        JRadioButton chdHundreds = new JRadioButton();
        JRadioButton chfTens = new JRadioButton();
        JRadioButton chdThousands = new JRadioButton();
        JRadioButton chdMillions = new JRadioButton();
        JRadioButton chfCustom = new JRadioButton();
        JRadioButton chdCustom = new JRadioButton();
        JTextField tdCustom = new JTextField();
        JTextField tfCustom = new JTextField();

        JPanel pMultiplication = new JPanel();
        JLabel lmFactor = new JLabel();
        JRadioButton chmMilions = new JRadioButton();
        JRadioButton chmTens = new JRadioButton();
        JRadioButton chmThousands = new JRadioButton();
        JRadioButton chmHundreds = new JRadioButton();
        JRadioButton chmCustom = new JRadioButton();
        JTextField tmCustom = new JTextField();
        JRadioButton chmmUnits = new JRadioButton();
        JRadioButton chmmTens = new JRadioButton();
        JRadioButton chmmHundreds = new JRadioButton();
        JRadioButton chmmCustom = new JRadioButton();
        JTextField tmmCustom = new JTextField();

        Settings settingsDivision = new Settings(Settings.DIVISION);
        Settings settingsMultiplication = new Settings(Settings.MULTIPLICATION);

        /**
         * DIVISION SETTINGS
         */
        pDivision.setLayout(new GridBagLayout());

        final DivisionPanel dpanel = new DivisionPanel(container, settingsDivision);
        container.add(dpanel, CardDivision);

        JLabel ldNumberOfExcercises = new JLabel("Počet příkladů");
        pDivision.add(ldNumberOfExcercises, Helper.gbc(0, 0, 1));

        JSlider sldNumberOfExcercises = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);
        sldNumberOfExcercises.setMajorTickSpacing(5);
        sldNumberOfExcercises.setMinorTickSpacing(1);
        sldNumberOfExcercises.setPaintTicks(true);
        sldNumberOfExcercises.setPaintLabels(true);
        sldNumberOfExcercises.setPreferredSize(new Dimension(350, 75));
        sldNumberOfExcercises.addChangeListener(new ChangeListenerSettingsSlider("count", settingsDivision));
        pDivision.add(sldNumberOfExcercises, Helper.gbc(0, 1, 2));

        ButtonGroup bgdSize = new ButtonGroup();
        lDivident.setText("Dělenec");
        pDivision.add(lDivident, Helper.gbc(0, 2, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));
        chdTens.setText("Desítky");
        chdTens.setSelected(true);
        chdTens.addActionListener(new ActionListenerSettings(99, "divident", settingsDivision, tdCustom));
        pDivision.add(chdTens, Helper.gbc(0, 3));
        chdHundreds.setText("Stovky");
        chdHundreds.addActionListener(new ActionListenerSettings(999, "divident", settingsDivision, tdCustom));
        pDivision.add(chdHundreds, Helper.gbc(0, 4));
        chdThousands.setText("Tisíce");
        chdThousands.addActionListener(new ActionListenerSettings(9999, "divident", settingsDivision, tdCustom));
        pDivision.add(chdThousands, Helper.gbc(0, 5));
        chdMillions.setText("Miliony");
        chdMillions.addActionListener(new ActionListenerSettings(9999999, "divident", settingsDivision, tdCustom));
        pDivision.add(chdMillions, Helper.gbc(0, 6));
        chdCustom.setText("Vlastní");
        chdCustom.addActionListener(new ActionListenerSettings(-1, "divident", settingsDivision, tdCustom));
        pDivision.add(chdCustom, Helper.gbc(0, 7));
        tdCustom.setText("9");
        tdCustom.setEnabled(false);
        tdCustom.setPreferredSize(new Dimension(100, 25));
        tdCustom.getDocument().addDocumentListener(new DocumentListenerSettingsText("divident", settingsDivision, tdCustom));
        pDivision.add(tdCustom, Helper.gbc(0, 8, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, new Insets(0, 0, 0, 75)));
        bgdSize.add(chdTens);
        bgdSize.add(chdHundreds);
        bgdSize.add(chdThousands);
        bgdSize.add(chdMillions);
        bgdSize.add(chdCustom);

        ButtonGroup bgfSize = new ButtonGroup();
        lFactor.setText("Dělitel");
        pDivision.add(lFactor, Helper.gbc(1, 2, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));
        chfUnits.setText("Jednotky");
        chfUnits.setSelected(true);
        chfUnits.addActionListener(new ActionListenerSettings(9, "factor", settingsDivision, tfCustom));
        pDivision.add(chfUnits, Helper.gbc(1, 3));
        chfTens.setText("Desítky");
        chfTens.addActionListener(new ActionListenerSettings(99, "factor", settingsDivision, tfCustom));
        pDivision.add(chfTens, Helper.gbc(1, 4));
        chfCustom.setText("Vlastní");
        chfCustom.addActionListener(new ActionListenerSettings(-1, "factor", settingsDivision, tfCustom));
        pDivision.add(chfCustom, Helper.gbc(1, 7));
        tfCustom.setText("9");
        tfCustom.setEnabled(false);
        tfCustom.setPreferredSize(new Dimension(100, 25));
        tfCustom.getDocument().addDocumentListener(new DocumentListenerSettingsText("factor", settingsDivision, tfCustom));
        pDivision.add(tfCustom, Helper.gbc(1, 8, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, new Insets(0, 0, 0, 75)));
        bgfSize.add(chfUnits);
        bgfSize.add(chfTens);
        bgfSize.add(chfCustom);

        JButton bdStart = new JButton("Spustit dělení");
        bdStart.setFont(fButton);
        bdStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) container.getLayout()).show(container, CardDivision);
                dpanel.reset();
            }
        });
        pDivision.add(bdStart, Helper.gbc(0, 9, 2, GridBagConstraints.BOTH, GridBagConstraints.LINE_START, new Insets(25, 0, 0, 0)));

        Font fLabel = lDivident.getFont().deriveFont(16f);
        lDivident.setFont(fLabel);
        lFactor.setFont(fLabel);
        ldNumberOfExcercises.setFont(fLabel);

        Font fRadio = chdTens.getFont().deriveFont(14f);
        chdTens.setFont(fRadio);
        chdHundreds.setFont(fRadio);
        chdThousands.setFont(fRadio);
        chdMillions.setFont(fRadio);
        chdCustom.setFont(fRadio);

        chfUnits.setFont(fRadio);
        chfTens.setFont(fRadio);
        chfCustom.setFont(fRadio);

        container.add(pDivision, CardSettingsDivision);

        /**
         * MULTIPLICATION SETTINGS
         */
        pMultiplication.setLayout(new java.awt.GridBagLayout());

        final MultiplicationPanel mpanel = new MultiplicationPanel(container, settingsMultiplication);
        container.add(mpanel, CardMultiplication);

        JLabel lmNumberOfExcercises = new JLabel("Počet příkladů");
        lmNumberOfExcercises.setFont(fLabel);
        pMultiplication.add(lmNumberOfExcercises, Helper.gbc(0, 0, 1));

        JSlider slmNumberOfExcercises = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);
        slmNumberOfExcercises.setMajorTickSpacing(5);
        slmNumberOfExcercises.setMinorTickSpacing(1);
        slmNumberOfExcercises.setPaintTicks(true);
        slmNumberOfExcercises.setPaintLabels(true);
        slmNumberOfExcercises.setPreferredSize(new Dimension(350, 75));
        slmNumberOfExcercises.addChangeListener(new ChangeListenerSettingsSlider("count", settingsMultiplication));
        pMultiplication.add(slmNumberOfExcercises, Helper.gbc(0, 1, 2));

        ButtonGroup bgmSize = new ButtonGroup();
        lmFactor.setText("Činitelé");
        lmFactor.setFont(fLabel);
        pMultiplication.add(lmFactor, Helper.gbc(0, 2));
        chmTens.setText("Desítky");
        chmTens.setSelected(true);
        chmTens.addActionListener(new ActionListenerSettings(99, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmTens, Helper.gbc(0, 3));
        chmHundreds.setText("Stovky");
        chmHundreds.addActionListener(new ActionListenerSettings(999, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmHundreds, Helper.gbc(0, 4));
        chmThousands.setText("Tisíce");
        chmThousands.addActionListener(new ActionListenerSettings(9999, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmThousands, Helper.gbc(0, 5));
        chmMilions.setText("Miliony");
        chmMilions.addActionListener(new ActionListenerSettings(99999999, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmMilions, Helper.gbc(0, 6));
        chmCustom.setText("Vlastní");
        chmCustom.addActionListener(new ActionListenerSettings(-1, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmCustom, Helper.gbc(0, 7));
        tmCustom.setText("9");
        tmCustom.setEnabled(false);
        tmCustom.setPreferredSize(new Dimension(100, 25));
        tmCustom.getDocument().addDocumentListener(new DocumentListenerSettingsText("multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(tmCustom, Helper.gbc(0, 8, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, new Insets(0, 0, 0, 75)));
        bgmSize.add(chmMilions);
        bgmSize.add(chmTens);
        bgmSize.add(chmHundreds);
        bgmSize.add(chmThousands);
        bgmSize.add(chmCustom);

        ButtonGroup bgmmSize = new ButtonGroup();
        chmmUnits.setText("Jednotky");
        chmmUnits.setSelected(true);
        chmmUnits.addActionListener(new ActionListenerSettings(9, "multiplebottom", settingsMultiplication, tmmCustom));
        pMultiplication.add(chmmUnits, Helper.gbc(1, 3));
        chmmTens.setText("Desítky");
        chmmTens.addActionListener(new ActionListenerSettings(99, "multiplebottom", settingsMultiplication, tmmCustom));
        pMultiplication.add(chmmTens, Helper.gbc(1, 4));
        chmmHundreds.setText("Stovky");
        chmmHundreds.addActionListener(new ActionListenerSettings(999, "multiplebottom", settingsMultiplication, tmmCustom));
        pMultiplication.add(chmmHundreds, Helper.gbc(1, 5));
        chmmCustom.setText("Vlastní");
        chmmCustom.addActionListener(new ActionListenerSettings(-1, "multiplebottom", settingsMultiplication, tmmCustom));
        pMultiplication.add(chmmCustom, Helper.gbc(1, 7));
        tmmCustom.setText("9");
        tmmCustom.setEnabled(false);
        tmmCustom.setPreferredSize(new Dimension(100, 25));
        tmmCustom.getDocument().addDocumentListener(new DocumentListenerSettingsText("multiplebottom", settingsMultiplication, tmmCustom));
        pMultiplication.add(tmmCustom, Helper.gbc(1, 8, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, new Insets(0, 0, 0, 75)));
        bgmmSize.add(chmmUnits);
        bgmmSize.add(chmmTens);
        bgmmSize.add(chmmHundreds);
        bgmmSize.add(chmmCustom);

        JButton bmStart = new JButton("Spustit násobení");
        bmStart.setFont(fButton);
        bmStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) container.getLayout()).show(container, CardMultiplication);
                mpanel.reset();
            }
        });
        pMultiplication.add(bmStart, Helper.gbc(0, 9, 2, GridBagConstraints.BOTH, GridBagConstraints.LINE_START, new Insets(25, 0, 0, 0)));

        chmMilions.setFont(fRadio);
        chmTens.setFont(fRadio);
        chmHundreds.setFont(fRadio);
        chmThousands.setFont(fRadio);
        chmCustom.setFont(fRadio);

        chmmUnits.setFont(fRadio);
        chmmTens.setFont(fRadio);
        chmmHundreds.setFont(fRadio);
        chmmCustom.setFont(fRadio);

        container.add(pMultiplication, CardSettingsMultiplication);

        /**
         * ABOUT
         */
        JPanel pAbout = new JPanel(new GridBagLayout());
        JLabel laVersion = new JLabel("Verze: 1.0.0");
        pAbout.add(laVersion, Helper.gbc(0, 0));
        JLabel laAuthor = new JLabel("Copyright (C) 2014 Ondřej Švec");
        pAbout.add(laAuthor, Helper.gbc(0, 1));
        JButton baHomepage = new JButton("<HTML>Homepage: <FONT color=\"#000099\"><U>http://svecon.cz/</U></FONT></HTML>");
        baHomepage.setBorderPainted(false);
        baHomepage.setBackground(new Color(0xEEEEEE));
        baHomepage.setToolTipText("http://svecon.cz/");
        baHomepage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://svecon.cz/"));
                    } catch (IOException | URISyntaxException e) {
                    }
                } else {
                }
            }
        });
        pAbout.add(baHomepage, Helper.gbc(0, 2));

        container.add(pAbout, CardAbout);
    }

    /**
     * Main method
     *
     * @param args Arguments from command line
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculus();
            }
        });
    }

}
