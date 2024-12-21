
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    public static void saveToJsonFile(String filePath, Configuration config) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(config.toJson());
            System.out.println("CLI.Configuration saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    public static Configuration loadFromJsonFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }
            System.out.println("Read JSON: " + jsonBuilder.toString()); // Debug log
            return Configuration.fromJson(jsonBuilder.toString());
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
}