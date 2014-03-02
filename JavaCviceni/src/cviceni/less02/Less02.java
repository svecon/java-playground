/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cviceni.less02;

/**
 *
 * @author sveco
 */
public class Less02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger l = new Logger();
        l.addPrinter(new TimestampPrinter());
        
        l.setLevel(5);
        
        l.log("Logging first message");
        
        l.setLevel(10);
        
        l.log("This message wont be logged", 5);
        
        l.log("This message WILL be logged (level 11)", 11);
        
        l.addPrinter(new TypePrinter("LAB"));
        
        l.log("message z labu");
        
        Printer temp = new TypePrinter("TEMPORARY");
        
        l.addPrinter(temp);
        
        l.log("Printing with temporary printer");
        
        l.removePrinter(temp);
        
        l.log("Temporary printer deleted");
    }
}
