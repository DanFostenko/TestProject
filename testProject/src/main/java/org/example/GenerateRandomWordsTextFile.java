import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateRandomWordsTextFile {
    public static void main(String[] args) {
        int numLines = 5000;
        String outputDirectory = "output";
        Random random = new Random();

        try {
            // Create the output directory if it doesn't exist
            File outputDir = new File(outputDirectory);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            String fileName = outputDirectory + File.separator + "random_words.txt";
            generateTextFile(fileName, generateRandomWords(random, numLines));
            System.out.println("Generated text file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateTextFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }

    private static String generateRandomWords(Random random, int numLines) {
        StringBuilder lines = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < numLines; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                if (j > 0) {
                    line.append("\t"); // Tab used as column delimiter
                }

                if (random.nextBoolean()) {
                    // Generate a random word
                    int wordLength = random.nextInt(10) + 1; // Random word length between 1 and 10 characters
                    StringBuilder word = new StringBuilder();
                    for (int k = 0; k < wordLength; k++) {
                        char randomChar = characters.charAt(random.nextInt(characters.length()));
                        word.append(randomChar);
                    }
                    line.append(word.toString());
                }
            }
            lines.append(line.toString());

            if (i < numLines - 1) {
                lines.append("\n"); // New line after each line
            }
        }

        return lines.toString();
    }
}
