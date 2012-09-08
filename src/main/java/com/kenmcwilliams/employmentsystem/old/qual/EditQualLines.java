/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.old.qual;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
public class EditQualLines extends ActionSupport {
    private static final Logger log = Logger.getLogger(EditQualLines.class.getName());
    @Autowired
    QualService qualService;
    private Qual qual;
    private Integer id;

    @Override
    public String execute() {
        log.info("Action edit-qual execute");
        qual = qualService.getQual(id);
        return SUCCESS;
    }

    @Override
    public void validate() {

        if (id == null) {
            this.addFieldError("id", "id is uninitialized");
            log.log(Level.WARNING, "EditQual_validate() id is uninitialized", this.getFieldErrors().size());
        }
        
    }

    public Qual getQual() {
        return qual;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
