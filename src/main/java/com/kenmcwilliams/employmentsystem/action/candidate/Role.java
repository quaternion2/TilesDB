/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.PositionPoint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ken
 */
public class Role {

    private Integer id;
    private String companyName;
    private String role;
    private String dateWorked;
    private Date startDate;
    private Date endDate;
    private ArrayList<PositionPoint> details = new ArrayList<>();

    public Integer getId(){
        return this.id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the dateWorked
     */
    public String getDateWorked() {
        return dateWorked;
    }

    /**
     * @param dateWorked the dateWorked to set
     */
    public void setDateWorked(String dateWorked) {
        //format of accepted String "Jan 2001 - Dec 2001"
        SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy");
        this.dateWorked = dateWorked;
        String[] split = dateWorked.split("-");
        if (split.length != 2) {
            throw new IllegalArgumentException("Date String not in correct format: " + dateWorked + " a working example would be: Example: Jan 2001 - Dec 2001");
        }

        Calendar cal = Calendar.getInstance();
        try {
            this.startDate = formatter.parse(split[0].trim());
            this.endDate = formatter.parse(split[1].trim());
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Parse Error, String not in correct format: " + dateWorked + " a working example would be: Example: Jan 2001 - Dec 2001");
        }
    }

    /**
     * @return the details
     */
    public ArrayList<PositionPoint> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(ArrayList<PositionPoint> details) {
        this.details = details;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }
}
