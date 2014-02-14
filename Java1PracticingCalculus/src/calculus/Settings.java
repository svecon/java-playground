package calculus;

import java.util.HashMap;

/**
 * Wrapper class for key-value pairs. Works over a dictionary, but aso
 *
 * @author svecon
 */
public class Settings {

    /**
     * Hashmap where all key-value pair settings are stored
     */
    HashMap<String, Integer> vals = new HashMap<>();

    /**
     * Default mode for Division
     */
    public static final int DIVISION = 1;
    /**
     * Default mode for Multiplication
     */
    public static final int MULTIPLICATION = 2;

    /**
     * Constructor
     * @param mode Sets default values for given mode
     */
    public Settings(int mode) {
        if (mode == 1) {
            setDefaultsToDivision();
        } else {
            setDefaultsToMultiplication();
        }
    }

    /**
     * Default values for Division
     */
    private void setDefaultsToDivision() {
        set("count", 15);
        set("divident", 99);
        set("factor", 9);
    }

    /**
     * Default values for Multiplication
     */
    private void setDefaultsToMultiplication() {
        set("count", 15);
        set("multiple", 99);
        set("multiplebottom", 9);
    }

    /**
     * Getter
     *
     * @param key String
     * @return int Value
     */
    public int get(String key) {
        return vals.get(key);
    }

    /**
     * Setter with Fluent Interface pattern
     *
     * @param key String
     * @param value int
     * @return Settings
     */
    public Settings set(String key, int value) {
        vals.put(key, value);
        return this;
    }
}
