/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
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
@Namespace("/crud/{entityName}")
@Result(type = "kjson") //TODO: could rid of this line by setting the result as the default for the package
public class Describe extends ActionSupport implements Preparable{
    private static final Logger log = Logger.getLogger(Describe.class.getName());
    @Autowired
    private CrudService crudService;
    private String entityName;
    //private Object entityModel;
    private Map jsonModel;// = new HashMap(); //for output, return the newly created object
    private Class clazz;

    @Override
    public String execute() throws IllegalAccessException, InstantiationException {
        Map describe = crudService.describe(clazz);
        jsonModel = describe;
        log.log(Level.INFO, "jsonModel:{0}", getJsonModel());
        log.info("Action describe returning 'success'");
        return SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        log.log(Level.INFO, "In prepare entityName is set with {0}", getEntityName());
        clazz = ActionUtils.initClazz(getEntityName());
        //entityModel = clazz.newInstance();
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
    public Map getJsonModel() {
        return jsonModel;
    }
}
