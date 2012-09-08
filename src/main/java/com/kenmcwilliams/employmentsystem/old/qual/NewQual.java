/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.old.qual;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@Results({
    @Result(name="success", location = "list-quals", type = "redirectAction"),
    @Result(name="input", location = "list-quals", type = "redirectAction")
})
public class NewQual extends ActionSupport implements ModelDriven<Qual>{
    private Qual qual = new Qual();
    @Autowired QualService qualService;
    
    @Override
    public String execute(){
        
        qualService.addQual(qual);
        return SUCCESS;
    }
    
    @Override
    public Qual getModel() {
        return qual;
    }
}
