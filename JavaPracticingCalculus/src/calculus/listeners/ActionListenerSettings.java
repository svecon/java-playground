package calculus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import calculus.Settings;

/**
 * Listener for radio buttons and text boxes. Auto updates values to Settings
 * variable.
 *
 * @author svecon
 */
public class ActionListenerSettings implements ActionListener {

    /**
     * Value used when radio button is selected
     */
    int value;
    /**
     * Key-value pair
     */
    String key;
    /**
     * Setting wrapper object for storing key-value pairs
     */
    Settings settings;
    /**
     * Auto-update text field when radio is selected
     */
    JTextField textField;

    public ActionListenerSettings(int value, String key, Settings settings, JTextField textField) {
        this.value = value;
        this.key = key;
        this.settings = settings;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (value == -1) {
            textField.setEnabled(true);
            return;
        }

        settings.set(key, value);
        textField.setText(value + "");
        textField.setEnabled(false);
    }

}
