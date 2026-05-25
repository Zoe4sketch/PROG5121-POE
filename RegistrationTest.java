package loginsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {

    @Test
    public void testUsernameCorrectlyFormatted_assertEquals() {
        Registration reg = new Registration("kyl_1", "Password1!", "+27831234567");
        assertEquals("Username successfully captured.", reg.checkUserNameMessage());
    }

    @Test
    public void testUsernameIncorrectlyFormatted_assertEquals() {
        Registration reg = new Registration("kyleeeee", "Password1!", "+27831234567");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", 
                     reg.checkUserNameMessage());
    }

    @Test
    public void testUsernameCorrectlyFormatted_assertTrue() {
        Registration reg = new Registration("kyl_1", "Password1!", "+27831234567");
        assertTrue(reg.checkUserName(), "Username should be valid");
    }

    @Test
    public void testUsernameIncorrectlyFormatted_assertFalse() {
        Registration reg = new Registration("kyleeeee", "Password1!", "+27831234567");
        assertFalse(reg.checkUserName(), "Username should be invalid");
    }

    @Test
    public void testPasswordMeetsComplexity_assertTrue() {
        Registration reg = new Registration("kyl_1", "Password1!", "+27831234567");
        assertTrue(reg.checkPasswordComplexity(), "Password should meet complexity");
    }

    @Test
    public void testPasswordDoesNotMeetComplexity_assertFalse() {
        Registration reg = new Registration("kyl_1", "password", "+27831234567");
        assertFalse(reg.checkPasswordComplexity(), "Password should not meet complexity");
    }

    @Test
    public void testCellPhoneCorrectlyFormatted_assertTrue() {
        Registration reg = new Registration("kyl_1", "Password1!", "+27831234567");
        assertTrue(reg.checkCellPhoneNumber(), "Cell phone should be valid");
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted_assertFalse() {
        Registration reg = new Registration("kyl_1", "Password1!", "0831234567");
        assertFalse(reg.checkCellPhoneNumber(), "Cell phone should be invalid");
    }

    @Test
    public void testLoginSuccessful_assertTrue() {
        Registration reg = new Registration("kyl_1", "Password1!", "+27831234567");
        Login login = new Login(reg);
        assertTrue(login.loginUser("kyl_1", "Password1!"), "Login should succeed");
    }

    @Test
    public void testLoginFailed_assertFalse() {
        Registration reg = new Registration("kyl_1", "Password1!", "+27831234567");
        Login login = new Login(reg);
        assertFalse(login.loginUser("wrong", "wrong"), "Login should fail");
    }

    @Test
    public void testLoginSuccessMessage_assertEquals() {
        Registration reg = new Registration("kyl_1", "Password1!", "+27831234567");
        Login login = new Login(reg);
        String result = login.returnLoginStatus("kyl_1", "Password1!", "Kyle", "Smith");
        assertEquals("Welcome Kyle, Smith it is great to see you.", result);
    }

    @Test
    public void testLoginFailedMessage_assertEquals() {
        Registration reg = new Registration("kyl_1", "Password1!", "+27831234567");
        Login login = new Login(reg);
        String result = login.returnLoginStatus("wrong", "wrong", "Kyle", "Smith");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}