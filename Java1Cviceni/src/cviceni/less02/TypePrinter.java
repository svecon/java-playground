/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less02;

/**
 *
 * @author sveco
 */
public class TypePrinter extends GenericPrinter {

    String type;
    
    public TypePrinter(String type) {
        this.type = type;
        
        System.out.println("TypePrinter constructor");
    }
    
    @Override
    public void print(String msg){
        super.print(type + ": " + msg);
    }
    
    
    
}
