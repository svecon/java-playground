/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cviceni.less02;

/**
 *
 * @author sveco
 */
public class GenericPrinter implements Printer {

    public GenericPrinter() {
        System.out.println("GenericPrinter constructor");
    }
    
    @Override
    public void print(String msg){
        System.out.println(msg);
    }
}
