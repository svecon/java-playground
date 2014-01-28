/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vyukanasobilky;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author svecon
 */
public class ChangeListenerSettingsSlider implements ChangeListener {

    String key;
    Settings settings;

    public ChangeListenerSettingsSlider(String key, Settings settings) {
        this.key = key;
        this.settings = settings;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (source.getValueIsAdjusting()) {
            return;
        }
        
        Debugger.log(key + ": " + (int) source.getValue());

        settings.set(key, (int) source.getValue());
    }
}
