/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.s2.interceptor.dynamic;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * NOT SURE IF I NEED FLEX JSON, will try to do this without it (using the provided json interceptor)
 * Allows an action to be set with json data from an entity 
 * @author ken
 */
public class Kflexjson implements Interceptor{

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Action action = (Action) ai.getAction();
        if (action instanceof ModelDriven){
            
        }
        //require action to contain name object
        return ai.invoke();
    }
    
}
