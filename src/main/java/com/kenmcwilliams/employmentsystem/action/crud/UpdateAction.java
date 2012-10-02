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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ParentPackage("staticParams-prepare-parms") //should probably use: @ParentPackage("staticParams-prepare-parms")
@Namespace("/crud/{entityName}")
@Result(type = "kjson")
//THIS IS WRONG I need to apply the types to the entity dirrectly to get type conversion
//Then I extract those properties into a map and sent THAT map to the service
public class UpdateAction extends ActionSupport implements Preparable, ParameterAware, ModelDriven {
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
    public String execute(){
        //read methods from entitModel which are in the parameters keys
        
        crudService.update(clazz, updateParams);
        return SUCCESS;
    }
    
    @Override
    public Object getModel() {
        return entityModel;
    }

    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
        //debug
        Set<String> keySet = parameters.keySet();
        for(String key : keySet){
            System.out.print("key: " + key + " values: ");
            for (String value : parameters.get(key)){
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void prepare() throws Exception {
        log.log(Level.INFO, "In prepare entityName is set with {0}", entityName);
        clazz = ActionUtils.initClazz(entityName);
        entityModel = clazz.newInstance();
    }
}
