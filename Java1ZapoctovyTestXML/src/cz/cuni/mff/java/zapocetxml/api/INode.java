/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cuni.mff.java.zapocetxml.api;

import java.util.List;

/**
 *
 * @author Martin Cerny
 */
public interface INode {
    enum NodeType {TEXT, ELEMENT};
    
    public NodeType getType(); 
    
    /**
     * Vraci hodnotu tohoto elementu, tj. 
     * @return 
     */
    public String getValue();
    
    public List<INode> getChildren();
}
