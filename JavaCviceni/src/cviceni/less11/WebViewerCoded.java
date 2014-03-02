/* $Id$ */
package cviceni.less11;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class WebViewerCoded {

    static class URLListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("comboBoxChanged")) {
                return;
            }

            urlBar.setEnabled(false);
            ((CardLayout) cards.getLayout()).show(cards, LOADING);

            final String text = (String) urlBar.getSelectedItem();
            urlBar.addItem(text);

            SwingWorker<String, Object> worker = new SwingWorker<String, Object>() {

                @Override
                protected String doInBackground() throws Exception {

                    StringBuilder builder = new StringBuilder();

                    URL url = new URL(text);
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                        String ln;
                        while ((ln = reader.readLine()) != null) {
                            builder.append(ln).append(System.getProperty("line.separator"));
                        }
                    } catch (IOException exception) {
                        throw exception;
                    }
                    return builder.toString();
                }

                @Override
                public void done() {
                    urlBar.setEnabled(true);
                    ((CardLayout) cards.getLayout()).show(cards, BROWSER);

                    try {
                        textBrower.setText(get());
                    } catch (InterruptedException | ExecutionException exception) {
                        textBrower.setText(exception.getCause().getMessage());
                    }
                }
            };

            worker.execute();

            System.out.println(text);
        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    static JPanel cards, cardBrowser, cardLoading;
    static JTextArea textBrower;
    static JComboBox urlBar;
    static Container pane;
    static BorderLayout layout;

    final static String BROWSER = "browser";
    final static String LOADING = "loading";

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Web viewer");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setVisible(true);

        urlBar = new JComboBox();
        urlBar.setEditable(true);
        urlBar.addActionListener(new URLListener());

        layout = new BorderLayout();
        pane = f.getContentPane();
        pane.setLayout(layout);

        textBrower = new JTextArea(10, 20);
        textBrower.setEditable(false);
        textBrower.setLineWrap(true);
        
        JScrollPane scroll = new JScrollPane(textBrower);

        cardBrowser = new JPanel();
        cardBrowser.setLayout(new BorderLayout());
        cardBrowser.add(scroll, BorderLayout.CENTER);

        cardLoading = new JPanel();
        JLabel loadingText = new JLabel("Loading...");
        cardLoading.add(loadingText);

        cards = new JPanel(new CardLayout());

        cards.add(cardBrowser, BROWSER);
        cards.add(cardLoading, LOADING);

        f.setVisible(true);
        pane.add(urlBar, BorderLayout.NORTH);
        pane.add(cards, BorderLayout.CENTER);
    }

}
