import java.util.Date;

public class TimestampConverter {
    public static void main(String[] args) {
        long timestamp = 1698397454309L; // Your timestamp

        // Create a Date object from the timestamp
        Date date = new Date(timestamp);

        // Display the date and time
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Date and Time: " + date);
    }
}