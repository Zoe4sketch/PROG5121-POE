package loginsystem;

import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PROG5121POE — Registration & Login ===\n");

        System.out.println("--- Create Account ---");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter username (must contain '_' and be max 5 characters): ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password (min 8 chars, 1 capital, 1 number, 1 special char): ");
        String password = scanner.nextLine().trim();

        System.out.print("Enter South African cell phone number (e.g. +27831234567): ");
        String cellPhone = scanner.nextLine().trim();

        Registration reg = new Registration(username, password, cellPhone);

        System.out.println("\n--- Registration Status ---");
        System.out.println(reg.registerUser());

        // Only proceed to login if all fields are valid
        if (!reg.checkUserName() || !reg.checkPasswordComplexity() || !reg.checkCellPhoneNumber()) {
            System.out.println("\nRegistration failed. Please correct the errors above and try again.");
            scanner.close();
            return;
        }

        // STEP 2: Login
        System.out.println("\n--- Login ---");

        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine().trim();

        Login login = new Login(reg);
        System.out.println("\n--- Login Status ---");
        String loginResult = login.returnLoginStatus(enteredUsername, enteredPassword, firstName, lastName);
        System.out.println(loginResult);

        // STEP 3: Launch QuickChat if login successful
        if (login.loginUser(enteredUsername, enteredPassword)) {
            System.out.println("\n========================================");
            QuickChat quickChat = new QuickChat(firstName, lastName);
            quickChat.start();
        }

        scanner.close();
    }
}