/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.orm.Person;
import com.kenmcwilliams.employmentsystem.service.AuthenticationService;
import com.kenmcwilliams.s2.interceptor.User;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ken
 */
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{

    private static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Override
    public User login(String userName, String password) {
        TypedQuery<Person> namedQuery = em.createNamedQuery("Person.findByNameAndPassword", com.kenmcwilliams.employmentsystem.orm.Person.class);
        namedQuery.setParameter("name", userName);
        namedQuery.setParameter("password", password);
        Person person = namedQuery.getSingleResult();
        return new User(person);
    }
    
    
}
