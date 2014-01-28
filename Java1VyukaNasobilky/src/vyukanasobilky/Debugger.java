/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vyukanasobilky;

/**
 *
 * @author svecon
 */
public class Debugger {

    public static boolean isEnabled() {
        return true;
    }

    public static void log(Object o) {
        if (Debugger.isEnabled()) {
            System.out.println(o.toString());
        }
    }
}
