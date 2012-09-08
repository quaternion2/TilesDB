/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.old.qual;

import com.kenmcwilliams.employmentsystem.orm.QualLine;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
//@ParentPackage("json-default")
//@Result(type = "json")
@Results({
    @Result(name="success", location = "edit-qual", type = "redirectAction", params={"id", "${qualId}"}),
    @Result(name="input", location = "edit-qual", type = "redirectAction", params={"id", "${qualId}"}),
})
public class NewQualLine extends ActionSupport {
    private static final Logger log = Logger.getLogger(NewQualLine.class.getName());
    @Autowired
    QualService qualService;
    
    private Integer qualId = null;
    private QualLine qualLine = new QualLine();

    @Override
    public String execute(){
        log.info("Action new-qual-line execute");
        qualService.addQualLine(qualId, qualLine);
        return SUCCESS;
    }
    
    public void setQualId(int qualId){
        this.qualId = qualId;
    }
    
    public Integer getQualId(){
        return qualId;
    }
    
    public QualLine getQualLine(){
        return qualLine;
    }
    
    public void setQualLine(QualLine qualLine){
        this.qualLine = qualLine;
    }
    
    @Override
    public void validate(){
        if (qualId == null){
            this.addFieldError("new.qualId", "New qualId must not be null");
        }
        //check qualLine for completeness
        if (qualLine.getDescription() == null){
            this.addFieldError("new.description", "New description must not be null");
        }
        if(qualLine.getMandatory() == null){
            this.addFieldError("new.mandatory", "New mandatory must not be null");
        }
        if(qualLine.getMonths() == null){
            this.addFieldError("new.months", "New months must not be null");
        }
        if(qualLine.getNumber() == null){
            this.addFieldError("new.number", "new.number must not be null");
        }
    }
}
