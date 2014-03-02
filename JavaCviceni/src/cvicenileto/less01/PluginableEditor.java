package cvicenileto.less01;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.lang.reflect.*;

/**
 *
 * @author svecon
 */
public class PluginableEditor extends JFrame {

    final Container container;
    final JTextArea textArea;
    JMenu menuSaveAs;
    JMenu menuFilters;

    final String fileName = "PluginableEditor.txt";

    public PluginableEditor(String... args) {

        super("Plugginable Text Editor");

        container = this.getContentPane();

        this.setSize(600, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new BorderLayout());

        textArea = new JTextArea();
        container.add(textArea, BorderLayout.CENTER);

        createAndShowGUI();

        loadPluginDefinitions(args);
    }

    private void createAndShowGUI() {

        JMenuBar menuBar = new JMenuBar();

        JMenu menuStart = new JMenu("Program");
        menuBar.add(menuStart);

        JMenuItem menuSave = new JMenuItem("Save");
        menuStart.add(menuSave);
        menuSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)))) {

                    bw.write(textArea.getText());
                    bw.close();
                    System.out.println("File saved: " + fileName);

                } catch (IOException ex) {
                    System.out.println("There was an error while saving the file.");
                    System.out.println(ex);
                }
            }
        });

        JMenuItem menuLoad = new JMenuItem("Load");
        menuStart.add(menuLoad);
        menuLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                textArea.setText("");

                try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {

                    String ln;
                    while ((ln = br.readLine()) != null) {
                        textArea.append(ln);
                        textArea.append("\n");
                    }

                } catch (IOException ex) {
                    System.out.println("There was an error while loading the file.");
                    System.out.println(ex);
                }
            }
        });

        menuSaveAs = new JMenu("Uložit jako");
        menuBar.add(menuSaveAs);

        menuFilters = new JMenu("Filtry");
        menuBar.add(menuFilters);

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

    }

    private void loadPluginDefinitions(String... args) {
        for (String interfaceName : args) {

            try {
                final Class<?> iface = Class.forName(interfaceName);

                ArrayList<?> filtes = (ArrayList<?>) loadPlugins(iface, loadPluginDefinitions(interfaceName + ".txt"));

                for (final Object obj : filtes) {
                    JMenuItem menuItem = new JMenuItem((String) iface.getMethod("menuText").invoke(obj));

                    menuItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                textArea.setText((String) iface.getMethod("process").invoke(obj, textArea.getText()));

                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
                                System.out.println(ex);
                            }
                        }
                    });

                    menuFilters.add(menuItem);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                System.out.println(e);
            }
        }
    }

    private String[] loadPluginDefinitions(String file) {
        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {

            String ln;
            while ((ln = br.readLine()) != null) {
                if (ln.trim().length() > 0) {
                    list.add(ln);
                }
            }

        } catch (IOException ex) {
            System.out.println("There was an error while loading the file.");
            System.out.println(ex);
        }

        String[] plugins = new String[list.size()];
        list.toArray(plugins);

        return plugins;
    }

    static <T> List<T> loadPlugins(Class<T> pluginInterface, String... pluginNames) {
        java.util.ArrayList<T> plugins = new java.util.ArrayList<>();

        for (String pluginName : pluginNames) {
            try {
                Class<?> cls = Class.forName(pluginName);
                if (cls.isArray() || cls.isInterface() || cls.isPrimitive() || cls.isEnum() || cls.isAnnotation()) {
                    System.out.println(cls.getName() + " neni trida.");
                    continue;
                }
                if (!pluginInterface.isAssignableFrom(cls)) {
                    System.out.println("Trida " + cls.getName() + " neimplementuje interface Plugin.");
                    continue;
                }
                plugins.add((T) cls.newInstance());
            } catch (ClassNotFoundException e) {
                System.out.println("Trida " + pluginName + " neexistuje.");
            } catch (InstantiationException | IllegalAccessException e) {
                System.out.println("Nelze vytvorit instanci od " + pluginName);
            }
        }
        return plugins;
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PluginableEditor(args);
            }
        });
    }

}
