package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class JsonParser {
    public static void main(String[] args) {
        try {
            // Create a Scanner object for user input
            Scanner scanner = new Scanner(System.in);

            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("input\\your-json-file.json");

            // Read JSON file into JsonNode
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            // Prompt the user to enter the attribute name
            System.out.print("Enter the name of the attribute: ");
            String attributeName = scanner.nextLine();

            // Access the 'data' array
            JsonNode dataArray = rootNode.get("data");

            if (dataArray != null && dataArray.isArray()) {
                // Iterate through each element in the 'data' array
                for (JsonNode dataNode : dataArray) {
                    // Access the specified attribute within each 'data' element
                    JsonNode attributeNode = dataNode.get(attributeName);
                    if (attributeNode != null) {
                        //System.out.println(attributeName + ": " + attributeNode);
                        System.out.println(attributeNode);
                    } else {
                        System.out.println(attributeName + " not found for this element.");
                    }
                }
            } else {
                System.out.println("'data' array not found or not an array.");
            }

            // Close the Scanner
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/** Print entire JSON
 * public class JsonParser {
 *     public static void main(String[] args) {
 *         try {
 *             ObjectMapper objectMapper = new ObjectMapper();
 *             File jsonFile = new File("input\\your-json-file.json");
 *
 *             // Read JSON file into JsonNode
 *             JsonNode rootNode = objectMapper.readTree(jsonFile);
 *
 *             // Print the entire JSON structure
 *             System.out.println("JSON Structure:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
 *
 *         } catch (IOException e) {
 *             e.printStackTrace();
 *         }
 *     }
 * }
 */