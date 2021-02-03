/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todoapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author alex
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ListView<String> toDoList;
    @FXML
    private TextField inputField;
    @FXML
    private Label advarsel;
    @FXML
    private Button doneJohnButton;

    DatabaseLayer db = new DatabaseLayer();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db.todoItems();
    }    

    @FXML
    private void insert(ActionEvent event) {
        String todoItem = this.inputField.getText();
        if (todoItem.length()>0) {
            toDoList.getItems().add(todoItem);
            this.advarsel.setText("");
        } else {
            this.advarsel.setText("Venligst skriv noget");
        }
        
    }

    @FXML
    private void doneJohn(ActionEvent event) {
       String newTodoItem = this.toDoList.getSelectionModel().getSelectedItem() + " [DONE JOHN]";
       int indexToRemove = this.toDoList.getSelectionModel().getSelectedIndex();
       this.toDoList.getItems().remove(indexToRemove);
       this.toDoList.getItems().add(indexToRemove, newTodoItem);
    }

    @FXML
    private void doALotOfThingsFunction(ActionEvent event) {
        String todoItem = this.inputField.getText();
        if (todoItem.length()>0) {
            for(int i = 0; i<5; i++) {
                toDoList.getItems().add(todoItem);
            }
            this.advarsel.setText("");
        } else {
            this.advarsel.setText("Venligst skriv noget");
        }
    }
    
}
