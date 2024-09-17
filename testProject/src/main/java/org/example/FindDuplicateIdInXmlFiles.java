package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FindDuplicateIdInXmlFiles {
    public static void main(String[] args) throws IOException {
        String directoryPath = "C:/All";
        File directory = new File(directoryPath);
        Map<String, Integer> idMap = new HashMap<String, Integer>();

        // loop through all XML files in the directory
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".xml")) {
                byte[] bytes = Files.readAllBytes(file.toPath());
                String content = new String(bytes, "UTF-8");
                int index = 0;

                // find all occurrences of <Id> tag and its value
                while (true) {
                    index = content.indexOf("<Id>", index);
                    if (index == -1) {
                        break;
                    }
                    int endIndex = content.indexOf("</Id>", index);
                    String idValue = content.substring(index + 4, endIndex);

                    // add the id value to the map, incrementing the count if it already exists
                    if (idMap.containsKey(idValue)) {
                        idMap.put(idValue, idMap.get(idValue) + 1);
                    } else {
                        idMap.put(idValue, 1);
                    }

                    index = endIndex;
                }
            }
        }

        // print the list of duplicate id values and the files they appear in
        for (Map.Entry<String, Integer> entry : idMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("Duplicate Id value '" + entry.getKey() + "' found in the following files:");
                for (File file : directory.listFiles()) {
                    if (file.getName().endsWith(".xml")) {
                        byte[] bytes = Files.readAllBytes(file.toPath());
                        String content = new String(bytes, "UTF-8");
                        if (content.contains("<Id>" + entry.getKey() + "</Id>")) {
                            System.out.println(file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
}
