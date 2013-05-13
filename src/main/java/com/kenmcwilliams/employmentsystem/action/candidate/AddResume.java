/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.opensymphony.xwork2.ActionSupport;
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
@Result(type="json")
public class AddResume extends ActionSupport {

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

    public class Role {

        private String companyName;
        private String role;
        private String dateWorked;
        private ArrayList<String> details = new ArrayList();

        /**
         * @return the companyName
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         * @param companyName the companyName to set
         */
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        /**
         * @return the role
         */
        public String getRole() {
            return role;
        }

        /**
         * @param role the role to set
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         * @return the dateWorked
         */
        public String getDateWorked() {
            return dateWorked;
        }

        /**
         * @param dateWorked the dateWorked to set
         */
        public void setDateWorked(String dateWorked) {
            this.dateWorked = dateWorked;
        }

        /**
         * @return the details
         */
        public ArrayList<String> getDetails() {
            return details;
        }

        /**
         * @param details the details to set
         */
        public void setDetails(ArrayList<String> details) {
            this.details = details;
        }
    }
    private Header header = new Header();
    private ArrayList<Role> roles = new ArrayList();
    
    @Override
    public String execute() {
        log.log(Level.INFO, "Received Data: {0}", (new JSONSerializer().serialize(getHeader())));
        log.log(Level.INFO, "Received Data: {0}", (new JSONSerializer().serialize(roles)));
        return SUCCESS;
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
}
