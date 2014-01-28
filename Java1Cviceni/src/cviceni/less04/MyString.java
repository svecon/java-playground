/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less04;

import java.util.Iterator;

/**
 *
 * @author sveco
 */
public class MyString implements Iterable<Character> {

    static final int SIZE = 16;
    
    char[] arr;
    int length;
    
    MyString(){
        initialize();
    }
    
    MyString(String str){
        initialize();
        append(str);
    }
    
    private void initialize(){
        arr = new char[SIZE];
        length = 0;
    }
    
    boolean checkAndIncreaseSize(int appending){
        if(length + appending <= arr.length)
            return false;
        
        char[] arr2 = new char[arr.length * 2 + 2];
        for (int i = 0; i < arr.length; i++)
            arr2[i] = arr[i];
        
        arr = arr2;
        
        return true;
    }
    
    void append(String str){
        checkAndIncreaseSize(str.length());
        
        for (int i = 0; i < str.length(); i++)
            arr[i + length] = str.charAt(i);
        this.length += str.length();
    }
    
    void append(char c){
        checkAndIncreaseSize(1);
        
        arr[length] = c;
        this.length += 1;
    }
    
    void moveRight(int pos, int length){
        for (int i = this.length; i >= 0; i--) {
            arr[i+length] = arr[i];
        }
    }
    
    void moveLeft(int pos, int length){
        for (int i = 0; i < this.length; i++) {
            arr[pos + i] = pos + length + i > arr.length ? null : arr[pos + length + i];
        }
    }
    
    void insert(int pos, String str){
        checkAndIncreaseSize(str.length());
        
        moveRight(pos, str.length());
        
        for (int i = 0; i < str.length(); i++)
            arr[pos + i] = str.charAt(i);
        length += str.length();
    }
    
    void insert(int pos, char c){
        checkAndIncreaseSize(1);
        
        moveRight(pos, 1);

        arr[pos] = c;
        length += 1;
    }
    
    void delete(int pos, int length) {
        
        moveLeft(pos, length);
        
        this.length -= length;
    }
    
    @Override
    public String toString() {
        return length > 0 ? new String(arr, 0, length) : new String();
    }
    
    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {

            private int i = 0;
            
            public boolean hasNext() {
                return i < length;
            }

            public Character next() {
                if (hasNext()) {
                    return arr[i++];
                }
                return null;
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    
    }
            
            
}
