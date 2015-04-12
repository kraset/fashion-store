/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication4;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author anc
 */
public class FXMLDocumentController implements Initializable {
    
    //A pointless comment.
    @FXML private Label label;
    @FXML private Button btnConnecDisconnect;
    @FXML private Button btnShowEmployees;
    
    DbHandler databaseHandler = new DbHandler();
    
    @FXML private TableView<Employee> tableView;
    @FXML private TableColumn<Employee, String> c1;
    @FXML private TableColumn<Employee, String> c2;
    @FXML private TableColumn<Employee, String> c3;    
    
    @FXML
    private void handleButtonConnect(ActionEvent event) {
        if (btnConnecDisconnect.getText().equals("Connect")){
            label.setText("Connected to database!");
            if ( databaseHandler.connect()){
                System.out.println("Connected to database!");
                btnConnecDisconnect.setText("Disconnect");
            }
        }
        else{
            label.setText("Disconnected!");
            databaseHandler.disconnect();
            System.out.println("Disconnected!");
            btnConnecDisconnect.setText("Connect");
        }
    }
    
    @FXML
    private void handleButtonQuit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleShowEmployees(ActionEvent event) {
        ArrayList<Employee> employees = databaseHandler.getEmployees();
        if (!employees.isEmpty()){
            tableView.getItems().add(employees.get(0));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setText("Name");
        c2.setText("Salary");
        //c1.setCellValueFactory(new PropertyValueFactory<>("c1"));
        //c2.setCellValueFactory(new PropertyValueFactory<>("c2"));
        //c3.setCellValueFactory(new PropertyValueFactory<>("c3"));        
    }    
    
}
