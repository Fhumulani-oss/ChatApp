package com.mycompany.chatapp;
//The Login class handles user registration and login.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class Login {
    
    //Variables to store the registered user's information
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellPhone;
    private String firstName;
    private String lastName;
    
    // For Part 2 ChatApp
    public Login(){
    }
    //Constructor
    //Checks that the username contains an underscore and is no more than five characters.
    public Login(String firstName, String lastName, String registeredUsername, String registeredPassword, String registeredCellPhone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredUsername = registeredUsername;
        this.registeredPassword = registeredPassword;
        this.registeredCellPhone = registeredCellPhone;
    }
    
    //Checks if the username contains an underscore
    //and is no more than 5 characters long
    public boolean checkUserName(String username){
        return username.contains("_") && username.length()<=5;
    }
    
    //Checks if the password meets the required complexity
    //Checks password length, capital letter, and special character.
    public boolean checkPasswordComplexity(String password){
            if(password.length() < 8){
            return false;
        }
        
        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        
        for(int i = 0; i < password.length(); i++){
            
            char character = password.charAt(i);
            
            if(Character.isUpperCase(character)){
                hasCapitalLetter = true;
            }
            if(Character.isDigit(character)){
                hasNumber = true;
            }
            if(!Character.isLetterOrDigit(character)){
                hasSpecialCharacter = true;
            }
        }
        
        return hasCapitalLetter && hasNumber && hasSpecialCharacter;
    }
    
    //Checks if the cellphone number has the South African
    //international country code +27 and 9 digits after it
    //Checks that the cellphone number follows the required South African format.
    public boolean checkCellPhoneNumber(String cellPhone){
        
        String regex = "^\\+27[0-9]{9}$";
        
        return cellPhone.matches(regex);
    }
    
    //Registers the user
    public String registerUser(String username, String password, String cellPhone){
        
        if(!checkUserName(username)){
            return "Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.";
        }
        if(!checkPasswordComplexity(password)){
            return "Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a captital letter, a number, and "
                    + "a special character.";
        }
        if(!checkCellPhoneNumber(cellPhone)){
            return "Cell number is incorrectly formatted or does not contain an international "
                    + "code; please correct the number and try again.";
        }
        
        registeredUsername = username;
        registeredPassword = password;
        registeredCellPhone = cellPhone;
        
        return "Username successfully captured."
                + "Password successfuly captured."
                + "Cell number successfully captured.";
    }
    
    //Checks whether the entered login details are correct
    public boolean loginUser(String username, String password){
        
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }
    
    //Returns the login status message
    public String returnLoginStatus(String username, String password){
        
        if(loginUser(username, password)){
            return "Welcome " +  firstName + ", " + lastName + ", it is great to see you again.";
        }
        
        return "Username or password incorrect, please try again.";
    }
}
