/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;
import View.ExcelConversion;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author maple
 */
public class PayrollSheet {
    private int ID;
    private double regHours;
    private String coCode;
    private double OtHours;
    private double payRate;
    private String earningsCode;
    private int batchID;
    
    PayrollSheet(int ID, double regHours, String coCode, double OtHours, double payRate){
        this.ID = ID;
        this.regHours = regHours;
        this.coCode = coCode;
        this.OtHours = OtHours;
        this.payRate = payRate;
    }

    public PayrollSheet() {
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the regHours
     */
    public double getRegHours() {
        return regHours;
    }

    /**
     * @param regHours the regHours to set
     */
    public void setRegHours(double regHours) {
        this.regHours = regHours;
    }

    /**
     * @return the coCode
     */
    public String getCoCode() {
        return coCode;
    }

    /**
     * @param coCode the coCode to set
     */
    public void setCoCode(String coCode) {
        this.coCode = coCode;
    }

    /**
     * @return the OtHours
     */
    public double getOtHours() {
        return OtHours;
    }

    /**
     * @param OtHours the OtHours to set
     */
    public void setOtHours(double OtHours) {
        this.OtHours = OtHours;
    }

    /**
     * @return the payRate
     */
    public double getPayRate() {
        return payRate;
    }

    /**
     * @param payRate the payRate to set
     */
    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    /**
     * @return the earningsCode
     */
    public String getEarningsCode() {
        return earningsCode;
    }

    /**
     * @param earningsCode the earningsCode to set
     */
    public void setEarningsCode(String earningsCode) {
        this.earningsCode = earningsCode;
    }

    /**
     * @return the batchID
     */
    public int getBatchID() {
        return batchID;
    }

    /**
     * @param batchID the batchID to set
     */
    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }
    
    public static ArrayList<Integer> getEmployeeID(XSSFWorkbook book ,XSSFSheet sheet){
        
        ArrayList<Integer> list = new ArrayList<>();
        int rows, col;
        sheet = book.getSheetAt(0);
        rows = sheet.getLastRowNum();
        col = sheet.getRow(0).getLastCellNum();
        
        for(int i=1; i <= rows; i++){
            XSSFRow row = sheet.getRow(i);
            for(int j=0; j<col; j++){
                XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if(i>0 && j==4){
                        int value = (int)cell.getNumericCellValue();
                        if(list.contains(value)==false)
                            list.add(value);
                    }
                }
            }
        return list;
        }
}
