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
           
        // === PART 1 LOGIN (keep your login logic) ===
        Login login = new Login();
        // If your login uses JOptionPane, keep it. This is a simple check:
        // For now we assume login passed to show Part 2
        boolean loggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!"); //change to your login method if you have one: loginObj.loginUser()
        
        if(!loggedIn){
            System.out.println("Login failed. Caanot send messages.");
            loggedIn = true;
        }
        
        // === PART 2 REQUIREMENT ===
        System.out.println("Welcome to ChatApp.");
        
        while(true){
            System.out.println("\n-- ChatApp Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("Choose: ");
            
            int menuChoice = 0;
            try{
                menuChoice = Integer.parseInt(scanner.nextLine());
            }catch(Exception e){
                menuChoice = 0;
            }
            
            if(menuChoice == 1){
                System.out.println("How many messages do you wish to enter? ");
                int numMessages = 2;
                try{
                    numMessages = Integer.parseInt(scanner.nextLine());
                }catch(Exception e){
                    numMessages = 1;
                }
                
                for(int i = 1; i <= numMessages; i++){
                    System.out.println("\nMessage " + i + "/" + numMessages);
                    System.out.println("Enter recipient (+27...): ");
                    String recipient = scanner.nextLine();
                    
                    System.out.println("Enter message (max 250 chars): ");
                    String text = scanner.nextLine();
                    
                    if(text.length() > 250){
                        System.out.println("Please enter a message of less than 250 characters.");
                        i--; //ask again for same message
                        continue;
                    }else{
                        System.out.println("Message sent");
                    }
                    
                    Message msg = new Message(recipient, text, i);
                    System.out.println(msg.checkRecipientCell());
                    
                    if(msg.checkRecipientCell().contains("incorrectly")){
                        i--;
                        continue;
                    }
                    
                    System.out.println("Choose: 1=Send Message 2=Disregard (Press 0 to delete) 3=Store to send later");
                    int sendChoice = 1;
                    try{
                        sendChoice = Integer.parseInt(scanner.nextLine());
                    }catch(Exception e){
                        sendChoice = 1;
                    }
                    
                    System.out.println(msg.SentMessage(sendChoice));
                    
                    if(sendChoice == 1 || sendChoice == 3){
                        msg.storeMessage();
                        System.out.println("\n--- FULL DETAILS ---");
                        System.out.println(msg.printMessages());
                    }
                  }
                System.out.println("\nTotal messages processed: ");
                
                }else if(menuChoice == 2){
                    System.out.println("Coming Soon.");
                }else if(menuChoice == 3){
                    System.out.println("Quitting...");
                    break;
                }else{
                    System.out.println("Invalid option. Try again.");
                }
             }
        scanner.close();
    }
}
