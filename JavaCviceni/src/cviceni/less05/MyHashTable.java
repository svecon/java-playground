/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less05;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author sveco
 */
public class MyHashTable implements Iterable<Map.Entry> {

    Map.Entry[] values;
    private int tableSize;
    int count;
    
    class HashEntry implements Map.Entry<String, Object> {

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return val;
        }

        @Override
        public Object setValue(Object v) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        String key;
        Object val;
        
        public HashEntry(String key, Object val) {
            this.key = key;
            this.val = val;
        }
            
    }
    
    private static int smallPrime = 31;
    
    public MyHashTable(int size){
        values = new Map.Entry[size];
        this.tableSize = size;
    }
    
    public Map.Entry get(String key){
        int index = calcHash(key);
        while(values[index].getKey() != key){ index++; index %= tableSize; }
        
        return values[index];
    }
    
    public void set(String key, Object value){
        if (2*count >= tableSize) { resizeTable(); }
        
        int index = calcHash(key);
        while (values[index] != null) { index++; index %= tableSize; }
        
        count++;
        values[index] = new HashEntry(key, value);
    }
    
    private void resizeTable(){
        Map.Entry[] narr = new Map.Entry[tableSize * 2 + 2];
        for (int i = 0; i < tableSize; i++) {
            narr[i] = values[i];
        }
        values = narr;
    }
    
    private int calcHash(String s){
        int hash = 0;
        for (int i = 0; i < s.length(); i++){
            hash = (smallPrime * hash + s.charAt(i)) % tableSize;
        }
        return hash;
    }
    
    private int iteratorIndex = 0;
    
    @Override
    public Iterator<Map.Entry> iterator() {
        return new Iterator<Map.Entry>() {

            public boolean hasNext() {
                while(iteratorIndex < tableSize 
                   && values[iteratorIndex] == null) { iteratorIndex++; }
                
                return iteratorIndex < tableSize;
            }

            public Map.Entry next() {
                while(values[iteratorIndex] == null){ iteratorIndex++; }
                
                return values[iteratorIndex++];
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }
    
    public static void main(String[] args){
        MyHashTable ht = new MyHashTable(87);
        ht.set("ahoj", 10);
        ht.set("bebe", 20);
        ht.set("xxx", 30);
        ht.set("cece", 40);
        
        for (Map.Entry e : ht) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
    
    
}
