/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.kenmcwilliams.employmentsystem.action.crud.ReadAction;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author ken
 */
@ParentPackage("json-default")
@Namespace("/test/{Name}")
//@Result(location="/WEB-INF/content/test/named-test.jsp")
@Result(type = "json")
public class NamedTestAction extends ActionSupport{
    private static final Logger log = Logger.getLogger(ReadAction.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    private String name;

    @Override
    public String execute(){
        System.out.println("NamedTestAction value of name: " + name);
        return SUCCESS;
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
}
