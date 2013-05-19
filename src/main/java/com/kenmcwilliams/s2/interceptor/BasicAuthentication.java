/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.s2.interceptor;

import com.kenmcwilliams.employmentsystem.util.ApplicationConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

/**
 *
 * @author ken
 */
public class BasicAuthentication implements Interceptor{

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void init() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet.");
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        User user = (User)session.get(ApplicationConstants.USER.name());
        if (user == null){
            return Action.LOGIN;
        }else{
            Action action = (Action)invocation.getAction();
            if (action instanceof UserAware){
                ((UserAware) action).setUser(user);
            }
            return invocation.invoke();
        }
    }
    
}
