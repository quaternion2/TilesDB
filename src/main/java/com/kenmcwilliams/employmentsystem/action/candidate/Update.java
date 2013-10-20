/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@Result(name = "success", type = "redirectAction", params = {"namespace", "/candidate", "actionName", "details", "id", "${model.id}"})
public class Update extends ActionSupport implements ModelDriven<Candidate> {

    private Candidate model = new Candidate();
    @Autowired
    private CrudService crudService;
    //private Candidate candidate;

    @Override
    public String execute() {
        Map describe;
        try {
            //describe = BeanUtils.describe(model);
            crudService.update(model);
        } catch (Exception ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR;
        } 
        return SUCCESS;
    }
    
    @Override
    public Candidate getModel() {
        return model;
    }
}
