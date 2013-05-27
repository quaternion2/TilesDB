/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.*;
import com.kenmcwilliams.employmentsystem.service.ResumeService;
import com.kenmcwilliams.s2.interceptor.User;
import com.kenmcwilliams.s2.interceptor.UserAware;
import com.opensymphony.xwork2.ActionSupport;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
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
//TODO: Does not return proper error messages on error (input actually).
public class AddResume extends ActionSupport implements UserAware {

    private static final Logger log = Logger.getLogger(AddResume.class.getName());
    @Autowired
    ResumeService resumeService;
    User user;
    //private Resume resume = new Resume();
    private Header header = new Header();
    private ArrayList<Role> roles = new ArrayList();
    //private Object jsonModel; //used for output

    private Resume assembleResume() {
        Resume res = new Resume();
        if (header.resumeId != null) {//TODO: Should do proper valididation
            res.setId(header.resumeId);
        }
        //resume.setCandidateId(null);
        Candidate candidate = new Candidate();
        candidate.setId(header.candidateId);
        res.setCandidateId(candidate);
        //resume.setCreatedBy();
        res.setCreatedDate(new Date()); //TODO: Set by pre-persist?
        ////resume.setId(Integer.SIZE); //auto set
        res.setName(header.name);
        //TODO: add description field to resume
        res.setOpportunityId(null);
        java.util.Set<Position> positions = new HashSet<>();
        for (Role r : roles) {
            //log.info("Adding");
            Position p = new Position();
            p.setId(r.getId());
            p.setResumeId(res);
            Company company = new Company(); //TODO: Company and Location are entwined, need to normalize out locationl
            company.setName(r.getCompanyName());
            p.setCompanyId(company);
            //p.setCurrentlyEmployed(currentlyEmployed); //TODO: Why takes byte[] ?
            p.setEndDate(r.getEndDate());
            p.setStartDate(r.getStartDate());
            java.util.Set<PositionPoint> points = new java.util.HashSet<>();
            Integer rank = 0;
            for (PositionPoint detail : r.getDetails()) {
                //PositionPoint pp = new PositionPoint();
                //pp.setDescription(detail);
                detail.setRank(rank++);
                //detail.setId(null); //THIS IS CRITICAL, if the rank is forced to be null then a position will be created to house it. 
                detail.setRoleId(p);
                log.info("Adding PP with rank:" + rank + " and id: " + detail.getId());
                points.add(detail);
            }
            p.setPositionPointCollection(points);
            //p.setPositionPointCollection(points);//Takes Colection<PositionPoint>
            p.setTitle(r.getRole());//takes string
            positions.add(p);
        }
        res.setPositionCollection(positions);
        ////resume.setQualId(null);
        ////resume.setUpdatedBy(Integer.MIN_VALUE);
        ////resume.setUpdatedDate(null);
        //NOTE: I think add resume will need to save the resume then
        //save the positions (because they need reference to the resume header id
        //then save the position points (because they need reference to the position id
        //then again maybe JPA is smart enough to avoid this
        resumeService.saveResume(res);
        return res;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public String execute() {
        log.log(Level.INFO, "Received Header: {0}", (new JSONSerializer().serialize(getHeader())));
        log.log(Level.INFO, "Received Roles: {0}", (new JSONSerializer().serialize(getRoles())));
        for (Role r : roles) {
            log.log(Level.INFO, "Received Details[]: {0}", (new JSONSerializer().serialize(r.getDetails())));
        }


        Resume assembleResume = this.assembleResume();
        return SUCCESS;
    }

    @Override
    public void validate() {
        //resume must have a name to save the result
        String name = getHeader().getName();
        if (name == null || name.isEmpty()) {
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
