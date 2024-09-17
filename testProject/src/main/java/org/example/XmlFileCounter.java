package org.example;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class XmlFileCounter {
    public static void main(String[] args) {
        String dirPath = "C:\\Strength"; // Replace with the path to your 'All' directory
        File dir = new File(dirPath);
        int count = 0;
        if (dir.isDirectory()) {
            File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".tcx"));
            for (File file : files) {
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(file);
                    doc.getDocumentElement().normalize();
                    NodeList nodeList = doc.getElementsByTagName("Activity");
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Element element = (Element) nodeList.item(i);
                        String totalTimeSeconds = element.getElementsByTagName("TotalTimeSeconds").item(0).getTextContent();
                        if (Integer.parseInt(totalTimeSeconds) >= 3600 && Integer.parseInt(totalTimeSeconds) < 5400) {
                            count++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Invalid directory path");
        }
        System.out.println("Number of XML files with <TotalTimeSeconds> > 5400: " + count);
    }
}
