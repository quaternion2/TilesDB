/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.kenmcwilliams.employmentsystem.action.crud.ReadAction;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ken
 */
@ParentPackage("json-default")
@Namespace("/test/{Name}/{id}")
//@Result(location="/WEB-INF/content/test/named-test.jsp")
@Result(type = "json")
public class NamedTestAction extends ActionSupport {

    private static final Logger log = Logger.getLogger(ReadAction.class.getName());
    @PersistenceContext
    private EntityManager em;
    private String name;
    private Object model;
    private Integer id;

    @Override
    public String execute() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        System.out.println("NamedTestAction value of name: " + name);
        log.info("Read execute");
        log.log(Level.INFO, "param entityName : {0}", name);
        //doSomething();
        //look up particular entity type by id

        return SUCCESS;
    }
    
    //@Transactional
    public void doSomething() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //sets the model
        //look up enity by name
        setName(getName().toLowerCase());
        setName(Character.toUpperCase(getName().charAt(0)) + getName());
        log.log(Level.INFO, "after setting string to: {0}", name);
        Class clazz = Class.forName("org.kenmcwilliams.employmentsystem.orm." + getName());
        model = clazz.newInstance();
        model = em.find(clazz, getId());
        em.detach(model);
        log.info(model.toString());
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
