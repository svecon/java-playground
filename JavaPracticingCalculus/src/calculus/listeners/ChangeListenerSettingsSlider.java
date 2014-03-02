package calculus.listeners;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import calculus.Settings;

/**
 * Listener for Slider. Updates Settings object.
 *
 * @author svecon
 */
public class ChangeListenerSettingsSlider implements ChangeListener {

    /**
     * Key-value pair
     */
    String key;
    /**
     * Setting wrapper object for storing key-value pairs
     */
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

        settings.set(key, (int) source.getValue());
    }
}
