/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less04;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author sveco
 */
public class Main {
    public static void main(String[] args){
        MyString s = new MyString("Hello World");
        
        s.append(", and Andrew!");
        s.append(", and Andrew!");
        s.append(", and Andrew!");
        s.append(", and Andrew!");
        s.append(", and Andrew!");
        
        s.delete(0, 6);
        s.insert(0, "123456789 ");
        
        s.delete(21, 6);
        s.insert(21, "Pepa");        
        
        System.out.println(s.toString());
        
        /////////////////
        
        try {
            System.out.println();
            Aligner a = new Aligner(new FileReader(args[0]));
            a.process(10);
            System.out.println();
            
        }
        catch (FileNotFoundException e){
            System.out.print("File not found");
        }
        catch (IOException e){
            System.out.print("IO exception");
        }
    }
}
