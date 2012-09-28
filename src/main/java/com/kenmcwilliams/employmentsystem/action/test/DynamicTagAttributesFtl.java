/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author ken
 */
public class DynamicTagAttributesFtl extends ActionSupport{
    private HashMap<String, Object> mapFromAction = new HashMap();
    @Override
    public String execute(){
        System.out.println("In DynamicTagAttributesFtl");
        getMapFromAction().put("placeholder", "input");
        getMapFromAction().put("foo", "bar");
        return SUCCESS;
    }

    /**
     * @return the mapFromAction
     */
    public String getEncodedString(){
        StringBuilder sb = new StringBuilder();
        //sb.append("{");
        Set<String> keySet = this.mapFromAction.keySet();
        int size = keySet.size();
        for(String key : keySet){
            size = size - 1;
            sb.append("\"").append(key).append("\":\"").append(this.mapFromAction.get(key)).append("\"");
            if (size > 0){
                sb.append(",");
            }
        }
        //sb.append("}");
        return sb.toString();
    }
    
    public HashMap<String, Object> getMapFromAction() {
        return mapFromAction;
    }

    /**
     * @param mapFromAction the mapFromAction to set
     */
    public void setMapFromAction(HashMap<String, Object> mapFromAction) {
        this.mapFromAction = mapFromAction;
    }
}
