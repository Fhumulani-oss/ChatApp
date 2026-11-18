/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    public String flag;
    
    //PART 3 - 1. THE 5 ARRAYS
    public static List<Message> sentMessages = new ArrayList<>();
    public static List<Message> disregardedMessages = new ArrayList<>();
    public static List<Message> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();
    
    public Message(String recipient, String messageText, int num, String flag){
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessagesSent = num;
        this.flag = flag;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
        
    public Message(String recipient, String messageText, int num){
        this(recipient, messageText, num, "Sent");
    }
    
    private String generateMessageID(){
        Random rand = new Random();        
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }
    public boolean checkMessageID(){ return messageID.length() == 10; }
    
    public String checkRecipientCell(){
        if(recipient != null && recipient.startsWith("+") && recipient.length() <= 13){
            return "Cell phone number successfully captured.";
        }
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    public String createMessageHash(){
        try{
        String[]parts = messageText.trim().split("\\s+");
        String first = parts[0].replaceAll("[^a-zA-Z0-9]", "");
        String last = parts[parts.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        return(messageID.substring(0,2) + ":" + numMessagesSent + ":" + first + last).toUpperCase();
        }catch(Exception e){
            return messageID.substring(0,2) + ":" + numMessagesSent + ":HITEST";
        }
    }
      
    // Send / Disregard / Store - also fills thr arrays
    public String SentMessage(int choice){       
        if (choice == 1){
            flag = "Sent";
            sentMessages.add(this);
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
            return "Message successfully sent.";
        }
        if (choice == 2){
            flag = "Disregard";
            disregardedMessages.add(this);
            return "Press 0 to delete the message.";
        }
        flag = "Stored";
        storedMessages.add(this);
        messageIDs.add(messageID);
        messageHashes.add(messageHash);
        storeMessage(); 
        return "Message successfully stored.";
    }
    
    // JSON file - proper format
    public void storeMessage(){
        try{
            StringBuilder json = new StringBuilder("[\n");
            for(int i = 0; i < storedMessages.size(); i++){
                Message m = storedMessages.get(i);
                json.append("  {\"messageID\":\"").append(m.messageID)
                    .append("\", \"messageHash\":\"").append(m.messageHash)
                    .append("\", \"recipient\":\"").append(m.recipient)
                    .append("\", \"message\":\"").append(m.messageText.replace("\"", "'"))
                    .append("\", \"flag\":\"").append(m.flag).append("\"}");
                if (i < storedMessages.size()-1) json.append(",");
                json.append("\n");
            }
            json.append("]");
            FileWriter w = new FileWriter("messages.json");
            w.write(json.toString());
            w.close();
        }catch(Exception e){}
    }
    
    public String printMessages(){
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient" + recipient +"\nMessage: " + messageText;
    }
    
    // ===== PART 3 - 2. MENU 4 METHODS =====
    
    // a. Display sender and recipient of all stored
    public static String displayStoredSenderRecipient(){
        if (storedMessages.isEmpty()) return "No stored messages.";
        StringBuilder sb = new StringBuilder();
        for (Message m : storedMessages){
            sb.append("Sender: You | Recipient: ").append(m.recipient).append(" | Message: ").append(m.messageText).append("\n");
        }
        return sb.toString();
    }
    
    // b. Display longest stored message
    public static String displayLongestStoredMessage(){
        if (storedMessages.isEmpty()) return "No stored messages.";
        Message longest = storedMessages.get(0);
        for (Message m: storedMessages) {
            if (m.messageText.length() > longest.messageText.length()) longest = m;
        }
        return longest.messageText;
    }
    
    //c. Search for message ID
    public static String searchByID(String id) {
        for(Message m: sentMessages) if (m.messageID.equals(id)) return "Recipient: " + m.recipient + " | Message: " + m.messageText;
        for(Message m: storedMessages) if (m.messageID.equals(id))return "Recipient: " + m.recipient + " | Message: " + m.messageText;
        for(Message m: sentMessages) if (m.recipient.equals(id)) return "Recipient: " + m.recipient + " | Message: " + m.messageText;
        return "Message ID not found.";
   }
    
    //d. Search all messages for a recipient
    public static String searchByRecipient(String recipient){
        StringBuilder sb = new StringBuilder();
        for(Message m: sentMessages) if (m.recipient.equals(recipient))sb.append(m.messageText).append("\n");
        for(Message m: storedMessages) if (m.recipient.equals(recipient))sb.append(m.messageText).append("\n");
        if(sb.length()==0) return "No messages for this recipient.";
        return sb.toString().trim();
    }
    
    //e. Delete using hash
    public static String deleteByHash(String hash){
        for(int i=0; i<storedMessages.size(); i++){
            if(storedMessages.get(i).messageHash.equalsIgnoreCase(hash)){
                String msg = storedMessages.get(i).messageText;
                storedMessages.remove(i);
                return "Message: \"" + msg + "\" successfully deleted.";           
            }
        }
        for(int i=0; i < sentMessages.size(); i++){
            if(sentMessages.get(i).messageHash.equalsIgnoreCase(hash)){
                String msg = sentMessages.get(i).messageText;
                sentMessages.remove(i);
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }
    
    //f. Display report
    public static String displayReport(){
        StringBuilder sb = new StringBuilder("=== FULL REPORT ===\n");
        sb.append("--- SENT ---\n");
        for(Message m: sentMessages) sb.append("Hash: ").append(m.messageHash).append(" | Recipient: ").append(m.recipient).append(" | Message: ").append(m.messageText).append("\n");
        sb.append("--- STORED ---\n");
        for(Message m: storedMessages) sb.append("Hash: ").append(m.messageHash).append(" | Recipient: ").append(m.recipient).append(" | Message: ").append(m.messageText).append("\n");
        sb.append("--- DISREGARDED ---\n");
        for(Message m: disregardedMessages) sb.append("Hash: ").append(m.messageHash).append(" | Recipient: ").append(m.recipient).append(" | Message: ").append(m.messageText).append("\n");
        return sb.toString();
    }
    
    //PART 3 - 3. TEST DATA - Populates arrays
    public static void populateTestData(){
        sentMessages.clear(); storedMessages.clear(); disregardedMessages.clear(); messageIDs.clear(); messageHashes.clear();
        Message m1 = new Message("+27834557896", "Did you get the cake?", 1, "Sent");
        m1.SentMessage(1);
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 2, "Stored");
        m2.SentMessage(3);
        Message m3 = new Message("+27834484567", "Yohooo, I am at your gate.", 3, "Disregard");
        m3.SentMessage(2);
        Message m4 = new Message("0838884567", "It is dinner time !", 4, "Sent");
        m4.SentMessage(1);
        Message m5 = new Message("+27838884567", "Ok, I am leaving without you.", 5, "Stored");
        m5.SentMessage(3);
    }
}
