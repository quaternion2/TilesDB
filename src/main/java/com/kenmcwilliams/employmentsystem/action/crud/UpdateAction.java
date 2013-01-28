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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanMap;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Algorithm:
 *  1) {entityName} is applied to the action
 *  2) Prepare changes the {entityName} into an entity object and sets ModelDrivens model
 *  3) Parameters are passed in and type conversion does what we expect
 *  4) The ParameterAware interface has trapped the parameter list,
 *      we will iterate over that list and extract that parameters from 
 *      the entity class and save them in "updateParams" with the parameter 
 *      name as the key and the entity value as the value of the hash
 *  5) The type of the entity class and the updateParams are passed to the service layer
 *      to update the entity
 *  6) If no exception is thrown return "success" otherwise return "error" indicating
 *      the update failed.
 * @author ken
 */
@ParentPackage("staticParams-prepare-parms")
@Namespace("/crud/{entityName}")
@Result(type = "kjson")
//THIS IS WRONG I need to apply the types to the entity dirrectly to get type conversion
//Then I extract those properties into a map and sent THAT map to the service
public class UpdateAction extends ActionSupport implements Preparable, ParameterAware, ModelDriven<Object> {
    private static final Logger log = Logger.getLogger(UpdateAction.class.getName());
    @Autowired
    private CrudService crudService;
    private String entityName; //entity name
    private Object jsonModel; //for result only
    private Object entityModel; //temp object used to ease type conversion
    private Class clazz;
    private Long id;
    private HashMap<String, Object> updateParams = new HashMap(); //for sercice layer
    private Map<String, String[]> parameters = new HashMap();
    
    @Override
    public String execute() throws Exception{
        //copy properties in entityModel to updateParams
        Collection<String> keys = parameters.keySet();
        BeanMap beanMap = new BeanMap(getEntityModel());
        for(String key : keys){
            updateParams.put(key, beanMap.get(key));
        }
        crudService.update(clazz, updateParams);
        return SUCCESS;
    }
    
    @Override
    public Object getModel() {
        return getEntityModel();
    }

    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
        //debug
        /**Set<String> keySet = parameters.keySet();
        //Found entityName is part of the dataset! But has not values
        
         * for(String key : keySet){
            Collection<String[]> values = parameters.values();
            System.out.println("key: " + key);
            for (String[] value : values){
                System.out.print(" values: ");
                for(String string : value){
                    System.out.print(string + " ");
                }
                System.out.println();
            }
            System.out.println();
        }**/
    }

    @Override
    public void prepare() throws Exception {
        log.log(Level.INFO, "In prepare entityName is set with {0}", getEntityName());
        clazz = ActionUtils.initClazz(getEntityName());
        setEntityModel(clazz.newInstance());
    }

    /**
     * @return the jsonModel
     */
    public Object getJsonModel() {
        return jsonModel;
    }

    /**
     * @param entityModel the entityModel to set
     */
    public void setEntityModel(Object entityModel) {
        this.entityModel = entityModel;
    }

    /**
     * @return the entityModel
     */
    public Object getEntityModel() {
        return entityModel;
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
