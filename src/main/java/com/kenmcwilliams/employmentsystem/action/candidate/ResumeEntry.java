/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.orm.Resume;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.service.ResumeService;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@ParentPackage(value = "tiles-package")
@Result(type = "tiles")
public class ResumeEntry extends ActionSupport {
    @Autowired
    private CrudService crudService;
    @Autowired
    private ResumeService resumeService;
    private Integer resumeId;
    private Integer id;//candidate id
    private Resume resume;
    private Candidate candidate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy");

    @Override
    public String execute() {
        if (resumeId != null) {
            resume = resumeService.getResume(resumeId);
            candidate = resume.getCandidateId();
        } else if (id != null) {
            candidate = (Candidate) crudService.read(Candidate.class, id);
        }//TODO: erro handling required - should set up global error handling for error
        return SUCCESS;
    }
    
    public String formatDate(Date d){
        return dateFormat.format(d);
    }

    /**
     * @return the resumeId
     */
    public Integer getResumeId() {
        return resumeId;
    }

    /**
     * @param resumeId the resumeId to set
     */
    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    /**
     * @return the resume
     */
    public Resume getResume() {
        return resume;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the candidate
     */
    public Candidate getCandidate() {
        return candidate;
    }
}
