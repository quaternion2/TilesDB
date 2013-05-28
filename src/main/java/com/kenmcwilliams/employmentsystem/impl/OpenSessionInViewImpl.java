/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.service.OpenSessionInViewService;
import com.opensymphony.xwork2.ActionInvocation;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author ken
 */
public class OpenSessionInViewImpl implements OpenSessionInViewService {

    private static final Logger log = Logger.getLogger(OpenSessionInViewImpl.class.getName());
    PlatformTransactionManager platformTransactionManager;
    @PersistenceContext
    private EntityManager em;

    @Override
    public void setTransactionManager(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    /**
     * Implementation code for this method was derived from: 
     * http://static.springsource.org/spring/docs/3.0.x/reference/transaction.html#transaction-programmatic
     * 
     **/
    @Override
    public String execute(ActionInvocation ai) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // explicitly setting the transaction name is something that can only be done programmatically
        Object object = new Object();
        def.setName("" + object.hashCode());
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = platformTransactionManager.getTransaction(def);
        String invoke = "error";
        try {
            log.info("OSIV should be active now!");
            invoke = ai.invoke();
        } catch (Exception ex) {
            platformTransactionManager.rollback(status);
            throw ex;
        }
        platformTransactionManager.commit(status);
        log.info("OSIV closing...");
        return invoke;
    }
}
