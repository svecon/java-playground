/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vyukanasobilky;

import java.util.Random;

/**
 *
 * @author svecon
 */
public class NumberGenerator {
    
    static Random r = new Random();
    
    static int getLargeNumber(int max){
        int min = max / 10 + 1;
        
        int num;
        while((num = r.nextInt(max)) < min){}
        
        return num;
    
    }
    
}
