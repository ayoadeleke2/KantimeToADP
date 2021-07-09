/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

//import static com.sun.org.apache.bcel.internal.generic.Type.STRING;
import Controller.HomeController;
import Data.Employees;
import Data.JSONEmployeesFactory;
import Data.PayrollSheet;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        public static Employees emp = new Employees();
        public static ArrayList<Employees> empList;
        public static PayrollSheet empSheet;
        public static ArrayList<PayrollSheet> empSheetList = new ArrayList<>();
        public JSONEmployeesFactory json = new JSONEmployeesFactory();
        public Scene scene;
        public static Stage stage = new Stage();
        
        
        
    @Override
    public void start(Stage primaryStage) throws IOException, InvalidFormatException{
    
            empList = new ArrayList(json.getEmployeeList());
            stage = primaryStage;
            Parent mainMenuFXML;
            mainMenuFXML = FXMLLoader.load(getClass().getResource("home.fxml"));
            scene = new Scene(mainMenuFXML);
            stage.setScene(scene);

        stage.setTitle("HomeWatch CareGiver xlsx Conversion");
        stage.show();
        stage.setOnCloseRequest((event) -> {
                try {
                    if(!empSheetList.isEmpty())
                        HomeController.outputStream.close();}
                catch (IOException ex) {
                    Logger.getLogger(ExcelConversion.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
