/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.old.qual;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.orm.QualLine;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@Results({
    @Result(name = "success", location = "edit-qual", type = "redirectAction", params = {"id", "${id}"}),
    @Result(name = "input", location = "edit-qual", type = "redirectAction", params = {"id", "${id}"})
})
public class UpdateQualLine extends ActionSupport {
    private static final Logger log = Logger.getLogger(UpdateQualLine.class.getName());
    @Autowired
    QualService qualService;
    private QualLine qualLine = new QualLine();
    private Integer id = null;

    @Override
    public String execute() {
        log.info("update-qual-line action");
        //qualLine = qualService.getQualLine(id);
        //TODO: validate!
        qualService.updateQualLine(qualLine);
        return SUCCESS;
    }

    //public QualLine getQualLine() {
    //    return qualLine;
    //}
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

    public QualLine getQualLine() {
        return qualLine;
    }

    public void setQualLine(QualLine qualLine) {
        this.qualLine = qualLine;
    }

    @Override
    public void validate() {
        if (this.getId() == null) {
            String msg = "No id for qual specified";
            this.addFieldError("id", msg);
            log.log(Level.WARNING, msg);
        } 
        if (qualLine.getId() == null) {
            String msg = "No id for qualLine specified";
            this.addFieldError("id", msg);
            log.log(Level.WARNING, msg);
        }
        if (qualLine.getNumber() == null) {
            String msg = "No ordinal specified";
            this.addFieldError("number", msg);
            log.log(Level.WARNING, msg);
        }
        if (qualLine.getDescription() == null) {
            String msg = "No description specified";
            this.addFieldError("description", msg);
            log.log(Level.WARNING, msg);
        }
        if (qualLine.getMandatory() == null) {
            String msg = "No mandatory specified";
            this.addFieldError("mandatory", msg);
            log.log(Level.WARNING, msg);
        }
        if (qualLine.getMonths() == null) {
            String msg = "No months specified";
            this.addFieldError("months", msg);
            log.log(Level.WARNING, msg);
        }
    }
}
