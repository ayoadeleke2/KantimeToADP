/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Data.Employees;
import Data.PayrollSheet;
import Data.PayrollSheetTableViewFactory;
import View.ExcelConversion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javax.swing.table.TableColumn;

/**
 * FXML Controller class
 *
 * @author maple
 */
public class ReviewEmployeeController implements Initializable {

    ObservableList<PayrollSheet> empTable = FXCollections.observableArrayList();
    TableView table;
    @FXML
    VBox vbox = new VBox();
    
            /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
        for(PayrollSheet emp : View.ExcelConversion.empSheetList)
            empTable.add(emp);
        
        table = new TableView<>();
        table.setItems(empTable);
        table.getColumns().addAll(PayrollSheetTableViewFactory.getColumns());
        vbox.getChildren().add(table);    
        
        HomeController.primaryStage2.setOnCloseRequest(event ->{
            empTable.clear();
            table.getItems().clear();
            ExcelConversion.empSheetList.clear();
            try {
                HomeController.outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ReviewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    public void download() throws IOException{
        ExcelConversion.stage.close();
        HomeController.primaryStage2.close();
    }
    
}
