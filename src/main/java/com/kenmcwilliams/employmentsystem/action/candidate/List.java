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
public class List extends ActionSupport {

    @Autowired
    private CrudService crudService;
    private java.util.List<Object> candidateList;
    private Long count;

    @Override
    public String execute() {
        count = crudService.count(Candidate.class);
        candidateList = crudService.page(Candidate.class, 0, count.intValue());
        return SUCCESS;
    }

    /**
     * @return the candidateList
     */
    public java.util.List<Object> getCandidateList() {
        return candidateList;
    }
    
    public Long getCount(){
        return count;
    }
}
