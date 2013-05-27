/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import com.opensymphony.xwork2.ActionInvocation;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author ken
 */
public interface OpenSessionInViewService {
    String execute(ActionInvocation ai) throws Exception;
    void setTransactionManager(PlatformTransactionManager platformTransactionManager);
}
