/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;
//JUnit tests for registration and login functionality.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    Login login = new Login("Kyle", "Smith", "", "","");
    
    //=====================
    //assertEquals TESTS
    //=====================
    
    @Test
    public void testUsernameCorrectlyFormatted(){
        assertEquals(true, login.checkUserName("kyl_1"));
    }
    
    @Test
    public void testUsernameIncorrectlyFormatted(){
        assertEquals("Username is not correctly formatted; please ensure that your username contains an "
                + "underscore and is no more than five characters in length.", login.registerUser("kyle!!!!!!!",
                  "Ch&&sec@ke99!", "+27838968976"));
    }
    
    @Test
    public void testPasswordMeetsComplexityRequirements(){
        assertEquals(true, login.checkPasswordComplexity("Ch&&sec@ke99"));
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements(){
        assertEquals(false, login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCellPhoneCorrectlyFormatted(){
        assertEquals(true, login.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormatted(){
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and "
                + "try again.", login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553"));
    }
    
    //==============================
    //assertTrue / assertFalse TESTS
    //==============================
    
    @Test
    public void testLoginSuccessful(){
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    @Test
    public void testLoginFailed(){
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        assertFalse(login.loginUser("wrong", "wrongpassword"));
    }
    
    @Test
    public void testUserNameCorrectlyFormattedWithAssertTrue(){
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    @Test
    public void testUserNameIncorrectlyFormattedWithAssertFalse(){
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testPasswordCorrectlyFormattedWithAssertTrue(){
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testPasswordIncorrectlyFormattedWithAssertFalse(){
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCellPhoneCorrectlyFormattedWithAssertTrue(){
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormattedWithAssertFalse(){
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}

