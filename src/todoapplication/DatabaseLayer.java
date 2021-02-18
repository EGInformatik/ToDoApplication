/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todoapplication;

import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alex
 */
public class DatabaseLayer {
    
    Connection connection = null;
    
    void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+Paths
                    .get("")
                    .toAbsolutePath()
                    .toString()
            +"\\src\\todoapplication\\example.sqlite");
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public ObservableList todoItems(String name) {
        this.connect();
        ObservableList todoItems = FXCollections.observableArrayList();
        String query =  "SELECT * FROM TodoItem " +
                        "JOIN User ON " +
                        "TodoItem.UserId = User.UserId " +
                        "WHERE User.Name = '"+name+"'";
        try {
            ResultSet result = connection
                    .createStatement()
                    .executeQuery(query);
            while(result.next()) {
                Todo item = new Todo();
                item.todoString = result.getString("TodoString");
                item.username = result.getString("Name");
                item.timestamp = result.getString("Timestamp");
                int isDone = result.getInt("isDone");
                if(isDone==0) {
                    item.isDone = false;
                } else if(isDone==1) {
                    item.isDone = true;
                }
                todoItems.add(item);
            }
        } catch(SQLException ex) {
           
        }
        return todoItems;
    }

    void writeString(String todoItem) {
        String query = "INSERT INTO TodoItem (TodoString) VALUES ('"+todoItem+"')";
        try {
           PreparedStatement ps = connection.prepareStatement(query);
           ps.executeUpdate();
        } catch (SQLException ex) {
            
        }
    }
    
    String findSalt(String username) {
        String query = "SELECT (PasswordSalt) FROM USER WHERE Name='"+username+"'";
        String salt = "";
        try {
            ResultSet result = connection
                    .createStatement()
                    .executeQuery(query);
            while(result.next()) {
                
                salt = result.getString("PasswordSalt");
            }
        } catch(SQLException ex) {
           
        }
        return salt;
    }

    String login(String username, String password) throws NoSuchAlgorithmException {
        this.connect();
        
        
        String salted = password+this.findSalt(username);
        
        
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(salted.getBytes());
        String hexString = "";
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) 
            {
                hexString = hexString + '0';
            } else {
                hexString = hexString + '1';
            }
            
        }

        String query = "SELECT * FROM USER WHERE Name='"+username+"' AND PASSWORDHASH='"+hexString+"'";
        String name = "";
        try {
            ResultSet result = connection
                    .createStatement()
                    .executeQuery(query);
            while(result.next()) {
                
                name = result.getString("Name");
            }
        } catch(SQLException ex) {
           
        }
        return name;
        
    }

    void register(String username, String password) throws NoSuchAlgorithmException {
        this.connect();
        String salt = "blabla";
        String salted = password+salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(salted.getBytes());
        String hexString = "";
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) 
            {
                hexString = hexString + '0';
            } else {
                hexString = hexString + '1';
            }
            
        }
        
        String query = "INSERT INTO USER (Name, PasswordHash, PasswordSalt) VALUES ('"+username+"', '"+hexString+"', '"+salt+"')";
        String name = "";
        try {
            ResultSet result = connection
                    .createStatement()
                    .executeQuery(query);
            
        } catch(SQLException ex) {
           
        }
    }

    
}
