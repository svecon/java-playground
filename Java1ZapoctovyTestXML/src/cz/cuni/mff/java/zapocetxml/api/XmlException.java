/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cuni.mff.java.zapocetxml.api;

/**
 * Vyjimka pri cteni XML.
 * @author Martin Cerny
 */
public class XmlException extends RuntimeException {

    public XmlException(String message) {
        super(message);
    }

    public XmlException(String message, Throwable cause) {
        super(message, cause);
    }

}
