package calculus.listeners;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import calculus.Settings;

/**
 * Listener for Text Field. Updates Settings object.
 *
 * @author svecon
 */
public class DocumentListenerSettingsText implements DocumentListener {

    /**
     * Key-value pair
     */
    String key;
    /**
     * Setting wrapper object for storing key-value pairs
     */
    Settings settings;
    /**
     * Reference for given Text Field
     */
    JTextField text;

    public DocumentListenerSettingsText(String key, Settings settings, JTextField text) {
        this.key = key;
        this.settings = settings;
        this.text = text;
    }

    private void update() {
        int value;
        try {
            value = Integer.parseInt(text.getText());
        } catch (NumberFormatException ex) {
            value = 9;
        }

        settings.set(key, value);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update();
    }

}
