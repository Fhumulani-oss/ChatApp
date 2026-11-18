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
    public void testSentMessagesArrayPopulated(){
        Message.populateTestData();
        assertEquals(2, Message.sentMessages.size());
        assertEquals("Did you get the cake?", Message.sentMessages.get(0).messageText);
        assertEquals("It is dinner time !", Message.sentMessages.get(1).messageText);
    }
    @Test
    public void testLongestMessage(){
        Message.populateTestData();
        assertEquals("Where are you? You are late! I have asked you to be on time.", Message.displayLongestStoredMessage());
    }
    @Test
    public void testSearchByID(){
        Message.populateTestData();
        String result = Message.searchByRecipient("0838884567");
        assertTrue(result.contains("It is dinner time !"));
    }
    @Test
    public void testSearchByRecipient(){
        Message.populateTestData();
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));       
    }
    @Test
    public void testDeleteByHash(){
        Message.populateTestData();
        String hash = Message.storedMessages.get(0).messageHash;
        String result = Message.deleteByHash(hash);
        assertTrue(result.contains("successfully deleted"));
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
    }
    @Test
    public void testDisplayReport(){
        Message.populateTestData();
        String report = Message.displayReport();
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("Hash:"));
    }
}

    
   
    
    
    
    