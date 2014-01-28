/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cuni.mff.java.zapocetxml.impl;

import cz.cuni.mff.java.zapocetxml.api.INode;
import java.util.List;

/**
 * Node reprezentujici element. Muzete si ji jakkoliv upravit, budete-li chtit.
 * @author Martin Cerny
 */
class ElementNode implements INode{

    String elementName;

    List<INode> children;

    public ElementNode(String elementName, List<INode> children) {
        this.elementName = elementName;
        this.children = children;
    }



    @Override
    public NodeType getType() {
        return NodeType.ELEMENT;
    }

    @Override
    public String getValue() {
        return elementName;
    }

    @Override
    public List<INode> getChildren() {
        return children;
    }
    
    

}
