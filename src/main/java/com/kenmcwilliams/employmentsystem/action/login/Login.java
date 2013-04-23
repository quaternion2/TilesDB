/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.login;

import com.kenmcwilliams.employmentsystem.service.AuthenticationService;
import com.kenmcwilliams.employmentsystem.util.ApplicationConstants;
import com.kenmcwilliams.s2.interceptor.User;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@Result(name = "success", type = "redirectAction", params = {"namespace", "/", "actionName", ""})
public class Login extends ActionSupport implements SessionAware {

    private String userName;
    private String password;
    private Map<String, Object> session;
    @Autowired
    private AuthenticationService authenticationService;
    private User login;

    @Override
    public String execute() {
        this.session.put(ApplicationConstants.USER.name(), login);
        return SUCCESS;
    }

    @Override
    public void validate() {
        login = authenticationService.login(userName, password);
        if (login == null) {
            this.addActionError("User name or password is incorrect");
            this.addFieldError("null", "null");//Used to force return of LOGIN
        }
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
