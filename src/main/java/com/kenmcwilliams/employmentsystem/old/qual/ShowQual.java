/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.old.qual;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@Results({
    @Result(name = "success", location = "edit-qual", type="chain"),
    @Result(name = "error", location = "edit-qual", type="chain")})
public class ShowQual extends ActionSupport {

    private static final Logger log = Logger.getLogger(ShowQual.class.getName());
    @Autowired
    QualService qualService;
    private Qual qual = new Qual();
    private Integer id = null;
    
    @Override
    public String execute() {
        log.info("ShowQual execute");
        qual = qualService.getQual(id);
        return SUCCESS;
    }

    public Qual getQual() {
        return qual;
    }

    public void setQual(Qual qual) {
        this.qual = qual;
    }

    @Override
    public void validate() {
        if (id == null) {
            log.info("ShowQual validate ERROR");
            this.addFieldError("id", "id must not be null");
        }
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
}
