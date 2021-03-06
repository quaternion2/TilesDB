/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.orm.ActionValidateable;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Adds a new record to the database
 * 
 * @author ken
 */
@ParentPackage("staticParams-prepare-parms")
@Namespace("/crud/{entityName}")
@Result(type = "kjson") //TODO: could rid of this line by setting the result as the default for the package
public class AddAction extends ActionSupport implements Preparable, ModelDriven<Object> {

    private static final Logger log = Logger.getLogger(AddAction.class.getName());
    @Autowired
    private CrudService crudService;
    private String entityName; 
    private Object entityModel; 
    private Map jsonModel = new HashMap(); //for output, return the newly created object
    private Class clazz;

    @Override
    public String execute() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        log.log(Level.INFO, "In execute entityName is set with {0}", entityName);
        //If an id is passed in it will merge the object with that id, null will be used for unset attributes
        String status = SUCCESS;
        boolean error = false;
        Object entity = null;
        try { 
            entity = crudService.create(entityModel);
        } catch (Exception e) {
            error = true;
            status = ERROR;
            jsonModel.put("message", e.getMessage());
        }
        if (error == false) {
            jsonModel.put("entity", entity);
        }
        jsonModel.put("status", status);
        return SUCCESS;
    }

    /**
     * @return the entityModel
     */
    public Object getEntityModel() {
        return entityModel;
    }

    /**
     * @param entityModel the entityModel to set
     */
    public void setEntityModel(Object entityModel) {
        this.entityModel = entityModel;
    }

    /**
     * @return the jsonModel
     */
    public Object getJsonModel() {
        return jsonModel;
    }

    //@Override
    @Override
    public Object getModel() {
        return this.entityModel;
    }

    @Override
    public void prepare() throws Exception {
        log.log(Level.INFO, "In prepare entityName is set with {0}", entityName);
        clazz = ActionUtils.initClazz(entityName);
        entityModel = clazz.newInstance();
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    
    //TODO: validation would be a good idea can't implement in this class need to delegate
    //if entity implements a validate method, this validate should
    //call that validate
    @Override
    public void validate(){
        if (entityModel instanceof ActionValidateable){
            ((ActionValidateable)entityModel).validate();
        }
    }
}
