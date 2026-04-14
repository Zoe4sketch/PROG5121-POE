 // Username check
    public static boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password check
    public static boolean checkPassword(String password) {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    // Phone number check (REGEX)
    public static boolean checkPhoneNumber(String phone) {
        return phone.matches("^\\+27\\d{9}$");
    }
}
public class Login {

    // Stored user details
    private String storedUsername;
    private String storedPassword;

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
}
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Login user = new Login();

        int choice;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine(); // FIX for Scanner (important!)

            switch (choice) {
                case 1:
                    user.register(sc);
                    break;

                case 2:
                    user.login(sc);
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 3);

        sc.close();
    }
}



