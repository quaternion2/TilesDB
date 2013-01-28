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

    @Override
    public Candidate getModel() {
        return model;
    }
}
