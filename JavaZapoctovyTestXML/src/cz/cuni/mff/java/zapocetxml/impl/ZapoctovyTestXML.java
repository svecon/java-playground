/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cuni.mff.java.zapocetxml.impl;

import cz.cuni.mff.java.zapocetxml.api.INode;
import cz.cuni.mff.java.zapocetxml.api.IXmlParser;
import cz.cuni.mff.java.zapocetxml.api.IXmlProcess;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Martin Cerny
 */
public class ZapoctovyTestXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //testovaci kod
        
        //vytvorim parser
        IXmlParser parser = new XmlParser();
        if(args.length != 1)
        {
            System.out.println("Nebyl zadan soubor");
            return;
        }
        
        //nactu dokument dle command line
        INode rootNode = parser.parseDocument(new FileReader(args[0]));
    
        
        //vypisu vytvoreny DOM pomoci debug writeru
        System.out.println("Vypis vytvoreneho XML");
        IXmlProcess debugWriter = new XmlDebugWriter();
        debugWriter.processXml(rootNode);
    }
}
