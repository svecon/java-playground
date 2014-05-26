/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenileto.less04;

import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author svecon
 */
public class JDOMParser {

    public static void main(String[] args) {
        long x = System.nanoTime();
        
        int total = 1;
        for (int i = 0; i < total; i++) {
            JDOM("nbproject/build-impl.xml");
        }
        
        System.out.println("Time: " + (System.nanoTime() - x) / total);
    }

    static void JDOM(String path) {

        try {
            // the SAXBuilder is the easiest way to create the JDOM2 objects.
            SAXBuilder jdomBuilder = new SAXBuilder();
            // jdomDocument is the JDOM2 Object
            Document doc = jdomBuilder.build(path);

            Element root = doc.getRootElement();

            for (Element e : root.getChildren("target")) {
                System.out.println("Target " + e.getAttributeValue("name"));
                System.out.println("  " + e.getAttributeValue("description"));
            }

        } catch (IOException | JDOMException e) {
        }
    }

}
