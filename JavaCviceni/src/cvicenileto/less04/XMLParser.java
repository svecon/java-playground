package cvicenileto.less04;

import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLParser {

    public static void main(String[] args) {
        long x = System.nanoTime();
        
        int total = 1;
        for (int i = 0; i < total; i++) {
            DOMTargets("nbproject/build-impl.xml");
        }
        
        System.out.println("Time: " + (System.nanoTime() - x) / total);
    }

    static void DOMTargets(String path) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(path);
            Element root = document.getDocumentElement();

            NodeList nl = root.getElementsByTagName("target");
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);

                Element ne = (Element) n;

                String name = ne.getAttribute("name");
                String descrption = ne.getAttribute("description");

                System.out.println("Target " + name);
                System.out.println("  " + descrption);
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
