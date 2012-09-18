/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */

public class Magic extends ActionSupport{
    private static final Logger log = Logger.getLogger(Magic.class.getName());
    @Autowired
    private CrudService crudService;   
    
    @Override
    public String execute(){
        return SUCCESS;
    }
}
