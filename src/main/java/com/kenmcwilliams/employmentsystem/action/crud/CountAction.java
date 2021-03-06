/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
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
public class CountAction extends ActionSupport {

    @Autowired
    private CrudService crudService;
    private String entityName; //entity name
    private Object jsonModel;
    private Long count;
    
    @Override
    public String execute() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Class clazz = ActionUtils.initClazz(getEntityName());
        jsonModel = crudService.count(clazz);
        return SUCCESS;
    }

    /**
     * @return the name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param name the name to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * @return the count
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Long count) {
        this.count = count;
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
}
