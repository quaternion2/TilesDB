/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.login;

import com.kenmcwilliams.s2.interceptor.User;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ken
 */
@Result(name = "success", type = "redirectAction", params = {"namespace", "/", "actionName", ""})
public class Login extends ActionSupport implements SessionAware{
    private String userName;
    private String password;
    private Map<String, Object> session;
            
    @Override
    public String execute(){
        User user = new User(userName);
        this.session.put("user", user);
        return SUCCESS;
    }
    
    @Override
    public void validate(){
        boolean error = false;
        if (userName.compareTo("ken") != 0){
            error = true;
        }
        if(password.compareTo("1234") != 0){
            error = true;
        }
        if (error == true){
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
