/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.orm.CandidateLog;
import com.kenmcwilliams.employmentsystem.orm.Resume;
import com.kenmcwilliams.employmentsystem.service.CandidateService;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@ParentPackage(value = "tiles-package")
@Result(type = "tiles")
public class Details extends ActionSupport {

    private static final Logger log = Logger.getLogger(Details.class.getName());
    @Autowired
    private CrudService crudService;
    @Autowired
    private CandidateService candidateService;
    private List<CandidateLog> logs;
    //private List<Resume> resumes;
    private Candidate candidate;
    private Integer id = null;

    @Override
    public String execute() {
        candidate = (Candidate) crudService.read(Candidate.class, id);
        log.info("/Candidate/Details: looking up logs...");
        logs = candidateService.getLogs(id);
        log.log(Level.INFO, "retrieved {0} logs", getLogs().size());
        return SUCCESS;
    }
    
    @Action("/candidate/resume-entry")
    public String resumeEntry(){
        return this.execute();
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the logs
     */
    public List<CandidateLog> getLogs() {
        return logs;
    }
}
