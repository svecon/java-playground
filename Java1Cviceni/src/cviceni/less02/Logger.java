/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cviceni.less02;

import cviceni.less03.IterableArray;

/**
 *
 * @author sveco
 */
public class Logger {
    
    protected IterableArray printers;
    protected int level;

    public Logger() {
        init();
    }

    void init(){
        printers = new IterableArray();
    }
    
    public void addPrinter(Printer printer){
        printers.add(printer);
    }
    
    public void removePrinter(Printer printer){
        printers.remove(printer);
    }
    
    public void log(String msg){
        this.log(msg, this.level);
    }
    
    public void log(String msg, int level){
        if (this.level <= level) {
            for (Object p : printers) {
                ((Printer)p).print(msg);
            }
        }
    }
    
    public void setLevel(int level){
        this.level = level;
    }
}
