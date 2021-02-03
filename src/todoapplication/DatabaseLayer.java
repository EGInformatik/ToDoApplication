/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todoapplication;

import java.nio.file.Paths;
import java.sql.Connection;
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
            +"\\src\\todoapplication\\database.sqlite");
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public ObservableList todoItems() {
        this.connect();
        ObservableList todoItems = FXCollections.observableArrayList();
        String query = "SELECT * FROM TodoItem";
        try {
            ResultSet result = connection
                    .createStatement()
                    .executeQuery(query);
            while(result.next()) {
                todoItems.add(result.getString("TodoString"));
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
    
}
