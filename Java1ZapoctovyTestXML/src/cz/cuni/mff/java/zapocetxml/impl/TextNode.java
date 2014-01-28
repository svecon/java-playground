/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cuni.mff.java.zapocetxml.impl;

import cz.cuni.mff.java.zapocetxml.api.INode;
import java.util.Collections;
import java.util.List;

/**
 * Node reprezentujici text. Muzete si ji jakkoliv upravit, budete-li chtit.
 * @author Martin Cerny
 */
class TextNode implements INode {

    String text;

    public TextNode(String text) {
        this.text = text;
    }

    @Override
    public NodeType getType() {
        return NodeType.TEXT;
    }

    @Override
    public String getValue() {
        return text;
    }

    @Override
    public List<INode> getChildren() {
        return Collections.EMPTY_LIST;
    }

}
