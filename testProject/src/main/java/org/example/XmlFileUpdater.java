import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XmlFileUpdater {

    public static void main(String[] args) throws IOException {
        String directoryPath = "C:/Experiment";
        String searchText = "<TotalTimeSeconds>";
        int searchValue = 100;
        String replaceText = "<Name>First Avenger</Name>";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".xml"));

        for (File file : files) {
            System.out.println("Processing file: " + file.getName());
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append(System.lineSeparator());
                }
            }
            String content = sb.toString();
            Document doc = Jsoup.parse(content);
            Elements elements = doc.select(searchText);
            for (Element element : elements) {
                int value = Integer.parseInt(element.text());
                if (value > searchValue) {
                    Element nameElement = element.parent().selectFirst("Name:contains(Fitbit Activity Tracker)");
                    if (nameElement != null) {
                        nameElement.html(replaceText);
                        System.out.println("Replaced Fitbit Activity Tracker with First Avenger");
                    }
                }
            }
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(doc.outerHtml());
            }
            System.out.println("File saved: " + file.getName());
        }
        System.out.println("All files processed.");
    }
}
