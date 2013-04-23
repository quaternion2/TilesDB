/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.login;

import com.kenmcwilliams.employmentsystem.util.ApplicationConstants;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ken
 */
@Result(name = "success", type = "redirectAction", params = {"namespace", "/", "actionName", ""})
public class LogOut extends ActionSupport implements SessionAware{
    Map<String, Object> session;
    
    @Override
    public String execute(){
        session.remove(ApplicationConstants.USER.name());
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
