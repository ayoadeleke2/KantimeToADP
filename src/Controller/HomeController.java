/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ExcelConversion;
import Data.PayrollSheet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author maple
 */
public class HomeController implements Initializable {
    //static String path = ".\\Excelsheet\\ADP.xlsx";
    ArrayList<PayrollSheet> empSheetList = new ArrayList<>();
    XSSFWorkbook kantimeWkbook;
    XSSFWorkbook wkbook = new XSSFWorkbook();
    XSSFSheet sheet = wkbook.createSheet("ADP");
    public static FileOutputStream outputStream;

    public static Stage primaryStage2;
    public static Scene reviewScene;
    @FXML
    TextField sickTime, filePath;
    @FXML
    DatePicker startDate, endDate;
    @FXML
    Button enter;
    @FXML
    CheckBox stCheck;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void convertKantimeSheet(){
        PayrollSheet emp;
        int rows, col;
        XSSFSheet sheet1;
        //this is the same as the line above but allows you to pick the sheet by index number
        sheet1 = kantimeWkbook.getSheetAt(0);
        //XSSFSheet sheet = wkbook.getSheet("Sheet1");
        rows = sheet1.getLastRowNum();
        //retrieve the number of columns in a given row
        col = sheet1.getRow(0).getLastCellNum();
        for(int i=1; i <= rows; i++){
            emp = new PayrollSheet();
            //gets the first row of the excel sheet
            XSSFRow row = sheet1.getRow(i);
            for(int j=0; j<col; j++){
                //this gets each cell from the row
                XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //this gets the type of data in cell (int, string, boolean, etc)
                switch(cell.getCellType()){
                    case STRING:
                        if(i>0 && j==0)
                            emp.setCoCode(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        break;
                    case NUMERIC:
                        if(i>0 && j==1){
                            emp.setBatchID((int)cell.getNumericCellValue());
                        }
                        if(i>0 && j==2){
                            emp.setID((int)cell.getNumericCellValue());
                            }
                        if(i>0 && j==5)
                            emp.setOtHours(cell.getNumericCellValue());
                            //System.out.println((int)cell.getNumericCellValue());
                        if(i>0 && j==11)
                            emp.setPayRate(cell.getNumericCellValue());
                        if(i>0 && j==4)
                            emp.setRegHours(cell.getNumericCellValue());
                        break;   
                    case BLANK:
                        break;
                }
            }
            ExcelConversion.empSheetList.add(emp);
        }
    }
    
    public void writeIntoSheet() throws FileNotFoundException, IOException{
        
    convertKantimeSheet();
    Object requiredInfo[][] = {{"##GENERIC## V1.0","","","","","","","","","",""},
                                        {"IID","Pay Frequency","Pay Period Start","Pay Period End",
                                        "Employee Time Clock Id", "Earnings Code", "Pay Hours",
                                        "Dollars", "Separate Check", "Worked Department", "Rate Code"}
                                      };
                                      
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/ADP.xlsx");
        outputStream = new FileOutputStream(file);
        for(int row=0; row<ExcelConversion.empSheetList.size()+requiredInfo.length;row++){ 
            XSSFRow newRow = sheet.createRow(row);
            for(int col=0; col<11; col++){  
                XSSFCell newCell = newRow.createCell(col);
                if(row==0){
                    Object titleNames = requiredInfo[row][col];
                    newCell.setCellValue((String)titleNames);}                   
                if(row==1){
                    Object titleNames = requiredInfo[row][col];
                    newCell.setCellValue((String)titleNames);}     
                if(row>1){
                    switch(col) {
                            case 0: newCell.setCellValue("KHPBM");
                                break;
                            case 1: newCell.setCellValue("B");
                                break;
                            case 2: newCell.setCellValue((String)(startDate.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"))));
                                break;
                            case 3:  newCell.setCellValue((String)(endDate.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"))));
                                break;    
                            case 4: newCell.setCellValue((Integer)ExcelConversion.empSheetList.get(row-requiredInfo.length).getID());
                                break;
                            case 5: if(ExcelConversion.empSheetList.get(row-requiredInfo.length).getPayRate()==Double.valueOf(sickTime.getText())){
                                        newCell.setCellValue((String)"SPY");
                                        ExcelConversion.empSheetList.get(row-requiredInfo.length).setEarningsCode("SPY");
                            /*          ExcelConversion.empList.get(row-2).getRegHours()/*
                                        write code to change value back to normal sick time pay rate
                                        */
                                    }
                                    else if(ExcelConversion.empSheetList.get(row-2).getOtHours()==0){
                                        newCell.setCellValue((String)"REG");
                                        ExcelConversion.empSheetList.get(row-requiredInfo.length).setEarningsCode("REG");}
                                    else{
                                        newCell.setCellValue((String)"OVT");
                                        ExcelConversion.empSheetList.get(row-requiredInfo.length).setEarningsCode("OVT");}
                                break;
                            case 6: if(ExcelConversion.empSheetList.get(row-2).getRegHours()!=0)
                                        newCell.setCellValue((Double)ExcelConversion.empSheetList.get(row-2).getRegHours());
                                    else
                                    newCell.setCellValue((Double)ExcelConversion.empSheetList.get(row-2).getOtHours());
                                break;    
                            case 8: newCell.setCellValue((int)0);
                                break;
                            case 10: newCell.setCellValue(((String)"BASE"));
                                break;
                    }
                }
            }
        }
        wkbook.write(outputStream);
    }    
    
    public void chooseFile() throws IOException, InvalidFormatException{
        
    filePath.textProperty().addListener((a,oldtxt,newtxt)->{ 
        newWindow();
        System.out.println("old "+oldtxt);
        System.out.println("new "+newtxt);
        System.out.println("e "+a);
    });    
    System.out.println("here1");
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("excel files (*.xlsx)", "*xlsx");
    fileChooser.getExtensionFilters().add(extFilter);
    File retrieve = fileChooser.showOpenDialog(ExcelConversion.stage);
    kantimeWkbook = new XSSFWorkbook(retrieve);
    filePath.setText(retrieve.getPath());
    System.out.println("here2");
    }
    
    public void newWindow(){
        

        try {
            primaryStage2= new Stage();
            System.out.println("counting");
            writeIntoSheet();
            Parent mainMenuFXML;
            mainMenuFXML = FXMLLoader.load(getClass().getResource("/View/reviewEmployee.fxml"));
            reviewScene = new Scene(mainMenuFXML);
            primaryStage2.setScene(reviewScene);
            primaryStage2.setTitle("Batch #"+ExcelConversion.empSheetList.get(ExcelConversion.empSheetList.size()-1).getBatchID());
            primaryStage2.show();
            //filePath.clear();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    public void changeToEmployeeProfile() throws IOException{
        primaryStage2= new Stage();
        Parent mainMenuFXML;

        mainMenuFXML = FXMLLoader.load(getClass().getResource("/View/EmployeeProfiles.fxml"));
        System.out.println("1");
            reviewScene = new Scene(mainMenuFXML);
            System.out.println("2");
            primaryStage2.setScene(reviewScene);
            System.out.println("3");
            primaryStage2.show();
            System.out.println("4");
    }
    
    public void toggleSickTime(){
        if(stCheck.isSelected())
            sickTime.setDisable(true);
        else
            sickTime.setDisable(false);
    }
}
