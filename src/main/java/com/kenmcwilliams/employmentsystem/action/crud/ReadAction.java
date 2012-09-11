/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@ParentPackage("json-default")
@Namespace("/crud/{Name}")
//@Result(location="/WEB-INF/content/test/named-test.jsp")
@Action(results = {
    @Result(name = "success", type = "json", params = {"root", "model"}),
    @Result(name = "input", type = "json", params = {"root", "fieldErrors"}),})
public class ReadAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(ReadAction.class.getName());
    @Autowired private CrudService crudService;
    private String name;
    private Integer id;
    private Class clazz;
    private Object model;
    private Object entity;

    @Override
    //@Transactional //<-THIS ANNOTATION CAUSES EVERYTHING TO GO TO HELL (named variables don't work)
    public String execute() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        log.info("Read execute");
        log.log(Level.INFO, "param entityName : {0}", name);
        initModel();
        model = crudService.read(clazz, id);
        return SUCCESS;
    }

    public void initModel() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //sets the model
        //look up enity by name
        setName(getName().toLowerCase());
        setName(Character.toUpperCase(getName().charAt(0)) + getName().substring(1));
        log.log(Level.INFO, "after setting string to: {0}", name);
        clazz = Class.forName("com.kenmcwilliams.employmentsystem.orm." + getName());
        entity = clazz.newInstance();
        model = clazz.newInstance();
    }

    //@Override
    //public void prepare() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    //}
    @Override
    public void validate() {
        //replace model with errors
    }

    public Object getModel() {
        throw new UnsupportedOperationException("Not supported yet.");
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

    /**
     * @return the entityName
     */
    public String getName() {
        return name;
    }

    /**
     * @param entityName the entityName to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the crudService
     */
    public CrudService getCrudService() {
        return crudService;
    }

    /**
     * @param crudService the crudService to set
     */
    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }
}
