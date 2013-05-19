/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.service.ResumeService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@ParentPackage("secure-json-default")
@Results({
    @Result(name = "success", type = "json"),
    @Result(name = "input", type = "json")
})
public class ListResumeNames extends ActionSupport {
    @Autowired
    private ResumeService resumeService;
    private Integer candidateId;
    private List<Pair<Integer, String>> resumes;

    @Override
    public String execute() {
        setResumes(resumeService.listResumeNamesByCandidate(candidateId));
        return SUCCESS;
    }
    
    public Integer getCandidateId(){
        return this.candidateId;
    }
    
    public void setCandidateId(Integer candidateId){
        this.candidateId = candidateId;
    }

    /**
     * @return the resumes
     */
    public List<Pair<Integer, String>> getResumes() {
        return resumes;
    }

    /**
     * @param resumes the resumes to set
     */
    public void setResumes(List<Pair<Integer, String>> resumes) {
        this.resumes = resumes;
    }
    
}
