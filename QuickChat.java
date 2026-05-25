package loginsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickChat {

    private String firstName;
    private String lastName;
    private List<Message> sentMessages;
    private Scanner scanner;

    public QuickChat(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sentMessages = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to QuickChat.");
        System.out.println("Hello, " + firstName + " " + lastName + "!\n");

        boolean running = true;

        while (running) {
            displayMenu();

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    sendMessagesFeature();
                    break;
                case "2":
                    System.out.println("\n--- Coming Soon. ---\n");
                    break;
                case "3":
                    System.out.println("\nThank you for using QuickChat. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please enter 1, 2, or 3.\n");
            }
        }

        // Display total messages sent before exiting
        if (!sentMessages.isEmpty()) {
            System.out.println("\n========================================");
            System.out.println("Total messages sent: " + sentMessages.size());
            System.out.println("========================================\n");
        }

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("--- QuickChat Menu ---");
        System.out.println("Option 1) Send Messages");
        System.out.println("Option 2) Show recently sent messages");
        System.out.println("Option 3) Quit");
        System.out.println();
    }

    private void sendMessagesFeature() {
        System.out.print("\nHow many messages do you wish to send? ");
        int numMessages;

        try {
            numMessages = Integer.parseInt(scanner.nextLine().trim());
            if (numMessages <= 0) {
                System.out.println("Please enter a positive number.\n");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.\n");
            return;
        }

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

            // Get recipient
            System.out.print("Enter recipient cell number (with international code, e.g. +27718693002): ");
            String recipient = scanner.nextLine().trim();

            // Get message
            System.out.print("Enter your message (max 250 characters): ");
            String messageText = scanner.nextLine().trim();

            // Create message object
            Message message = new Message(recipient, messageText, sentMessages.size());

            // Validate message length
            if (!message.checkMessageLength()) {
                System.out.println("Please enter a message of less than 250 characters.");
                i--; // Retry this message
                continue;
            }

            // Validate recipient
            String recipientCheck = message.checkRecipientCell();
            if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                System.out.println(recipientCheck);
                i--; // Retry this message
                continue;
            }

            // Display message hash
            System.out.println("Message Hash: " + message.createMessageHash());

            // Ask user what to do with the message
            String action = message.sentMessage();

            switch (action) {
                case "sent":
                    sentMessages.add(message);
                    System.out.println("\nMessage successfully sent.");
                    System.out.println("--- Message Details ---");
                    System.out.println(message.printMessage());
                    break;
                case "disregarded":
                    System.out.println("\nPress 0 to delete the message.");
                    String deleteConfirm = scanner.nextLine().trim();
                    if (deleteConfirm.equals("0")) {
                        System.out.println("Message deleted.\n");
                    }
                    break;
                case "stored":
                    System.out.println("\nMessage successfully stored.");
                    message.storeMessage();
                    break;
            }

            System.out.println();
        }
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }
}