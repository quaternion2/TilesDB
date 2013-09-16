/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.qual;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@ParentPackage("package-kjson")
@Result(type = "kjson")
public class LinesById extends ActionSupport {

    @Autowired
    private QualService qualService;
    private Integer id;
    private Object jsonModel;
    private String[] excludeParams = {"qualId"};
    
    @Override
    public String execute(){
        Qual qual = qualService.getQual(id);
        //TODO: returning "qualId" 
        jsonModel = qual.getQualLineCollection();
        return SUCCESS;
        
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
     * @return the jsonModel
     */
    public Object getJsonModel() {
        return jsonModel;
    }

    /**
     * @return the excludeParams
     */
    public String[] getExcludeParams() {
        return excludeParams;
    }

    /**
     * @param excludeParams the excludeParams to set
     */
    public void setExcludeParams(String[] excludeParams) {
        this.excludeParams = excludeParams;
    }
}
