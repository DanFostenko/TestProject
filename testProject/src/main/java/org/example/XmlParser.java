package org.example;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XmlParser {
    public static void main(String[] args) {
        try {
            File dir = new File("C:/All");
            File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".xml"));

            for (File file : files) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("Id");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        System.out.println("Id: " + element.getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
