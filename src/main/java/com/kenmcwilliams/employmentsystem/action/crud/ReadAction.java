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
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
@ParentPackage("package-kjson")
@Namespace("/crud/{entityName}")
@Result(type="kjson")
public class ReadAction extends ActionSupport implements Preparable, RequestAware{

    private static final Logger log = Logger.getLogger(ReadAction.class.getName());
    @Autowired
    private CrudService crudService;
    private String entityName;
    private Integer id;
    private Class clazz;
    private Object jsonModel;
    private Object entity;
    private String excludeProperties = "qualLineCollection";
    private Map<String, Object> request;
    //private ArrayList<String> excludeProperties = new ArrayList<String>();

    @Override
    public void prepare() throws Exception {
        //excludeProperties.add("qualLineCollection");
    }
    
    public String getExcludeProperties(){
        return excludeProperties;
    }

    @Override
    public String execute() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //request.put("excludeProperties", "qualLineCollection")
        log.info("Read execute");
        log.log(Level.INFO, "param entityName : {0}", entityName);
        clazz = ActionUtils.initClazz(entityName);
        //Map modelDescription;
        jsonModel = crudService.read(clazz, id);
        return SUCCESS;
    }

    //TODO: extract this into util method
    public void initClazz() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //sets the model
        //look up enity by name
        setEntityName(getEntityName().toLowerCase());
        setEntityName(Character.toUpperCase(getEntityName().charAt(0)) + getEntityName().substring(1));
        log.log(Level.INFO, "after setting string to: {0}", entityName);
        clazz = Class.forName("com.kenmcwilliams.employmentsystem.orm." + getEntityName());
        //entity = clazz.newInstance();
        //jsonModel = clazz.newInstance();
    }

    //@Override
    //public void prepare() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    //}
    @Override
    public void validate() {
        //replace model with errors
    }

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

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }
}
