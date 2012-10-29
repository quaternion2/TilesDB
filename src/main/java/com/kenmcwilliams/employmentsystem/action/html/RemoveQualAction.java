/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.html;

import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@Result(name = "success", type = "redirectAction", params = {"actionName", "list-qualification-forms", "namespace", "/html"})
public class RemoveQualAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(RemoveQualAction.class.getName());
    @Autowired
    private QualService qualService;
    private int id;
    @Override
    public String execute() {
        try {
            log.log(Level.INFO, "In add-qual execute() with id of: {0}", id);          
            qualService.deleteQual(id);
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: {0}", e.getMessage());
        }
        return SUCCESS;
    }
    /**
     * @param ID the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
