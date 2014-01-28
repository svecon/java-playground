/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vyukanasobilky;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author svecon
 */
public class ActionListenerChangeCard implements ActionListener {

    String switchTo;
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
