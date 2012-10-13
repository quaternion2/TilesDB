/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Set;
import javax.persistence.Entity;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.reflections.Reflections;

/**
 * Lists all the tables currently in com.kenmcwilliams.employmentsystem
 *
 * @author ken
 */
@ParentPackage("package-kjson")
@Namespace("/crud")
@Result(type = "kjson")
public class ListTablesAction extends ActionSupport {
    Reflections reflections = new Reflections("com.kenmcwilliams.employmentsystem.orm");
    private Object jsonModel;
    
    @Override
    public String execute() {
        System.out.println("In list-tables");
        Set<?> temp =
                reflections.getTypesAnnotatedWith(Entity.class);
        Set<Class> subTypes = (Set<Class>) temp;
        //Set<Class> subTypes = temp;
        for(Class c : subTypes){
            System.out.println(c);
        }
        jsonModel = ActionUtils.convertClazzList("com.kenmcwilliams.employmentsystem.orm", subTypes);
        return SUCCESS;
    }

    /**
     * @return the jsonModel
     */
    public Object getJsonModel() {
        return jsonModel;
    }
}
