/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    @Test
    public void testRecipientSuccess(){
        Message m = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }
    @Test
    public void testRecipientFailure(){
        Message m = new Message("08575975889", "Hi Keegan, did you receive the payment?", 2);
        assertTrue(m.checkRecipientCell().contains("incorrectly formatted"));
    }
    @Test
    public void testMessageHash(){
        Message m = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        m.messageID = "0012345678";
        m.messageText = "Hi Mike, can you join us for dinner tonight?";
        String hash = m.createMessageHash();
        assertEquals("00:0:HITONIGHT", hash);
    }
    @Test
    public void testSentMessages(){
        Message m = new Message("+27718693002", "Test", 1);
        assertEquals("Message successfully sent.", m.SentMessage(1));
        assertEquals("Press 0 to delete the message.", m.SentMessage(2));
        assertEquals("Message successfully stored.", m.SentMessage(3));
    }
}

    
   
    
    
    
    