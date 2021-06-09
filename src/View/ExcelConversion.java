/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

//import static com.sun.org.apache.bcel.internal.generic.Type.STRING;
import Data.Employee;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author maple
 */
public class ExcelConversion extends Application {
        public static File retrieve;
        public static String fileName = ".\\Excelsheet\\payroll-tst-kuns1.xlsx";
        public static File fInput = new File(fileName); 
        public static XSSFWorkbook wkbook;
        public static XSSFSheet sheet1;
        public static int rows;
        public static int col;
        public static Employee emp;
        public static ArrayList<Employee> empList = new ArrayList<>();
        public static Scene scene;
        public static Stage stage = null;
        Scanner scan = new Scanner(System.in);
        
        
        
    @Override
    public void start(Stage primaryStage) throws IOException, InvalidFormatException{
        
        
        //for(int i=0;i<empList.size();i++)
          //  System.out.println(ExcelConversion.empList.get(i).getRegHours());
            Parent mainMenuFXML;
            mainMenuFXML = FXMLLoader.load(getClass().getResource("home.fxml"));
            scene = new Scene(mainMenuFXML);
            primaryStage.setScene(scene);
        /*
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        */
        primaryStage.setTitle("HomeWatch CareGiver xlsx Conversion");
        primaryStage.show();
        //String date1 = startDate.getPromptText();
        //String date2 = endDate.getPromptText();
        //writeExcel.writeIntoSheet(date1,date2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void writeReplace() throws IOException, InvalidFormatException{
        
    }
}
