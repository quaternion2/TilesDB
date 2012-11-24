/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.html.candidate;

import com.kenmcwilliams.employmentsystem.enums.Country;
import com.kenmcwilliams.employmentsystem.enums.Province;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author ken
 */
public class Add extends ActionSupport{
    public Integer Id = null;
    public String fname;
    public String lname;
    public String homePhone; //best as a string people have weird phone numbers
    public String cellPhone;
    public String address;
    public String city;
    public Province.Code province;
    public String areaCode;
    public Country.Code country;
    public String email;
    public String skype;
    
    @Override
    public String execute(){
        return SUCCESS;
    }
    
    @Override
    public void validate(){
        
    }
}
