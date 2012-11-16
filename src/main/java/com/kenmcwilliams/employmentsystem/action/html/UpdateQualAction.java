/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.html;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@Result(name = "success", type = "redirectAction", params = {"namespace", "/html", "actionName", "list-qualification-forms"})
public class UpdateQualAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(UpdateQualAction.class.getName());
    @Autowired
    private QualService qualService;
    private Qual qual;
    
    @Override
    public String execute(){
        qualService.updateQual(qual);
        return SUCCESS;
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
}
