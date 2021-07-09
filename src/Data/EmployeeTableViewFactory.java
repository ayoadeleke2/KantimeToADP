/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author maple
 */
public class EmployeeTableViewFactory {
    public static ObservableList<Employees> getColumns(){
  
        ObservableList list = FXCollections.observableArrayList();
        TableColumn<?,?> nameColumn = new TableColumn<>("Employee Name");
            nameColumn.setPrefWidth(150);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            //nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            nameColumn.setId("0");
            
            TableColumn<Employees,String> totalHoursColumn = new TableColumn<>("Total Hours");
            totalHoursColumn.setPrefWidth(75);
            totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalHoursWorked"));
            totalHoursColumn.setId("1");
            
            TableColumn<Employees,?> VTColumn = new TableColumn<>("Vac Hours");
            VTColumn.setPrefWidth(75);
            VTColumn.setCellValueFactory(new PropertyValueFactory<>("vacationTime"));
            VTColumn.setId("5");

            TableColumn OTColumn = new TableColumn<>("OverTime Hours");
            OTColumn.setPrefWidth(75);
            OTColumn.setCellValueFactory(new PropertyValueFactory<>("overtime")); 
            OTColumn.setId("3");

            TableColumn STColumn = new TableColumn<>("Sick Time Accrued");
            STColumn.setPrefWidth(75);
            STColumn.setCellValueFactory(new PropertyValueFactory<>("sickTimeAccrued"));
            STColumn.setId("2");

            TableColumn PTOColumn = new TableColumn<>("PTO");
            PTOColumn.setPrefWidth(75);
            PTOColumn.setCellValueFactory(new PropertyValueFactory<>("paidTimeOff"));
            PTOColumn.setId("4");
            
            TableColumn IDColumn = new TableColumn<>("ID");
            PTOColumn.setPrefWidth(75);
            PTOColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            PTOColumn.setId("5");
            
           list.addAll(nameColumn,totalHoursColumn,STColumn,OTColumn,PTOColumn,VTColumn,IDColumn);
           return list;
    }
}
