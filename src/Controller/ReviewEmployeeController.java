/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Data.Employee;
import View.ExcelConversion;
import java.net.URL;
import java.util.ResourceBundle;
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

    static ObservableList<Employee> empTable = FXCollections.observableArrayList();
    TableView table;
    @FXML
    VBox vbox = new VBox();
    
            /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
            for(Employee emp : View.ExcelConversion.empList)
            empTable.add(emp);
        
        javafx.scene.control.TableColumn<Employee, Integer> idColumn = new javafx.scene.control.TableColumn<>("ID");
        idColumn.setPrefWidth(75);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        
        javafx.scene.control.TableColumn<Employee, Double> regHoursColumn = new javafx.scene.control.TableColumn<>("Regular Hours");
        regHoursColumn.setPrefWidth(150);
        regHoursColumn.setCellValueFactory(new PropertyValueFactory<>("regHours"));
        
        javafx.scene.control.TableColumn<Employee, String> coCodeColumn = new javafx.scene.control.TableColumn<>("Company Code");
        coCodeColumn.setPrefWidth(75);
        coCodeColumn.setCellValueFactory(new PropertyValueFactory<>("coCode"));
        
        javafx.scene.control.TableColumn<Employee, Double> OthoursColumn = new javafx.scene.control.TableColumn<>("OverTime");
        OthoursColumn.setPrefWidth(75);
        OthoursColumn.setCellValueFactory(new PropertyValueFactory<>("OtHours"));
        
        javafx.scene.control.TableColumn<Employee, Double> PayRateColumn = new javafx.scene.control.TableColumn<>("Pay Rate");
        PayRateColumn.setPrefWidth(75);
        PayRateColumn.setCellValueFactory(new PropertyValueFactory<>("PayRate"));
        
        javafx.scene.control.TableColumn<Employee, String> earingsCodeColumn = new javafx.scene.control.TableColumn<>("Earnings Code");
        earingsCodeColumn.setPrefWidth(75);
        earingsCodeColumn.setCellValueFactory(new PropertyValueFactory<>("earningsCode"));
        
        table = new TableView<>();
        table.setItems(empTable);
        table.getColumns().addAll(idColumn,regHoursColumn,coCodeColumn,OthoursColumn,PayRateColumn,earingsCodeColumn);
        vbox.getChildren().add(table);    
        
        HomeController.primaryStage2.setOnCloseRequest(event ->{
            empTable.clear();
            ExcelConversion.empList.clear();
        });
    }    
    
    
}