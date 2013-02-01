/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.service.CriteriaConstraints;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Search makes the following assumptions
 *
 * @author ken
 */
public class Search extends ActionSupport {

    @Autowired
    private CrudService crudService;
    private java.util.List<Object> candidateList;
    private Long count;
    private Candidate candidate;
    private Map<String, Map<CriteriaConstraints, java.util.List>> constraints;
    private Map<CriteriaConstraints, java.util.List> fnameMap;
    private Map<CriteriaConstraints, java.util.List> lnameMap;

    public void prepare() {
        constraints = new HashMap<>();
        //fnameMap = 
        //constraints.put("fname", new HashMap());
    }

    @Override
    public String execute() {
        if (candidate.getFname() != null && candidate.getFname().isEmpty() == false) {
            ArrayList parameters = new ArrayList();
            parameters.add("%");
            parameters.add(candidate.getFname());
            parameters.add("%");//these values will be concatinated by the service, it could be done as one string "%namevalue%" but I want to check this
            addConstraint("fname", CriteriaConstraints.Like, parameters);
        }
        if (candidate.getLname() != null && candidate.getLname().isEmpty() == false) {
            ArrayList parameters = new ArrayList();
            parameters.add("%");
            parameters.add(candidate.getLname());
            parameters.add("%");//these values will be concatinated by the service, it could be done as one string "%namevalue%" but I want to check this
            addConstraint("lname", CriteriaConstraints.Like, parameters);
        }
        //candidateList = crudService.find(Candidate.class, candidate, constraints);
        count = (long)candidateList.size();
        return SUCCESS;
    }

    /**
     * @return the candidateList
     */
    public java.util.List<Object> getCandidateList() {
        return candidateList;
    }

    public Long getCount() {
        return count;
    }

    /**
     * @return the model
     */
    public Candidate getCandidate() {
        return candidate;
    }

    /**
     * @param model the model to set
     */
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    private void addConstraint(String fieldName, CriteriaConstraints criteriaConstraint, ArrayList parameters) {
        Map fieldConstraints = new HashMap<>();
        fieldConstraints.put(criteriaConstraint, parameters);
        constraints.put(fieldName, fieldConstraints);
    }
}
