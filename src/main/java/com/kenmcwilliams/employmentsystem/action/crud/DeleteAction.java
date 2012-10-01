/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@ParentPackage("package-kjson")
@Namespace("/crud/{entityName}")
@Result(type = "kjson")
public class DeleteAction extends ActionSupport {

    @Autowired
    private CrudService crudService;
    private String entityName; //entity name
    private Object jsonModel;
    private Class clazz;
    private Long id;

    //TODO: add (or consider adding) validate method
    @Override
    public String execute() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        clazz = ActionUtils.initClazz(entityName);
        crudService.delete(clazz, id);
        return SUCCESS;
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
     * @return the jsonModel
     */
    public Object getJsonModel() {
        return jsonModel;
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
