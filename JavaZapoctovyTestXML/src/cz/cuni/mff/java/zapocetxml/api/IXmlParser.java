/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cuni.mff.java.zapocetxml.api;

import java.io.IOException;
import java.io.Reader;

/**
 * 
 * @author Martin Cerny
 */
public interface IXmlParser {
    INode parseDocument(Reader reader) throws IOException, XmlException;
}
