/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.s2;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
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
    private HashMap<String, String> toJson = new HashMap();
    private Object jsonModel;
    
    @Override
    public String execute(){
        toJson.put("One", "Fish1");
        toJson.put("Two", "Fish2");
        toJson.put("Red", "Fish3");
        toJson.put("Blue", "Fish4");
        return SUCCESS;
    }
}
