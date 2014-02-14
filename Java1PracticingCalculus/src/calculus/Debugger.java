package calculus;

/**
 * Simplistic debugger that was used for testing ActionListeners
 *
 * @author svecon
 */
public class Debugger {

    /**
     * Manual switch for debugger
     *
     * @return true/false
     */
    public static boolean isEnabled() {
        return true;
    }

    /**
     * Logging messages to standard output
     *
     * @param o Object to log
     */
    public static void log(Object o) {
        if (Debugger.isEnabled()) {
            System.out.println(o.toString());
        }
    }
}
