/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.service.ResumeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author ken
 */
@ParentPackage("json-default")
@Result(type = "json")
public class AddResume extends ActionSupport {
    @Inject ResumeService resumeService;
    private static final Logger log = Logger.getLogger(AddResume.class.getName());
    //private Object jsonModel; //used for output

    public class Header {

        private Integer candidateId = null;
        private Integer resumeId = null;
        private String name = null;
        private String description = null;

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
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }
    };

    private Header header = new Header();
    private ArrayList<Role> roles = new ArrayList();

    @Override
    public String execute() {
        log.log(Level.INFO, "Received Header: {0}", (new JSONSerializer().serialize(getHeader())));
        log.log(Level.INFO, "Received Roles: {0}", (new JSONSerializer().serialize(getRoles())));
        for(Role r : roles){
            log.log(Level.INFO, "Received Details[]: {0}", (new JSONSerializer().serialize(r.getDetails())));
        }
        return SUCCESS;
    }
    
    @Override
    public void validate(){
        //resume must have a name to save the result
        String name = getHeader().getName();
        if (name == null || name.isEmpty()){
            this.addFieldError("name", "Resume field must have a name.");
        }
    }

    /**
     * @return the header
     */
    public Header getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     * @return the roles
     */
    public ArrayList<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
}
