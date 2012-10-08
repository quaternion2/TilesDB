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
public class PageAction extends ActionSupport {

    @Autowired
    private CrudService crudService;
    private Integer count;
    private Integer start;
    private Object jsonModel;
    private String entityName;
    private Class clazz;

    @Override
    public String execute() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        setClazz(ActionUtils.initClazz(getEntityName()));
        jsonModel = crudService.page(clazz, start, count);
        return SUCCESS;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return the start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Integer start) {
        this.start = start;
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
    //public void setJsonModel(Object jsonModel) {
    //    this.jsonModel = jsonModel;
    //}

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
     * @return the clazz
     */
    public Class getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
