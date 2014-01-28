/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less02;

/**
 *
 * @author sveco
 */
public class TimestampPrinter extends GenericPrinter {

    public TimestampPrinter() {
        System.out.println("TimestampPrinter constructor");
    }
    
    @Override
    public void print(String msg){
        super.print(new java.util.Date().toString() + ": " + msg);
    }
    
    
    
}
