/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maple
 */
public class JSONEmployeesFactory {
    
    public List<Employees> getEmployeeList() {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("Employees.json");
        ArrayList employees = null;
        try {
            employees = mapper.readValue(jsonFile, new TypeReference<ArrayList<Employees>>() {
            });
        } catch (IOException ex) {
            Logger.getLogger(JSONEmployeesFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }
}
