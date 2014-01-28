/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vyukanasobilky;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;

/**
 *
 * @author svecon
 */
public class VyukaNasobilky extends JFrame {

    final private Container container;

    private final String CardAbout = "cardabout";
    private final String CardStartScreen = "cardstartscreen";
    private final String CardSettingsDivision = "cardsettingsdivision";
    private final String CardSettingsMultiplication = "cardsettingsmultiplication";
    private final String CardDivision = "carddivision";
    private final String CardMultiplication = "cardmultiplication";

    public VyukaNasobilky() {
        super("Výuka násobení a dělení");

        container = this.getContentPane();

        this.setSize(600, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new CardLayout());

        createAndShowGUI();
    }

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
        JRadioButton chmUnits = new JRadioButton();
        JRadioButton chmTens = new JRadioButton();
        JRadioButton chmThousands = new JRadioButton();
        JRadioButton chmHundreds = new JRadioButton();
        JRadioButton chmCustom = new JRadioButton();
        JTextField tmCustom = new JTextField();

        Settings settingsDivision = new Settings(Settings.DIVISION);
        Settings settingsMultiplication = new Settings(Settings.MULTIPLICATION);

        /**
         * DIVISION SETTINGS
         */
        pDivision.setLayout(new GridBagLayout());
        
        final DivisionPanel dpanel = new DivisionPanel(settingsDivision);
        container.add(dpanel, CardDivision);

        JLabel ldNumberOfExcercises = new JLabel("Počet příkladů");
        pDivision.add(ldNumberOfExcercises, Helper.gbc(0, 0, 1));

        JSlider sldNumberOfExcercises = new JSlider(JSlider.HORIZONTAL, 5, 30, 15);
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

        JButton bdStart = new JButton("Spustit");
        bdStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) container.getLayout()).show(container, CardDivision);
                dpanel.initialize();
            }
        });
        pDivision.add(bdStart, Helper.gbc(0, 9, 2, GridBagConstraints.BOTH, GridBagConstraints.LINE_START, new Insets(25, 0, 0, 0)));

        container.add(pDivision, CardSettingsDivision);

        /**
         * MULTIPLICATION SETTINGS
         */
        pMultiplication.setLayout(new java.awt.GridBagLayout());

        JLabel lmNumberOfExcercises = new JLabel("Počet příkladů");
        pMultiplication.add(lmNumberOfExcercises, Helper.gbc(0, 0, 1));

        JSlider slmNumberOfExcercises = new JSlider(JSlider.HORIZONTAL, 5, 30, 15);
        slmNumberOfExcercises.setMajorTickSpacing(5);
        slmNumberOfExcercises.setMinorTickSpacing(1);
        slmNumberOfExcercises.setPaintTicks(true);
        slmNumberOfExcercises.setPaintLabels(true);
        slmNumberOfExcercises.setPreferredSize(new Dimension(350, 75));
        slmNumberOfExcercises.addChangeListener(new ChangeListenerSettingsSlider("count", settingsMultiplication));
        pMultiplication.add(slmNumberOfExcercises, Helper.gbc(0, 1, 2));

        ButtonGroup bgmSize = new ButtonGroup();
        chmUnits.setText("Jednotky");
        chmUnits.setSelected(true);
        chmUnits.addActionListener(new ActionListenerSettings(9, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmUnits, Helper.gbc(0, 3, 2));
        chmTens.setText("Desítky");
        chmTens.addActionListener(new ActionListenerSettings(99, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmTens, Helper.gbc(0, 4, 2));
        chmHundreds.setText("Stovky");
        chmHundreds.addActionListener(new ActionListenerSettings(999, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmHundreds, Helper.gbc(0, 5, 2));
        chmThousands.setText("Tisíce");
        chmThousands.addActionListener(new ActionListenerSettings(9999, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmThousands, Helper.gbc(0, 6, 2));
        chmCustom.setText("Vlastní");
        chmCustom.addActionListener(new ActionListenerSettings(-1, "multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(chmCustom, Helper.gbc(0, 7, 2));
        tmCustom.setText("9");
        tmCustom.setEnabled(false);
        tmCustom.setPreferredSize(new Dimension(100, 25));
        tmCustom.getDocument().addDocumentListener(new DocumentListenerSettingsText("multiple", settingsMultiplication, tmCustom));
        pMultiplication.add(tmCustom, Helper.gbc(0, 8, 2, GridBagConstraints.NONE, GridBagConstraints.LINE_START, new Insets(0, 0, 0, 75)));
        bgmSize.add(chmUnits);
        bgmSize.add(chmTens);
        bgmSize.add(chmHundreds);
        bgmSize.add(chmThousands);
        bgmSize.add(chmCustom);

        JButton bmStart = new JButton("Spustit");
        pMultiplication.add(bmStart, Helper.gbc(0, 9, 2, GridBagConstraints.BOTH, GridBagConstraints.LINE_START, new Insets(25, 0, 0, 0)));

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VyukaNasobilky();
            }
        });
    }

}
