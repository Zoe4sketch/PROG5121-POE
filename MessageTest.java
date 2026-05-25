package loginsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class MessageTest {

    // Test Data 1
    private Message createTestMessage1() {
        return new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
    }

    // Test Data 2
    private Message createTestMessage2() {
        return new Message("08575975889", "Hi Keegan, did you receive the payment?", 1);
    }

    @Test
    public void testCheckMessageID() {
        Message msg = createTestMessage1();
        assertTrue(msg.checkMessageID(), "Message ID should be 10 digits or less");
    }

    @Test
    public void testCheckRecipientCell_Success() {
        Message msg = createTestMessage1();
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_Failure_NoPlus() {
        Message msg = new Message("27718693002", "Test message", 0);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", 
                     msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_Failure_TooLong() {
        Message msg = new Message("+27718693002123456789", "Test message", 0);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", 
                     msg.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = createTestMessage1();
        String hash = msg.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains(":"), "Hash should contain colon");
        String[] parts = hash.split(":");
        assertEquals(3, parts.length, "Hash should have 3 parts");
        assertEquals("0", parts[1], "Message number should be 0");
        assertTrue(parts[2].startsWith("HI"), "Hash should start with HI");
        assertTrue(parts[2].endsWith("TONIGHT"), "Hash should end with TONIGHT");
    }

    @Test
    public void testMessageLength_Success() {
        Message msg = createTestMessage1();
        assertTrue(msg.checkMessageLength(), "Message should be under 250 chars");
    }

    @Test
    public void testMessageLength_Failure() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMessage.append("a");
        }
        Message msg = new Message("+27718693002", longMessage.toString(), 0);
        assertFalse(msg.checkMessageLength(), "Message should exceed 250 chars");
    }

    @Test
    public void testReturnTotalMessages() {
        List<Message> messages = new ArrayList<>();
        assertEquals(0, Message.returnTotalMessages(messages));

        messages.add(createTestMessage1());
        assertEquals(1, Message.returnTotalMessages(messages));

        messages.add(createTestMessage2());
        assertEquals(2, Message.returnTotalMessages(messages));
    }

    @Test
    public void testPrintMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(createTestMessage1());
        messages.add(createTestMessage2());

        String result = Message.printMessages(messages);
        assertTrue(result.contains("Message ID:"), "Should contain Message ID");
        assertTrue(result.contains("+27718693002"), "Should contain recipient");
        assertTrue(result.contains("Hi Keegan"), "Should contain Keegan's message");
    }

    @Test
    public void testMessageIdGenerated() {
        Message msg = createTestMessage1();
        assertNotNull(msg.getMessageId());
        assertEquals(10, msg.getMessageId().length(), "Message ID should be 10 digits");
    }
}