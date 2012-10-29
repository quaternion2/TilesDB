/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.html;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
public class ListQualificationForms extends ActionSupport{
    private static final Logger log = Logger.getLogger(ListQualificationForms.class.getName());
    @Autowired
    private QualService qualService;
    private List<Qual> quals;
    
    @Override
    public String execute(){
        log.info("In list-qualification-forms action");
        quals = qualService.listQuals();
        log.log(Level.INFO, "# quals returned {0}", getQuals().size());
        return SUCCESS;
    }

    /**
     * @return the quals
     */
    public List<Qual> getQuals() {
        return quals;
    }
}
