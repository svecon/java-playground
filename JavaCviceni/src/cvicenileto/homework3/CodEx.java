
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * DU 3.
 *
 * @author Ondrej Svec
 */
public class CodEx {

    public static void main(String[] argv) throws IOException, JDOMException {
        Crawler c = new Crawler(System.in);
        c.StartCrawling();
        c.Print();
    }

    static class Crawler {

        private static class Section {

            public String Title;
            ArrayList<Link> links = new ArrayList<>();

            public Section(String title) {
                Title = title;
            }

            public void AddLink(Link l) {
                links.add(l);
            }

            public void Print(HashMap<String, Section> ids) {

                if (links.isEmpty()) {
                    return;
                }

                System.out.println(Title + ":");

                for (Link link : links) {
                    System.out.print("    " + link.Text);
                    System.out.println(" (" + ids.get(link.Target).Title + ")");
                }
            }
        }

        private static class Link {

            public String Text;
            public String Target;

            public Link(String text, String target) {
                Text = text;
                Target = target;
            }
        }

        Document doc;
        ArrayList<Section> sections = new ArrayList<>();
        HashMap<String, Section> ids = new HashMap<>();

        public Crawler(InputStream input) throws JDOMException, IOException {
            doc = new SAXBuilder().build(input);
        }

        public void StartCrawling() {
            Element root = doc.getRootElement();
            Element title = root.getChild("title");

            crawlRecursive(root, title == null ? null : title.getText(), null);
        }

        void crawlRecursive(Element root, String title, Section section) {

            for (Element e : root.getChildren()) {
                switch (e.getName()) {
                    case "section":
                        sections.add(section = new Section(title = getAllText(e.getChild("title"))));
                        break;
                    case "link":
                        section.AddLink(new Link(getAllText(e), e.getAttributeValue("linkend")));
                        break;
                }

                if (e.getAttribute("id") != null) {
                    ids.put(e.getAttributeValue("id"), section);
                }

                crawlRecursive(e, title, section);
            }

        }

        public void Print() {
            for (Section s : sections) {
                s.Print(ids);
            }
        }

        String getAllText(Element e) {

            StringBuilder sb = new StringBuilder();
            LinkedList<Element> els = new LinkedList<>();
            els.add(e);

            while (els.isEmpty() == false) {
                Element el = els.poll();

                sb.append(el.getText());

                for (Element ee : el.getChildren()) {
                    els.add(ee);
                }
            }

            return sb.toString();
        }
    }
}
