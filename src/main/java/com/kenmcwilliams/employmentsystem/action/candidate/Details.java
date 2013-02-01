/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
public class Details extends ActionSupport {

    @Autowired
    private CrudService crudService;
    private Candidate candidate;
    private Integer id = null;

    @Override
    public String execute() {
        candidate = (Candidate) crudService.read(Candidate.class, id);
        return SUCCESS;
    }
    
    public Candidate getCandidate(){
        return candidate;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
}
