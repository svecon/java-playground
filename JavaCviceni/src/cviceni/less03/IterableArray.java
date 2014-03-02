/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cviceni.less03;

import java.util.Iterator;
import java.util.NoSuchElementException;
import cviceni.less02.DumbArray;

/**
 *
 * @author sveco
 */
public class IterableArray extends DumbArray implements Iterable {

    @Override
    public Iterator iterator() {

        return new Iterator() {

            private int index = 0;

            @Override
            public boolean hasNext(){
                return index < size();
            }

            @Override
            public Object next(){
                if (index < count)
                    return arr[index++];

                throw new NoSuchElementException();
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException("Not supported yet.");
            }

        };
    }

}
