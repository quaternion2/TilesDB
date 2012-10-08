/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
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
@Result(type = "kjson") //TODO: I can get rid of this line by setting the result as the defaul for the package
public class AddAction extends ActionSupport implements Preparable, ModelDriven {

    private static final Logger log = Logger.getLogger(AddAction.class.getName());
    @Autowired
    private CrudService crudService;
    private String entityName; //entity name
    private Object entityModel; // for input
    private Object jsonModel; //for output, return the newly created object
    //private Long count;
    //private Long id;
    private Class clazz;

    @Override
    public String execute() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        log.log(Level.INFO, "In execute entityName is set with {0}", entityName);
        //Not sure what happens if an id is passed in

        jsonModel = crudService.create(clazz, entityModel);
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

    /**
     * @param jsonModel the jsonModel to set
     */
    public void setJsonModel(Object jsonModel) {
        this.jsonModel = jsonModel;
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
}
