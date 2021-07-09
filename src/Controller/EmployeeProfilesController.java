/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Data.Employees;
import Data.PayrollSheet;
import View.ExcelConversion;
import static View.ExcelConversion.empList;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import javax.management.Notification;
import static javax.management.Query.value;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Data.EmployeeTableViewFactory;

/**
 * FXML Controller class
 *
 * @author maple
 */
public class EmployeeProfilesController implements Initializable {

    
    public Employees employee;
    ObservableList<PayrollSheet> empTable = FXCollections.observableArrayList();
    ObservableList<Employees> li = FXCollections.observableArrayList();
    TableView table;
    @FXML
    VBox vbox;
    @FXML
    TextField name,OT,VT,PTO,ST,hours, mins;
    boolean updated= false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        table = new TableView();
        if(ExcelConversion.empList.isEmpty()){
            
        }
        else{ 
            table.getItems().addAll(ExcelConversion.empList);
        }
            //table.setEditable(true);
            table.getColumns().addAll(EmployeeTableViewFactory.getColumns());
            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            vbox.getChildren().add(table);   
            BackgroundFill fills = new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY);
            Background bg = new Background(fills);
            table.backgroundProperty().setValue(bg);
            //table.setStyle("-fx-selection-bar: red; -fx-selection-bar-non-focused: salmon;");
            
        
        table.setOnMouseClicked((MouseEvent event) -> {
                            
                ObservableList<Employees> list = table.getSelectionModel().getSelectedCells();
                Employees e = new Employees();
                Employees selecteditem = /*(Employees)table.getSelectionModel().getSelectedItem();*/
                (Employees)table.getSelectionModel().getTableView().getSelectionModel().getSelectedItem();
                //String index = (Employees)table.getSelectionModel().getTableView().getVisibleLeafIndex(OTColumn);
                TablePosition tp;
                tp = table.getFocusModel().getFocusedCell();
                switch(tp.getColumn()){
                    case 0:
                        System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(tp.getColumn()).getCellData(table.getSelectionModel().getSelectedIndex()));
                        break;
                    case 1:
                        System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(tp.getColumn()).getCellData(tp.getRow()));
                        break;
                    case 2:
                        System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(tp.getColumn()).getCellData(tp.getRow()));
                        break;
                    case 3:
                        System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(tp.getColumn()).getCellData(tp.getRow()));
                        break;
                    case 4:
                        System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(tp.getColumn()).getCellData(tp.getRow()));
                        break; 
                    case 5:
                        System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(tp.getColumn()).getCellData(tp.getRow()));
                        break;
                    case 6:
                        //PayrollSheet.getEmployeeID(book, sheet);
                        System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(tp.getColumn()).getCellData(tp.getRow()));
                        break;
                }
                    if(event != table.getSelectionModel().getTableView().getVisibleLeafColumn(1).getCellFactory()){
                        //System.out.println(selecteditem.getName());
                        //System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(1).getCellData(table.getSelectionModel().getSelectedIndex()));
                        //System.out.println(index);
                    }
        });
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
        employee.setID(0);
        table.getItems().add(employee);   
        
        name.clear();
        VT.clear();
        PTO.clear();
        ST.clear();
        hours.clear();
        mins.clear();   
        updated=true;
    }
    
    
    
    public void removeEmployee(){
        ObservableList<Employees> deletedEmployees;
        
        deletedEmployees = table.getSelectionModel().getSelectedItems();
        li.addAll(deletedEmployees);
        for(Employees emp: deletedEmployees)
            System.out.println(deletedEmployees.listIterator().hasNext());
        if(deletedEmployees.listIterator().hasNext()){ 
            table.getItems().removeAll(deletedEmployees);
            empList.remove(deletedEmployees);
            updated=true;
        }else{
            Employees selecteditem = (Employees)table.getSelectionModel().getSelectedItem();
        
            table.getItems().remove(selecteditem);
            ExcelConversion.empList.remove(selecteditem);
            updated=true;}        
    }    
    
    
    
    public void calculateSick()throws InvocationTargetException{
        Employees emp = new Employees();
        double totalHours;
        
         if(hours.getText().isEmpty()||mins.getText().isEmpty()){
                totalHours = 0;
         }else
             totalHours = (double)emp.timeToDecimal(Integer.parseInt(hours.getText()),Integer.parseInt(mins.getText()));

        String sicktime = String.valueOf(emp.calculateSickTime(totalHours));
        ST.setText(sicktime);
    }
    
    
    public void writeToJson(){
        
        String mess = "Data Sheet Updated and Saved";
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("Employees.json");
        NotificationType notify = NotificationType.SUCCESS;
        Image img = new Image(getClass().getResourceAsStream("/img/Homewatch-Caregivers-1080x521.jpg"));
        TrayNotification note = new TrayNotification("Employee Updated",mess,notify);
        note.setImage(img);
        note.setAnimationType(AnimationType.FADE);
        
        if(updated==true){
            updated=false;
            empList.clear();
            ExcelConversion.empList.addAll(table.getSelectionModel().getTableView().getItems());
            try {
                mapper.writeValue(file, ExcelConversion.empList);
                note.showAndDismiss(Duration.seconds(3));
            } catch (IOException ex) {
                Logger.getLogger(EmployeeProfilesController.class.getName()).log(Level.SEVERE, null, ex);}
        }else
            System.out.println("not updated");        
    }
    
    public void recoverDeleted(){
        //TableView<Employees> l = new TableView<>();
        String blue = "-fx-selection-bar: blue; -fx-selection-bar-non-focused: salmon;";
        String red = "-fx-selection-bar: red; -fx-selection-bar-non-focused: salmon;";
        TableCell<Employees,Employees> cell = new TableCell<>();
        cell.updateTableView(table);
        TableView<Employees> dl = new TableView<>(li);
        
        table.getItems().addAll(dl.getItems());
        Iterator<Employees> tableIterator = table.getItems().iterator();
        
        
        for(int j=0;tableIterator.hasNext();j++,tableIterator.next()){
            table.getFocusModel().focus(j);
            Employees focusedEmp = (Employees)table.getFocusModel().getFocusedItem();
            Iterator<Employees> dlIterator = dl.getItems().listIterator(0);
            for(int i=0;dlIterator.hasNext();dlIterator.next(),i++){
                //if(focusedEmp.getName().equals(dl.getItems().get(i).getName()))
                    //table.getFocusModel().getFocusedCell().getTableView().getSelectionModel().selectIndices(table.getItems().size()-dl.getItems().size(), table.getItems().size()-1);
            }        
        }
        table.getFocusModel().getFocusedCell().getTableView().getSelectionModel().clearSelection();
        table.getFocusModel().getFocusedCell().getTableView().getSelectionModel().selectRange(table.getItems().size()-dl.getItems().size(), table.getItems().size());
        System.out.println((table.getItems().size()-dl.getItems().size())+" "+(table.getItems().size()-1));
        //table.setStyle(red);
        li.clear();
    }
}
