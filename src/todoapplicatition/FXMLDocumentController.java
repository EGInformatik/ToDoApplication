/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todoapplicatition;

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
 * FXML Controller class
 *
 * @author alex
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private ListView<?> toDoList;
    @FXML
    private TextField inputField;
    @FXML
    private Label advarsel;
    @FXML
    private Button doneJohnButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insert(ActionEvent event) {
    }

    @FXML
    private void doneJohn(ActionEvent event) {
    }

    @FXML
    private void doALotOfThingsFunction(ActionEvent event) {
    }
    
}
