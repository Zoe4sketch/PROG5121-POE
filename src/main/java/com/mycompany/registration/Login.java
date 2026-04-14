import java.util.Scanner;

public class Login {

    // Stored user details
    private String storedUsername;
    private String storedPassword;
    private String firstName;
    private String lastName;

    // ===== USERNAME CHECK =====
    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // ===== PASSWORD CHECK =====
    public boolean checkPasswordComplexity(String password) {

        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return password.length() >= 8 && hasUpperCase && hasNumber && hasSpecial;
    }

    // ===== CELL PHONE CHECK =====
    public boolean checkCellPhoneNumber(String cellNumber) {

        if (cellNumber.length() != 12) {
            return false;
        }

        if (!cellNumber.startsWith("+27")) {
            return false;
        }

        for (int i = 3; i < cellNumber.length(); i++) {
            if (!Character.isDigit(cellNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // ===== REGISTER USER =====
    public String registerUser(String username, String password) {

        boolean validUsername = checkUsername(username);
        boolean validPassword = checkPasswordComplexity(password);

        if (!validUsername && !validPassword) {
            return "Username is not correctly formatted.\n"
                 + "Password is not correctly formatted.\n"
                 + "Please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!validUsername) {
            return "Username is not correctly formatted.\n"
                 + "Please ensure your username contains an underscore and is no more than five characters.";
        }

        if (!validPassword) {
            return "Password is not correctly formatted.\n"
                 + "Please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        // Store details if valid
        this.storedUsername = username;
        this.storedPassword = password;

        return "Username and password successfully captured.\nUser registered successfully.";
    }

    // ===== LOGIN USER =====
    public boolean loginUser(String enteredUsername, String enteredPassword) {

    if (storedUsername == null || storedPassword == null) {
        return false;
    }

    return enteredUsername.equals(storedUsername) &&
           enteredPassword.equals(storedPassword);
}

    // ===== RETURN LOGIN STATUS =====
    public String returnLoginStatus(String firstName, String lastName, boolean loginStatus) {

        if (loginStatus) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    

public void register(Scanner sc) {

    System.out.print("Enter first name: ");
    this.firstName = sc.nextLine();

    System.out.print("Enter last name: ");
    this.lastName = sc.nextLine();

    System.out.print("Enter username: ");
    String username = sc.nextLine();

    System.out.print("Enter password: ");
    String password = sc.nextLine();

    System.out.print("Enter cell phone number: ");
    String phone = sc.nextLine();

    // Show registration result
    System.out.println(registerUser(username, password));

    // Check phone number
    if (checkCellPhoneNumber(phone)) {
        System.out.println("Cell phone number successfully added.");
    } else {
        System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
    }
}
public void login(Scanner sc) {

    System.out.print("Enter username: ");
    String username = sc.nextLine();

    System.out.print("Enter password: ");
    String password = sc.nextLine();

    boolean status = loginUser(username, password);

    String message = returnLoginStatus(firstName, lastName, status);

    System.out.println(message);
}
}
