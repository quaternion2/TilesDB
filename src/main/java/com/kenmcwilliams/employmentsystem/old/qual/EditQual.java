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
public class EditQual extends ActionSupport {

    private static final Logger log = Logger.getLogger(EditQual.class.getName());
    @Autowired
    QualService qualService;
    private Qual qual = new Qual();
    private Integer id = null;

    @Override
    public String execute() {
        log.log(Level.INFO, "EditQual execute id: {0}", id);
        qual.setId(id);
        log.log(Level.INFO, "EditQual execute qual before update: {0}", qual.toString());
        qualService.updateQual(qual);
        qual = qualService.getQual(id);
        log.log(Level.INFO, "EditQual execute qual after update: {0}", qual.toString());
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (this.id == null) {
            this.addFieldError("id", "id must not be null");
        }
    }

    /**
     * @return the qual
     */
    public Qual getQual() {
        return qual;
    }

    /**
     * @param qual the qual to set
     */
    public void setQual(Qual qual) {
        this.qual = qual;
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
    public void setId(int id) {
        this.id = id;
    }
}
