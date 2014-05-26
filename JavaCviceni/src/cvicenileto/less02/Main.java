package cvicenileto.less02;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author svecon
 */
public class Main extends JFrame {

    final Container container;

    public Main(String... args) {

        super("Form Framework");

        container = this.getContentPane();

        this.setSize(600, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new BorderLayout());

        createAndShowGUI();
    }

    private void createAndShowGUI() {
        
        FormPanel<TestData> fp = new FormPanel<>(TestData.class);
        container.add(fp);
        
        fp.setObjectValues(new TestData("Petr", "heslo", true));
        
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main(args);
            }
        });
    }
}
