/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vyukanasobilky;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author svecon
 */
public class ActionListenerSettings implements ActionListener {

    int value;
    String key;
    Settings settings;
    JTextField textField;

    public ActionListenerSettings(int value, String key, Settings settings, JTextField textField) {
        this.value = value;
        this.key = key;
        this.settings = settings;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update(value);
    }

    protected void update(int value) {
        if (value == -1) {
            textField.setEnabled(true);
            return;
        }
        
        Debugger.log(key + ":: " + value);

        settings.set(key, value);
        textField.setText(value + "");
        textField.setEnabled(false);
    }

}
