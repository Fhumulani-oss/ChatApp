/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
//ChatApp main class for the console-based chat application.
/**
 *
 * @author Student
 */
import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password;
         String username;
         String cellPhone; 
         
        boolean validUserName;
        boolean validPassword;
        boolean validCellPhone;
        System.out.println("=========================");
        System.out.println("        CHAT APP         ");
        System.out.println("=========================");
        
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();
        
        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();
        
        
        
        System.out.println("\n---Registration ---");
        
//        System.out.println("Enter username: ");
//        String username = scanner.nextLine();
  Login login = new Login(firstName, lastName,"", "","");
            do{
                System.out.println("Enter your Username(eg: kyl_1):  ");
                  username = scanner.nextLine();
                Login user = new Login(username,""," ","","");
                validUserName = user.checkUserName(username);
             }while(!validUserName);

            do{
                 System.out.println("Enter password(eg: Ch&&sec@ke99!): ");
                password = scanner.nextLine();
                Login user = new Login("",password," ","","");
                validPassword = user.checkPasswordComplexity(password);
             }while(!validPassword);
        
        do{
                
        System.out.println("Enter South African cell phone number(eg: +27838968976): ");
        cellPhone = scanner.nextLine();
      
                validCellPhone = login.checkCellPhoneNumber(cellPhone);
             }while(!validCellPhone);
//        
//        System.out.println("Enter South African cell phone number: ");
//        String cellPhone = scanner.nextLine();
//        Login login = new Login(firstName, lastName,"", "",cellPhone);
        
        
        String registrationMessage = login.registerUser(username, password, cellPhone);
        
        System.out.println("\n" + registrationMessage);
        
        System.out.println("\n--- Login ---");
        
        System.out.println("Enter username: ");
        String loginUsername = scanner.nextLine();
        
        System.out.println("Enter password: ");
        String loginPassword = scanner.nextLine();
        
        String loginMessage = login.returnLoginStatus(loginUsername, loginPassword);
        
        System.out.println("\n" + loginMessage);
        
        scanner.close();
    }
}
