package loginsystem;

import java.util.List;
import java.util.Random;

public class Message {

    private String messageId;
    private int numMessagesSent;
    private String recipient;
    private String messageText;
    private String messageHash;

    // No-argument constructor (required by some tests)
    public Message() {
        this.messageId = generateMessageId();
        this.numMessagesSent = 0;
    }

    // Constructor with parameters (used by QuickChat and tests)
    public Message(String recipient, String messageText, int currentCount) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessagesSent = currentCount;
        this.messageId = generateMessageId();
    }

    // Generate random 10-digit number
    private String generateMessageId() {
        Random random = new Random();
        long id = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Boolean: checkMessageID() - ensures ID is not more than 10 characters
    public boolean checkMessageID() {
        return messageId != null && messageId.length() <= 10;
    }

    // String: checkRecipientCell() - ensures recipient is no more than 15 chars and starts with +
    public String checkRecipientCell() {
        if (recipient == null || recipient.isEmpty()) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        if (!recipient.startsWith("+")) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        if (recipient.length() > 15) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        String numberWithoutPlus = recipient.substring(1);
        if (!numberWithoutPlus.matches("[0-9]+")) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        return "Cell phone number successfully captured.";
    }

    // Check message length
    public boolean checkMessageLength() {
        return messageText != null && messageText.length() <= 250;
    }

    // String: createMessageHash() - creates hash: first2ofID:messageNum:FIRSTWORDLASTWORD
    public String createMessageHash() {
        String firstTwo = messageId.substring(0, Math.min(2, messageId.length()));
        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        firstWord = firstWord.replaceAll("[^A-Z]", "");
        lastWord = lastWord.replaceAll("[^A-Z]", "");

        messageHash = firstTwo + ":" + numMessagesSent + ":" + firstWord + lastWord;
        return messageHash;
    }

    // String: sentMessage() - allows user to choose send, store, or disregard
    public String sentMessage() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Enter choice (1-3): ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                return "sent";
            case "2":
                return "disregarded";
            case "3":
                return "stored";
            default:
                System.out.println("Invalid choice. Defaulting to Send.");
                return "sent";
        }
    }

    // Instance method for single message display
    public String printMessage() {
        return "Message ID: " + messageId + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    // Static method: printMessages() - returns all messages sent
    public static String printMessages(List<Message> messages) {
        if (messages.isEmpty()) {
            return "No messages sent.";
        }

        StringBuilder sb = new StringBuilder();
        for (Message msg : messages) {
            sb.append(msg.printMessage()).append("\n");
            sb.append("--------------------\n");
        }
        return sb.toString();
    }

    // Static method: returnTotalMessages() - returns total number of messages sent
    public static int returnTotalMessages(List<Message> messages) {
        return messages.size();
    }

    // Store message in JSON
    public void storeMessage() {
        MessageStore.storeMessage(this);
    }

    // Getters
    public String getMessageId() { return messageId; }
    public int getNumMessagesSent() { return numMessagesSent; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }

    // For JSON serialization
    public String toJson() {
        return "  {\n" +
               "    \"messageId\": \"" + messageId + "\",\n" +
               "    \"numMessagesSent\": " + numMessagesSent + ",\n" +
               "    \"recipient\": \"" + recipient + "\",\n" +
               "    \"messageText\": \"" + messageText.replace("\"", "\\\"") + "\",\n" +
               "    \"messageHash\": \"" + messageHash + "\"\n" +
               "  }";
    }
}