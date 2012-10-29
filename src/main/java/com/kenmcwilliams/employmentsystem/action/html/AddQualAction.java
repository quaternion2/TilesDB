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
public class AddQualAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(AddQualAction.class.getName());
    @Autowired
    private QualService qualService;
    private String name, role, description;

    @Override
    public String execute() {
        try {
            log.info("In add-qual execute()");
            Qual qual = new Qual();
            qual.setName(name);
            qual.setRole(role);
            qual.setDescription(description);
            qualService.addQual(qual);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return SUCCESS;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
