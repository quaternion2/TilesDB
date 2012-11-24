/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.html.candidate;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
public class List extends ActionSupport{
    @Autowired
    private CrudService crudService;
    private java.util.List<Candidate> candidates = null;
    
    @Override
    public String execute(){
        Long count = crudService.count(Candidate.class);
        final Object temp = crudService.page(Candidate.class, 0, count.intValue()); // get full list
        candidates = (java.util.List<Candidate>) temp;
        return SUCCESS;
    }
    
    public java.util.List<Candidate> getCandidates(){
        return candidates;
    }
}
