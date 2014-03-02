/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cuni.mff.java.zapocetxml.impl;

import cz.cuni.mff.java.zapocetxml.api.INode;
import cz.cuni.mff.java.zapocetxml.api.IXmlProcess;
import cz.cuni.mff.java.zapocetxml.api.XmlException;

/**
 * Pomocna trida, nemusite (ale muzete) menit.
 * Umi vypsat DOM strom jako XML. Muzete tak porovnat, jestli je vas DOM strom skutecne
 * reprezentaci vstupu.
 * @author Martin Cerny
 */
public class XmlDebugWriter implements IXmlProcess{
    
    @Override
    public void processXml(INode root) throws XmlException {
        processXmlInternal(root, 0);
    }
    
    protected void writeIndent(int indent)
    {
        for(int i = 0; i < indent; i++)
            System.out.print("\t");
    }
    
    protected void processXmlInternal(INode root, int indent)
    {
        if(root.getType() == INode.NodeType.ELEMENT) {
            writeIndent(indent);
            System.out.println("<" + root.getValue() + ">");
        }
        
        if(root.getType() == INode.NodeType.TEXT)
        {
            writeIndent(indent);
            System.out.println(root.getValue());
        }
        
        for(INode child : root.getChildren())
        {
            processXmlInternal(child, indent + 1);
        }

        if(root.getType() == INode.NodeType.ELEMENT) {
            writeIndent(indent);
            System.out.println("</" + root.getValue() + ">");
        }
        
    }
    
}
