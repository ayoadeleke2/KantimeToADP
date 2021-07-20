/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author maple
 */
public class PayrollSheetTableViewFactory {
    
    public static ObservableList getColumns(){
        ObservableList<TableColumn> list = FXCollections.observableArrayList();
        
        TableColumn idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(75);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        
        TableColumn regHoursColumn = new TableColumn<>("Regular Hours");
        regHoursColumn.setPrefWidth(150);
        regHoursColumn.setCellValueFactory(new PropertyValueFactory<>("regHours"));
        
        TableColumn coCodeColumn = new TableColumn<>("Company Code");
        coCodeColumn.setPrefWidth(75);
        coCodeColumn.setCellValueFactory(new PropertyValueFactory<>("coCode"));
        
        TableColumn OthoursColumn = new TableColumn<>("OverTime");
        OthoursColumn.setPrefWidth(75);
        OthoursColumn.setCellValueFactory(new PropertyValueFactory<>("OtHours"));
        
        TableColumn PayRateColumn = new TableColumn<>("Pay Rate");
        PayRateColumn.setPrefWidth(75);
        PayRateColumn.setCellValueFactory(new PropertyValueFactory<>("PayRate"));
        
        TableColumn earingsCodeColumn = new TableColumn<>("Earnings Code");
        earingsCodeColumn.setPrefWidth(75);
        earingsCodeColumn.setCellValueFactory(new PropertyValueFactory<>("earningsCode"));
        
        list.addAll(idColumn,regHoursColumn,coCodeColumn,OthoursColumn,PayRateColumn,earingsCodeColumn);
        
        return list;
    }
}