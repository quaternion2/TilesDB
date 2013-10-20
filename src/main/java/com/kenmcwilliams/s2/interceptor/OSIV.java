/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.s2.interceptor;

import com.kenmcwilliams.employmentsystem.service.OpenSessionInViewService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Open Session in View Interceptor
 * @author ken
 */
public class OSIV implements Interceptor {
    @Autowired
    OpenSessionInViewService osivService;

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        String result = osivService.execute(ai);
        return result;
    }
}
