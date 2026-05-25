package loginsystem;


public class Registration {

    private String username;
    private String password;
    private String cellPhoneNumber;

   

    public Registration(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

 

    public boolean checkUserName() {
        if (username != null && username.contains("_") && username.length() <= 5) {
            return true;
        }
        return false;
    }

    public String checkUserNameMessage() {
        if (checkUserName()) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }



    public boolean checkPasswordComplexity() {
        if (password == null) return false;

        boolean hasLength    = password.length() >= 8;
        boolean hasUpper     = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit     = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial   = password.chars().anyMatch(c ->
                "!@#$%^&*()-_=+[]{}|;:',.<>?/`~".indexOf(c) >= 0);

        return hasLength && hasUpper && hasDigit && hasSpecial;
    }

    public String checkPasswordComplexityMessage() {
        if (checkPasswordComplexity()) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

   

    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber == null) return false;
        
        return cellPhoneNumber.matches("^\\+[0-9]{1,3}[0-9]{6,10}$");
    }

    public String checkCellPhoneNumberMessage() {
        if (checkCellPhoneNumber()) {
            return "Cell phone number successfully added.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
    }

    
    public String registerUser() {
        StringBuilder sb = new StringBuilder();
        sb.append(checkUserNameMessage()).append("\n");
        sb.append(checkPasswordComplexityMessage()).append("\n");
        sb.append(checkCellPhoneNumberMessage());
        return sb.toString();
    }


    public String getUsername()        { return username; }
    public String getPassword()        { return password; }
    public String getCellPhoneNumber() { return cellPhoneNumber; }
}