/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cuni.mff.java.zapocetxml.impl;

import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import cz.cuni.mff.java.zapocetxml.api.INode;
import cz.cuni.mff.java.zapocetxml.api.IXmlParser;
import cz.cuni.mff.java.zapocetxml.api.XmlException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

/**
 *
 * @author Martin Cerny
 */
public class XmlParser implements IXmlParser {

    INode root = null;
    ArrayList<INode> currentNode;

    private boolean isAlfaNum(char c) {
//        return ((c > 'a') && (c < 'z')) || ((c > 'A') && (c < 'Z'));
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    private boolean isWhitespace(char c) {
        return (c == ' ') || (c == '\n') || (c == '\t') || (c == '\r');
    }

    private ElementNode createNewNode(StringBuilder sb) {
        if (sb.length() == 0) {
            throw new XmlException("Syntax error: empty tag.");
        }
        
        return new ElementNode(sb.toString(), new ArrayList<INode>());
    }

    private TextNode createNewTextNode(String s) {
        return new TextNode(s);
    }

    private void addNodeToTree(INode node) {

        if (root == null) {
            
            if (node.getType() == INode.NodeType.TEXT) {
                throw new XmlException("Root tag must not be text: syntax error.");
            }
            
            root = node;
            currentNode.add(root);
            return;
        }
        
        if (currentNode.size() == 0) {
            throw new XmlException("Trying to add second root.");
        }

        currentNode.get(currentNode.size() - 1).getChildren().add(node);
        
        if (node.getType() == INode.NodeType.ELEMENT) {
            currentNode.add(node);
        }
        

    }

    private void closeNode(StringBuilder sb) {

        if (currentNode.get(currentNode.size() - 1).getValue().equals(sb.toString())) {
            currentNode.remove(currentNode.size() - 1);
            return;
        }
        
        throw new XmlException("Incorrect tag pairing.");
    }

    @Override
    public INode parseDocument(Reader reader) throws IOException, XmlException {

        StringBuilder sb = new StringBuilder();
        currentNode = new ArrayList<INode>();

        int ci;
        boolean inTag = false;
        boolean closingTag = false;
        while ((ci = reader.read()) != -1) {

            char c = (char) ci;

            if (inTag) {

                if (c == '>') {
                    
                    if (closingTag) {
                        closeNode(sb);
                    } else {
                        addNodeToTree(createNewNode(sb));
                    }

                    sb = new StringBuilder();
                    inTag = false;
                    closingTag = false;
                    continue;
                }

                if (c == '/' && sb.length() == 0) {
                    closingTag = true;
                    continue;
                }

                if (isAlfaNum(c)) {
                    sb.append(c);
                } else {
                    throw new XmlException("Tag containts incorrect character: \"" + c + "\"");
                }


            } else {

                if (c == '<') {
                    String trimmed = sb.toString().trim();
                    
                    if (trimmed.length() > 0) {
                        addNodeToTree(createNewTextNode(trimmed));
                    }
                    
                    sb = new StringBuilder();
                    inTag = true;
                    continue;
                }

                sb.append(c);

            }


        }

        //Takto se vytvari strom elementu.
        //Doplnte implementaci, ktera ho vytvori dle XML na vstupu.
//        INode text = new TextNode("aaa");
//        INode rootNode = new ElementNode(sb.toString(), Collections.singletonList(text));
        return root;
    }
}
