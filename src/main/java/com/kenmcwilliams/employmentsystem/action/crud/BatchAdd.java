/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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
@ParentPackage("staticParams-prepare-json")
@Namespace("/crud/{entityName}")
@Result(type = "kjson") //TODO: could rid of this line by setting the result as the default for the package
public class BatchAdd extends ActionSupport implements ModelDriven<Object> {

    private static final Logger log = Logger.getLogger(BatchAdd.class.getName());
    @Autowired
    private CrudService crudService;
    private String entityName;
    private Class clazz;
    private Object entityModel;

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    //TODO: DUPLICATE CODE ALL OVER THIS PACKAGE, REFACTOR! (into struts2 interceptor)
    public void prepare() throws Exception{
        log.log(Level.INFO, "In prepare entityName is set with {0}", entityName);
        clazz = ActionUtils.initClazz(entityName);
        entityModel = clazz.newInstance();
    }

    @Override
    public Object getModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
