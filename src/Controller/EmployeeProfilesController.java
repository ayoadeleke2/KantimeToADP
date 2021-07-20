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
import Data.PayrollSheetTableViewFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author maple
 */
public class EmployeeProfilesController implements Initializable {

    public File ADPfile;
    public Employees employee;
    ArrayList<PayrollSheet> IDlist = new ArrayList<>();
    ObservableList<PayrollSheet> empTable = FXCollections.observableArrayList();
    ObservableList<Employees> li = FXCollections.observableArrayList();
    TableView table;
    @FXML
    VBox vbox,vbox2;
    @FXML
    HBox hbox;
    @FXML
    TextField name,OT,VT,PTO,ST,hours, mins;
    boolean updated= false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PayrollSheet PS = new PayrollSheet();
        
        TableView table2 = new TableView();
        table = new TableView();
        if(ExcelConversion.empList.isEmpty()){
            
        }
        else{ 
            table.getItems().addAll(ExcelConversion.empList);
        }
        table2.getItems().addAll(getEmployeeIDs());
        table2.getColumns().addAll(PayrollSheetTableViewFactory.getColumns().subList(0, 1));
        table.getColumns().addAll(EmployeeTableViewFactory.getColumns());
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vbox.getChildren().add(table); 
        vbox2.getChildren().add(table2);
        BackgroundFill fills = new BackgroundFill(new Color(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(fills);
        table.backgroundProperty().setValue(bg);
            
        table.setOnMouseClicked((MouseEvent event) -> {
                            
                ObservableList<Employees> list = table.getSelectionModel().getSelectedCells();
                Employees e = new Employees();
                Employees selecteditem = (Employees)table.getSelectionModel().getSelectedItem();
                int SelectedItemIndex = table.getSelectionModel().getSelectedIndex();
                //String index = (Employees)table.getSelectionModel().getTableView().getVisibleLeafIndex(OTColumn);
                TablePosition tp, tp2;
                tp = table.getFocusModel().getFocusedCell();
                tp2 = table2.getFocusModel().getFocusedCell();
                switch(tp.getColumn()){
                    case 6:
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
                    case 0:
                        if(alert().equals(ButtonType.OK)){
                            selecteditem.setIDs(Integer.valueOf(table2.getSelectionModel().getTableView().getVisibleLeafColumn(0).getCellData(tp2.getRow()).toString()));
                            e.setEarningsCode(selecteditem.getEarningsCode());
                            e.setID(selecteditem.getID());
                            e.setBatchID(selecteditem.getBatchID());
                            e.setCoCode(selecteditem.getCoCode());
                            e.setName(selecteditem.getName());
                            e.setOvertime(selecteditem.getOvertime());
                            e.setPaidTimeOff(selecteditem.getPaidTimeOff());
                            e.setSickTimeAccrued(selecteditem.getSickTimeAccrued());
                            e.setTotalHoursWorked(selecteditem.getTotalHoursWorked());
                            e.setVacationTime(selecteditem.getVacationTime());
                            e.setIDs((Integer)table2.getSelectionModel().getTableView().getVisibleLeafColumn(0).getCellData(tp2.getRow()));
                            System.out.println(table.getSelectionModel().getTableView().getVisibleLeafColumn(tp.getColumn()).getCellData(tp.getRow()));
                            System.out.println(table2.getSelectionModel().getTableView().getVisibleLeafColumn(0).getCellData(tp2.getRow()));
                            ExcelConversion.empList.remove(SelectedItemIndex);
                            ExcelConversion.empList.add(SelectedItemIndex,e);
                            table.getItems().remove(SelectedItemIndex);
                            table.getItems().add(SelectedItemIndex, e);
                            table.getSelectionModel().clearSelection();
                            table.getSelectionModel().select(SelectedItemIndex);
                            updated=true;
                            break;
                        }
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
        ExcelConversion.empList.add(employee);
        
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

        if(deletedEmployees.size() > 1){
            table.getItems().removeAll(deletedEmployees);
            empList.removeAll(li);
            updated=true;
        }else{
            Employees selecteditem = (Employees)table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selecteditem);
            ExcelConversion.empList.remove(selecteditem);
            updated=true;}
        table.getSelectionModel().clearSelection();    
    }
    
    
    
    public void calculateSick()throws InvocationTargetException{
        Employees emp = new Employees();
        double totalHours;
        
         if(hours.getText().isEmpty()||mins.getText().isEmpty())
                totalHours = 0;
         else
             totalHours = (double)emp.timeToDecimal(Integer.parseInt(hours.getText()),Integer.parseInt(mins.getText()));

        String sicktime = String.valueOf(emp.calculateSickTime(totalHours));
        ST.setText(sicktime);
    }
    
    
    public void writeToJson() throws IOException, InvalidFormatException{
        
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
            empList.addAll(table.getSelectionModel().getTableView().getItems());
            calculateTimefromADPSheet();
            table.getItems().clear();
            table.getItems().addAll(ExcelConversion.empList);
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
        
        table.getItems().addAll(li);
        ExcelConversion.empList.addAll(li);
        
        table.getFocusModel().getFocusedCell().getTableView().getSelectionModel().clearSelection();
        table.getFocusModel().getFocusedCell().getTableView().getSelectionModel().selectRange(table.getItems().size()-li.size(), table.getItems().size());
        System.out.println((table.getItems().size()-li.size())+" "+(table.getItems().size()-1));
        li.clear();
    }
    
    public ArrayList<PayrollSheet> getEmployeeIDs(){
        
        String home = System.getProperty("user.home");
        File filepath = new File(home+"/Downloads/");
        File[] fileList = filepath.listFiles();
        
        int rows, col;
        PayrollSheet newID;
        Employees e = new Employees();
        ArrayList<Employees> affectedEmps = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList();
        XSSFSheet sheet = null;
        XSSFWorkbook book = null;
        
        
        if(fileList!=null){
            for(File f: fileList){
                if(f.getName().equals("ADP.xlsx")){ 
                    try {
                        ADPfile = f;
                        book = new XSSFWorkbook(f);
                    } catch (IOException ex) {
                        Logger.getLogger(PayrollSheet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(PayrollSheet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        sheet = book.getSheetAt(0);
        rows = sheet.getLastRowNum();
        col = sheet.getRow(0).getLastCellNum();
        int flag =0;
                
                for(int i=1; i <= rows; i++){
                XSSFRow row = sheet.getRow(i);
                    for(int j=0; j<col; j++){
                    XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        if(i>1 && j==4){
                            int value = (int)cell.getNumericCellValue();
                            if(temp.contains(value)==false){
                                newID = new PayrollSheet();
                                newID.setID(value);
                                IDlist.add(newID);
                                temp.add(value);}
                        }
                    }
                    affectedEmps.add(e);
                }
        return IDlist;
    }
    
    
    public void calculateTimefromADPSheet() throws IOException, InvalidFormatException{
        Employees e = new Employees();
        XSSFWorkbook book = new XSSFWorkbook(ADPfile);
        XSSFSheet sheet;
        int rows, col;
        
        
        sheet = book.getSheetAt(0);
        rows = sheet.getLastRowNum();
        col = sheet.getRow(0).getLastCellNum();
        int flag =0;
                
        for(int i=1; i <= rows; i++){
        XSSFRow row = sheet.getRow(i);
            for(int j=0; j<col; j++){
            XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if(i>1 && j==4){
                    int val = (int)cell.getNumericCellValue();
                    for(int k=0; k < ExcelConversion.empList.size();k++){
                        if(ExcelConversion.empList.get(k).getIDs()==val){
                            e = ExcelConversion.empList.get(k);
                        }
                    }
                }if(i>1 && j==5){
                    String earningsCode = cell.getStringCellValue();
                    switch(earningsCode){
                        case "REG":
                            flag =0;
                            break;
                        case "OVT":
                            flag =1;
                            break;
                        case "SYC":
                            flag =2;
                            break;
                    }
                }if(i>1 && j==6){
                    double val = cell.getNumericCellValue();
                    switch(flag){
                        case 0:
                            System.out.println(e.getTotalHoursWorked()+" VALUE");
                            e.setTotalHoursWorked(e.getTotalHoursWorked()+val);
                            break;
                        case 1:
                            e.setOvertime(e.getOvertime()+val);
                            break;
                        case 2:
                            e.setSickTimeAccrued(e.getSickTimeAccrued()+val);
                            break;
                    }
                }
            }
        }
        int i=0;
        for(Employees emp: empList){
            emp.calculateSickTime(emp.getTotalHoursWorked());
        }
    }
    
    public ButtonType alert(){
        Employees e = (Employees)table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Confirm Change to "+e.getName()+"'s ID");
        alert.showAndWait();
        return alert.getResult();
    }
}
