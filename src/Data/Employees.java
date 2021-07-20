/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author maple
 */
public class Employees extends PayrollSheet{
    
    private double vacationTime;
    private double overtime;
    private String name;
    private double paidTimeOff;
    private double totalHoursWorked;
    private double sickTimeAccrued;
    private double payPeriodHours;
    private int yearsEmployed;
    private int IDs;

    public Employees() {
    }

    public Employees(double vacationTime, double overtime, double sickTime, String name, double paidTimeOff, double totalHoursWorked, double sicktimeacc) {
        this.vacationTime = vacationTime;
        this.overtime = overtime;
        this.name = name;
        this.paidTimeOff = paidTimeOff;
        this.totalHoursWorked = totalHoursWorked;
        this.sickTimeAccrued = sicktimeacc;
    }

    /**
     * @return the vacationTime
     */
    public double getVacationTime() {
        return vacationTime;
    }

    /**
     * @param vacationTime the vacationTime to set
     */
    public void setVacationTime(double vacationTime) {
        this.vacationTime = vacationTime;
    }

    /**
     * @return the overtime
     */
    public double getOvertime() {
        return overtime;
    }

    /**
     * @param overtime the overtime to set
     */
    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the paidTimeOff
     */
    public double getPaidTimeOff() {
        return paidTimeOff;
    }

    /**
     * @param paidTimeOff the paidTimeOff to set
     */
    public void setPaidTimeOff(double paidTimeOff) {
        this.paidTimeOff = paidTimeOff;
    }

    /**
     * @return the totalHoursWorked
     */
    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    /**
     * @param totalHoursWorked the totalHoursWorked to set
     */
    public void setTotalHoursWorked(double totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    /**
     * @return the sickTimeAccrued
     */
    public double getSickTimeAccrued() {
        return sickTimeAccrued;
    }

    /**
     * @param sickTimeAccrued the sickTimeAccrued to set
     */
    public void setSickTimeAccrued(double sickTimeAccrued) {
        this.sickTimeAccrued = sickTimeAccrued;
    }
    
    public double timeToDecimal(double hours, double mins){
        double totalHours;

        totalHours = (double)(hours/1)+(double)(mins/60);
        return totalHours;
    }
    
    public double calculateOvertime(int hoursWorked){
        double overtime =0;
        
        return overtime;
    }
    /**
     * Sick time accumulates at a rate of 1 hour for every hour 30 hours worked per benefit year up to 40 hours 
     * @param hoursWorked
     * @return sick Time
     */
    public double calculateSickTime(double hoursWorked){
        
        if(sickTimeAccrued<=40){
            sickTimeAccrued = sickTimeAccrued+ hoursWorked/30;
            if(sickTimeAccrued>40){
                sickTimeAccrued = 40;
                return 40;
            }
            return sickTimeAccrued;
        }        
        return sickTimeAccrued;
    }

    /**
     * @return the ID
     */
    public int getIDs() {
        return IDs;
    }

    /**
     * @param IDs the ID to set
     */
    public void setIDs(int ID) {
        this.IDs = ID;
    }
}
