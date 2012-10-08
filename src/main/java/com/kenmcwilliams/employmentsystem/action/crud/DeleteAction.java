/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
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
    private Integer id;

    //TODO: add (or consider adding) validate method
    @Override
    public String execute() {
        Map<String, String> message = new HashMap();

        try {
            clazz = ActionUtils.initClazz(entityName);
            crudService.delete(clazz, id);
        } catch (Exception e) {
            message.put(ERROR, e.getMessage());
        }
        if (message.size() == 0) { //no error messages
            message.put("status", SUCCESS);
        }
        jsonModel = message;
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
