/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cviceni.less03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author sveco
 */
public class SearchTree {

    private Node root;

    private class Node {
        int value;
        Node left;
        Node right;

        public Node(int v){
            value = v;
        }

    }

    public SearchTree(int v){
        this.root = new Node(v);
    }

    

}
