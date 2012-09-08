/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author ken
 */
@ParentPackage("json-default")
@Action(value = "/read/{entity}",
        results={@Result(type = "json")}
)        
public class Read extends ActionSupport implements ModelDriven{
    private String entity;
    
    public String getEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void setEntity(String entity){
        this.entity = entity;
    }

    @Override
    public Object getModel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
