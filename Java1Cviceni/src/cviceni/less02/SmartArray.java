/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cviceni.less02;

import java.util.ArrayList;

/**
 *
 * @author sveco
 */
public class SmartArray implements IMyCollection {
    
    ArrayList<Object> sa;

    public SmartArray() {
        sa = new ArrayList<Object>();
    }
    
    @Override
    public void add(Object o) {
        sa.add(o);
    }
    
    @Override
    public Object get(int i) {
        return sa.get(i);
    }
    
    @Override
    public void remove(Object o) {
        sa.remove(o);
    }
    
    @Override
    public void remove(int i) {
        sa.remove(i);
    }
    
    public int size(){
        return sa.size();
    }
    
}
