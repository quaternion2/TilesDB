/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.html.candidate;

import com.kenmcwilliams.employmentsystem.enums.Country;
import com.kenmcwilliams.employmentsystem.enums.Province;
import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@Result(name = "success", type = "redirectAction", params = {"namespace", "/html/candidate", "actionName", "input"})
public class Add extends ActionSupport implements ModelDriven<Candidate>{

    @Autowired
    private CrudService crudService;
    private Candidate model = new Candidate();
    /*
    private Integer Id = null;
    private String fname;
    private String lname;
    private String homePhone; //best as a string people have weird phone numbers
    private String cellPhone;
    private String address;
    private String city;
    private Province.Code province;
    private String areaCode;
    private Country.Code country;
    private String email;
    private String skype;
*/
    @Override
    public String execute() {
        crudService.create(Candidate.class, model);
        return SUCCESS;
    }

    @Override
    public void validate() {
    }
/**

    public Integer getId() {
        return Id;
    }

 
    public void setId(Integer Id) {
        this.Id = Id;
    }


    public String getFname() {
        return fname;
    }


    public void setFname(String fname) {
        this.fname = fname;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

 
    public String getHomePhone() {
        return homePhone;
    }

 
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }


    public String getCellPhone() {
        return cellPhone;
    }


    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public Province.Code getProvince() {
        return province;
    }


    public void setProvince(Province.Code province) {
        this.province = province;
    }


    public String getAreaCode() {
        return areaCode;
    }


    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Country.Code getCountry() {
        return country;
    }


    public void setCountry(Country.Code country) {
        this.country = country;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getSkype() {
        return skype;
    }


    public void setSkype(String skype) {
        this.skype = skype;
    }
**/
    @Override
    public Candidate getModel() {
        return model;
    }
}
