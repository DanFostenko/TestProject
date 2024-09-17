import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateRandomPDFs {
    public static void main(String[] args) {
        int numPDFs = 2500;
        String outputDirectory = "output";
        Random random = new Random();

        try {
            // Create the output directory if it doesn't exist
            File outputDir = new File(outputDirectory);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            for (int i = 1; i <= numPDFs; i++) {
                String fileName = outputDirectory + File.separator + generateRandomNumber() + ".pdf";
                String content = generateRandomContent(random);
                createPDF(fileName, content);
                System.out.println("Generated PDF: " + fileName);
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void createPDF(String filePath, String content) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        document.add(new Paragraph(content));
        document.close();
    }

    private static String generateRandomContent(Random random) {
        // Generate 10 random digits followed by 100 random words of random sizes separated by commas
        String randomDigits = generateRandomNumber();
        String randomWords = generateRandomWords(random, 30000);
        return randomDigits + "; " + randomWords;
    }

    private static String generateRandomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000000) + 1000000000);
    }

    private static String generateRandomWords(Random random, int wordCount) {
        // Generate random words of random lengths using a stream of characters
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        return IntStream.range(0, wordCount)
                .mapToObj(i -> generateRandomWord(random, characters))
                .collect(Collectors.joining(", "));
    }

    private static String generateRandomWord(Random random, String characters) {
        int wordLength = random.nextInt(10) + 1; // Random word length between 1 and 10 characters
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            word.append(randomChar);
        }
        return word.toString();
    }
}
