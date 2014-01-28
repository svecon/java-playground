/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vyukanasobilky;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author svecon
 */
public class DocumentListenerSettingsText implements DocumentListener {

    String key;
    Settings settings;
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

        Debugger.log(key + ": " + value);
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
