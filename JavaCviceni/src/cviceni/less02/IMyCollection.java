/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cviceni.less02;

/**
 *
 * @author sveco
 */
public interface IMyCollection {
    void add(Object o);
    Object get(int i);
    void remove(Object o);
    void remove(int i);
    int size();
}
