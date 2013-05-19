/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.Resume;
import com.kenmcwilliams.employmentsystem.service.ResumeService;
import com.opensymphony.xwork2.ActionSupport;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.logging.Logger;
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
    @Result(name = "success", type = "kjson"),
    @Result(name = "input", type = "kjson")
})
public class GetResume extends ActionSupport {

    private static final Logger log = Logger.getLogger(GetResume.class.getName());
    @Autowired
    private ResumeService resumeService;
    private Integer resumeId;
    //private Resume resume;
    private Object jsonModel;
    private String[] includeParams = {"positionCollection.positionPointCollection"};

    @Override
    public String execute() {
        jsonModel = resumeService.getResume(resumeId);
        log.info(new JSONSerializer().serialize(getJsonModel()));
        
        return SUCCESS;
    }

    /**
     * @param candidateId the candidateId to set
     */
    public void setResumeId(Integer candidateId) {
        this.resumeId = candidateId;
    }

    /**
     * @return the jsonModel
     */
    public Object getJsonModel() {
        return jsonModel;
    }

    /**
     * @return the includeParams
     */
    public String[] getIncludeParams() {
        return includeParams;
    }
}
