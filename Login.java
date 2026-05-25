package loginsystem;


public class Login {

    private Registration registeredUser;

   

    public Login(Registration registeredUser) {
        this.registeredUser = registeredUser;
    }

  

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername == null || enteredPassword == null) return false;
        return enteredUsername.equals(registeredUser.getUsername())
            && enteredPassword.equals(registeredUser.getPassword());
    }

    public String returnLoginStatus(String enteredUsername, String enteredPassword,
                                    String firstName, String lastName) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}