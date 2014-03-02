/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cviceni.less02;

/**
 *
 * @author sveco
 */
public class DumbArray implements IMyCollection {

    private static final int SIZE = 16;

    protected Object[] arr;
    protected int count;

    public DumbArray() {
        arr = new Object[SIZE];
        count = 0;
    }
    
    @Override
    public void add(Object o) {
        if(arr.length < count){
            Object[] narr = new Object[arr.length * 2];

            for(int i = 0; i< arr.length; i++){
                narr[i] = arr[i];
            }

            arr = narr;
        }

        arr[count] = o;
        count++;
    }
    
    @Override
    public Object get(int i) {
        return arr[i];
    }
    
    @Override
    public void remove(Object o) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == o) {
                remove(i);
            }
        }
    }
    
    @Override
    public void remove(int i) {
        for (int j = i; j < arr.length - 1; j++) {
            arr[j] = arr[j + 1];
        }
        count--;
    }
    
    public int size(){
        return count;
    }
    
}
