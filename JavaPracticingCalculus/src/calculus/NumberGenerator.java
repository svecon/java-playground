package calculus;

import java.util.Random;

/**
 * Wrapper class for generating random numbers with specific needs (in given interval).
 * @author svecon
 */
public class NumberGenerator {
    
    /**
     * Prepare standard generator
     */
    static Random r = new Random();
    
    /**
     * Returns random number between given bounds
     * @param max higher bound
     * @return Number between max and its tenth (= lower bound)
     */
    static int get(int max){
        int maxLength = String.valueOf(max).length();
        
        int min = 1;
        for (int i = 0; i < maxLength - 1; i++) {
            min *= 10;
        }
        
        int num;
        while((num = r.nextInt(max)) < min){}
        
        return num;
    
    }
    
}
