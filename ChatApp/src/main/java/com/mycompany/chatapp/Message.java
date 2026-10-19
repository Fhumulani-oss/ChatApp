/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Student
 */

public class Message {
    public String messageID;
    public String recipient;
    public String messageText;
    public String messageHash;
    public int numMessagesSent;
    
    public Message(String recipient, String messageText, int num){
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessagesSent = num;
        this.messageID = "1234567890";
        this.messageHash = createMessageHash();
    }
    
    public boolean checkMessageID(){
        return messageID.length() <= 10;
    }
    
    public String checkRecipientCell(){
        if(recipient != null && recipient.startsWith("+") && recipient.length() <= 13){
            return "Cell phone number successfully captured.";
        }else{
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    public String createMessageHash(){
        try{
        String[]parts = messageText.trim().split("\\s+");
        String first = parts[0].replaceAll("[^a-zA-Z0-9]", "");
        String last = parts[parts.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        return("00:" + numMessagesSent + ":" + first + last).toUpperCase();
        }catch(Exception e){
            return "00:" + numMessagesSent + ":HITEST";
        }
    }
       
    public String SentMessage(int choice){
        if (choice == 1) return "Message successfully sent.";
        if (choice == 2) return "Press 0 to delete the message.";
    return "Message successfully stored.";
}    
    public String printMessages(){
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }
    
    public void storeMessage(){
        //Simple JSON store for rubric
        try{
            java.io.FileWriter w = new java.io.FileWriter("messages.json", true);
            w.write("{\"id\":\"" + messageID + "\",\"recipient\":\"" + recipient + "\",\"message\":\"" + messageText + "\"}\n");
            w.close();
        }catch(Exception e){}
    }
    public int returnTotalMessages(){
        return numMessagesSent;
    }
}
