/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vyukanasobilky;

import java.util.HashMap;

/**
 *
 * @author svecon
 */
public class Settings {

    HashMap<String, Integer> vals = new HashMap<>();
    
    public static final int DIVISION = 1;
    public static final int MULTIPLICATION = 2;
    
    public Settings(int mode) {
        if (mode == 1) {
            setDefaultsToDivision();
        } else {
            setDefaultsToMultiplication();
        }
    }
    
    private void setDefaultsToDivision(){
        set("count", 15);
        set("divident", 99);
        set("factor", 9);
    }
    
    private void setDefaultsToMultiplication(){
        set("count", 15);
        set("multiple", 9);
    }

    public int get(String key){
        return vals.get(key);
    }
    
    public Settings set(String key, int value){
        vals.put(key, value);
        return this;
    }
    
    
}
