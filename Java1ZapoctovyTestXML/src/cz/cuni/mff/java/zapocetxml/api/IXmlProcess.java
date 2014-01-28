/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cuni.mff.java.zapocetxml.api;

/**
 *
 * @author Martin Cerny
 */
public interface IXmlProcess {
    public void processXml(INode root) throws XmlException;
}
