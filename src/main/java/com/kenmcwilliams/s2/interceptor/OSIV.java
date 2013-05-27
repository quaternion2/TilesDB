/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.s2.interceptor;

import com.kenmcwilliams.employmentsystem.service.OpenSessionInViewService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
public class OSIV implements Interceptor {
    @Autowired
    OpenSessionInViewService osivService;

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void init() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        //EntityTransaction transaction = osivService.getEntityManager().getTransaction();
        String result = osivService.execute(ai);
        //String invoke = ai.invoke();
        //transaction.commit();
        return result;
    }
}
