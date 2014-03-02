/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less04;

import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author sveco
 */
public class Aligner {

    FileReader reader;
    
    public Aligner(FileReader r) {
        reader = r;
    }
    
    public void process(int charsPerLine) throws IOException
    {
        MyString s = new MyString();
        int curLine = 0;
        
        int c;
        while((c = reader.read()) != -1){
            if ((c == ' ') || (c == '\n') || (c == '\r') || (c == '\t')) {
                
                if (s.length > 0) {
                    if (curLine != 0) {
                            System.out.print(" ");
                        }
                    
                    if (curLine + s.length > charsPerLine) {
                        System.out.println(s.toString());
                        curLine = 0;
                    } else {
                        System.out.print(s.toString());
                        curLine += s.length;
                    }
                    
                    s = new MyString();
                    
                }
                
            } else {
                
                s.append((char)c);
            
            }
        }
    }
    
    
    
}
