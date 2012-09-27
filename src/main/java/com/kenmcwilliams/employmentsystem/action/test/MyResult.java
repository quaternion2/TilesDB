/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author ken
 */
@ParentPackage("package-kjson")
@Result(type="kjson")
public class MyResult extends ActionSupport{
    private Object jsonModel;

    @Override
    public String execute(){
        //Map map = new HashMap();
        //map.put("Hello", "World");
        Qual qual = new Qual();
        this.setJsonModel(qual);
        return SUCCESS;
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
