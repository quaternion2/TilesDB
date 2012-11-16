/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.html;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */

public class DisplayQualAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(DisplayQualAction.class.getName());
    @Autowired
    private QualService qualService;
    private Qual qual;
    private Integer id;

    @Override
    public String execute() {
        qual = qualService.getQual(id);
        return SUCCESS;
    }
    
    @Action(value="edit-qual")
    public String editQual() {
        log.info("called edit-qual");
        return this.execute();
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Qual getQual() {
        return qual;
    }
}
