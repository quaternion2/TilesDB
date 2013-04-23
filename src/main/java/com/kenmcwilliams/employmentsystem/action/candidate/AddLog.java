package com.kenmcwilliams.employmentsystem.action.candidate;


import com.kenmcwilliams.employmentsystem.service.CandidateService;
import com.kenmcwilliams.employmentsystem.util.ApplicationConstants;
import com.kenmcwilliams.s2.interceptor.User;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ken
 */
@Result(name = "success", type = "redirectAction", params = {"namespace", "/candidate", "actionName", "details", "id", "${candidateId}"})
public class AddLog extends ActionSupport implements SessionAware{
    private String log;
    private Integer candidateId;
    private Map<String, Object> session;
    @Autowired
    private CandidateService candidateService;
    
    @Override
    public String execute(){
        //assert(candidateService != null);//TODO: Remove after testing
        User user = (User)session.get(ApplicationConstants.USER.name());
        if (user == null)
            return LOGIN;
        candidateService.addLog(candidateId, user.getId(), log);
        return SUCCESS;
    }
   
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * @return the candidateId
     */
    public Integer getCandidateId() {
        return candidateId;
    }

    /**
     * @param candidateId the candidateId to set
     */
    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }
    
    @Override
    public void validate(){
        if (candidateId == null){
            this.addFieldError("candidateId", "CandiateId must not be empty");
        }
        if (log == null || log.length() == 0){
            this.addFieldError("log", "Log must not be empty");
        }
    }
}
