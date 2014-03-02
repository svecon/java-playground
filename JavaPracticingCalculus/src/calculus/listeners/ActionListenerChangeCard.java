package calculus.listeners;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener that can switch cards on a CardLayout container
 * @author svecon
 */
public class ActionListenerChangeCard implements ActionListener {

    /**
     * Name of card to switch to
     */
    String switchTo;
    /**
     * Container that has CardLayout
     */
    Container pane;

    public ActionListenerChangeCard(Container pane, String card) {
        this.pane = pane;
        this.switchTo = card;
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        ((CardLayout) pane.getLayout()).show(pane, switchTo);
    }
    
}
