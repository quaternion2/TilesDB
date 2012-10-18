/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.resume;

import com.kenmcwilliams.employmentsystem.orm.PositionPoint;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author ken
 */
public class NewResumeRolePoint extends ActionSupport{
    //attributes for role id & point
    public Integer id = null;
    public PositionPoint resumePoint = new PositionPoint();
    
    @Override
    public String execute(){
        return SUCCESS;
    }
}
