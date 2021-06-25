/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ReviewEmployeeController.empTable;
import Data.Employees;
import Data.PayrollSheet;
import View.ExcelConversion;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author maple
 */
public class EmployeeProfilesController implements Initializable {

    
    public Employees employee;
    ObservableList<PayrollSheet> empTable = FXCollections.observableArrayList();
    TableView table = new TableView();
    @FXML
    VBox vbox;
    @FXML
    TextField name,OT,VT,PTO,ST,hours, mins;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(!ExcelConversion.empList.isEmpty()){
            
        }
        else{
            for(Employees emp : View.ExcelConversion.empList)
                empTable.add(emp);

            javafx.scene.control.TableColumn<Employees, String> nameColumn = new javafx.scene.control.TableColumn<>("Employee Name");
            nameColumn.setPrefWidth(150);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            
            javafx.scene.control.TableColumn<Employees, Double> totalHoursColumn = new javafx.scene.control.TableColumn<>("Total Hours");
            totalHoursColumn.setPrefWidth(75);
            totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalHoursWorked"));
            
            javafx.scene.control.TableColumn<Employees, Double> VTColumn = new javafx.scene.control.TableColumn<>("Vac Hours");
            VTColumn.setPrefWidth(75);
            VTColumn.setCellValueFactory(new PropertyValueFactory<>("vacationTime"));

            javafx.scene.control.TableColumn<Employees, Double> OTColumn = new javafx.scene.control.TableColumn<>("OverTime Hours");
            OTColumn.setPrefWidth(75);
            OTColumn.setCellValueFactory(new PropertyValueFactory<>("overtime"));            

            javafx.scene.control.TableColumn<Employees, Double> STColumn = new javafx.scene.control.TableColumn<>("Sick Time Accrued");
            STColumn.setPrefWidth(75);
            STColumn.setCellValueFactory(new PropertyValueFactory<>("sickTimeAccrued"));

            javafx.scene.control.TableColumn<Employees, Double> PTOColumn = new javafx.scene.control.TableColumn<>("PTO");
            PTOColumn.setPrefWidth(75);
            PTOColumn.setCellValueFactory(new PropertyValueFactory<>("paidTimeOff"));

            //table = new TableView<>();
            //table.setItems(empTable);
            table.getColumns().addAll(nameColumn,totalHoursColumn,STColumn,OTColumn,PTOColumn,VTColumn);
            vbox.getChildren().add(table);
        }
    }
    
    public void addNewEmployee(){
        employee =  new Employees();
        double totalHours = (double)employee.timeToDecimal(Double.parseDouble(hours.getText()),Double.parseDouble(mins.getText()));
        employee.setVacationTime(Double.parseDouble(VT.getText()));
        employee.setOvertime(employee.calculateSickTime(0));
        employee.setSickTimeAccrued(employee.calculateSickTime(totalHours));
        System.out.println((double)employee.timeToDecimal(Integer.parseInt(hours.getText()),Integer.parseInt(mins.getText())));
        employee.setName(name.getText());
        employee.setPaidTimeOff(0);
        employee.setTotalHoursWorked(totalHours);
        table.getItems().add(employee);    
        
        name.clear();
        OT.clear();
        VT.clear();
        PTO.clear();
        ST.clear();
        hours.clear();
        mins.clear();
        
    }
    
    public void calculateSick()throws InvocationTargetException{
        Employees emp = new Employees();
        double totalHours= 0;
        
        if(!mins.getText().equals("")){
            if(hours.getText().equals(""))
                hours.setText("0");
            totalHours = (double)emp.timeToDecimal(Double.parseDouble(hours.getText()),Double.parseDouble(mins.getText()));
        }
       
        String sicktime = String.valueOf(emp.calculateSickTime(totalHours));
        ST.setText(sicktime);
    }
}
