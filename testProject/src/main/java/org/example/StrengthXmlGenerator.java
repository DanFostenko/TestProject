package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StrengthXmlGenerator {

    public static void main(String[] args) throws IOException {
        String csvFile = "C:\\Strength\\Strength.csv";
        String xmlFile = "C:\\Strength\\StrengthTemplate.xml";
        String outputDir = "C:\\Strength\\Output";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String xml = new String(Files.readAllBytes(Paths.get(xmlFile)));
                xml = xml.replaceAll("<Id>.*</Id>", "<Id>" + values[0] + "</Id>");
                xml = xml.replaceAll("<Lap StartTime=\".*\">", "<Lap StartTime=\"" + values[0] + "\">");
                xml = xml.replaceAll("<Time1>.*</Time1>", "<Time1>" + values[0] + "</Time1>");
                xml = xml.replaceAll("<TotalTimeSeconds>.*</TotalTimeSeconds>", "<TotalTimeSeconds>" + values[2] + "</TotalTimeSeconds>");
                xml = xml.replaceAll("<Calories>.*</Calories>", "<Calories>" + values[3] + "</Calories>");
                xml = xml.replaceAll("<Time2>.*</Time2>", "<Time2>" + values[1] + "</Time2>");

                String fileName = values[0].replaceAll(":", "") + ".xml";
                Path outputFile = Paths.get(outputDir, fileName);
                Files.write(outputFile, xml.getBytes());
            }
        }
    }
}
