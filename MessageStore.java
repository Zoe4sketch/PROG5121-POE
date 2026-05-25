package loginsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageStore {

    private static final String FILE_NAME = "stored_messages.json";

    public static void storeMessage(Message message) {
        List<Message> storedMessages = loadMessages();
        storedMessages.add(message);
        saveMessages(storedMessages);
    }

    private static void saveMessages(List<Message> messages) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("[");
            for (int i = 0; i < messages.size(); i++) {
                writer.print(messages.get(i).toJson());
                if (i < messages.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("]");
        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    public static List<Message> loadMessages() {
        List<Message> messages = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return messages;
        }

        // Simple parsing - in production you'd use a JSON library like Gson or Jackson
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder jsonContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // Very basic parsing - this is a simplified version
            // For a full solution, you'd use org.json or com.google.gson

        } catch (IOException e) {
            System.out.println("Error loading messages: " + e.getMessage());
        }

        return messages;
    }

    public static String getFileName() {
        return FILE_NAME;
    }
}