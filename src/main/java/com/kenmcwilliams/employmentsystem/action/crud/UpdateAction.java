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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@ParentPackage("staticParams-prepare-parms")
@Namespace("/crud/{entityName}/{id}")
@Result(type = "kjson")
//THIS IS WRONG I need to apply the types to the entity dirrectly to get type conversion
//Then I extract those properties into a map and sent THAT map to the service
public class UpdateAction extends ActionSupport implements Preparable, ModelDriven<Object> {

    private static final Logger log = Logger.getLogger(UpdateAction.class.getName());
    @Autowired
    private CrudService crudService;
    private String entityName; //entity name
    private Map jsonModel = new HashMap(); //for result only
    private Object model; //temp object used to ease type conversion
    private Class clazz;
    private Long id;

    @Override
    public String execute() throws Exception {
        String status = SUCCESS;
        String message = "";
        try{
        log.info("Entered execute(): /crud/{entityName}/{id}/UpdateAction");
        crudService.update(model);
        }catch (Exception e){
            status = ERROR; 
            message = e.getMessage();
        }
        jsonModel.put("status", status);
        if (status.equals(ERROR)){
            jsonModel.put("message", message);
        }
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return this.model;
    }
    
    @Override
    public void prepare() throws Exception {
        log.log(Level.INFO, "In prepare entityName is set with {0}", getEntityName());
        clazz = ActionUtils.initClazz(getEntityName());
        log.log(Level.INFO, "Setting Model with {0}", clazz.toString());
        //setEntityModel(clazz.newInstance());
        model = crudService.read(clazz, id.intValue());
        
    }

    /**
     * @return the jsonModel
     */
    public Object getJsonModel() {
        return jsonModel;
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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
