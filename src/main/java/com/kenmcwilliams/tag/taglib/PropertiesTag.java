/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.tag.taglib;

import com.kenmcwilliams.tag.component.Properties;
import com.opensymphony.xwork2.util.ValueStack;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.SetTag;

/**
 *
 * @author ken
 */
public class PropertiesTag extends SetTag {
    //private String pojo; //name of class

    private String mode = "get"; //get, set, both, all to generate list
    private String excludeTypes = null;
    private String excludeProperties = null;
    private String declaredOnly = "false";
    private String blockInheritanceAt = "com.opensymphony.xwork2.ActionSupport";
    //private String omitTypes="";

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Properties(stack);
    }

    //DOES NOT HANDLE QUOTED STRINGS
    private java.util.Set<String> parseListToSet(String slist) {
        java.util.Set<String> set = new HashSet();
        if (slist != null && slist.length() > 0) {
            int length = slist.length();
            if (length > 2) {
                char first = slist.charAt(0);
                char last = slist.charAt(length - 1);
                if (first == '[' && last == ']'
                        || first == '{' && last == '}') {
                    slist = slist.substring(1, length - 1);
                }
            }
        }else{
            slist = "";
        }
        return new HashSet(Arrays.asList(slist.split(",")));
    }

    private java.util.Set<Class> produceTypeList(java.util.Set<String> types) throws ClassNotFoundException {
        java.util.Set<Class> clazzes = new HashSet();
        for (String t : types) {
            clazzes.add(Class.forName(t));
        }
        return clazzes;
    }

    @Override
    protected void populateParams() {
        super.populateParams();
        Properties properties = (Properties) component;
        properties.setMode(mode);
        try {
            properties.setBlockInheritanceAt(Class.forName(this.getBlockInheritanceAt()));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PropertiesTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        properties.setDeclaredOnly(this.getDeclaredOnly().equalsIgnoreCase("false") ? false : true);
        Set<String> list = this.parseListToSet(this.getExcludeTypes());
        Set<Class> excludeTypes;
        try {
            excludeTypes = this.produceTypeList(list);
            properties.setExcludeTypes(excludeTypes);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PropertiesTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        properties.setExcludeProperties(this.parseListToSet(this.getExcludeProperties()));
    }

    /**
     * @return the accessFilter
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param accessFilter the accessFilter to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the excludeTypes
     */
    public String getExcludeTypes() {
        return excludeTypes;
    }

    /**
     * @param excludeTypes the excludeTypes to set
     */
    public void setExcludeTypes(String excludeTypes) {
        this.excludeTypes = excludeTypes;
    }

    /**
     * @return the excludeProperties
     */
    public String getExcludeProperties() {
        return excludeProperties;
    }

    /**
     * @param excludeProperties the excludeProperties to set
     */
    public void setExcludeProperties(String excludeProperties) {
        this.excludeProperties = excludeProperties;
    }

    /**
     * @return the declaredOnly
     */
    public String getDeclaredOnly() {
        return declaredOnly;
    }

    /**
     * @param declaredOnly the declaredOnly to set
     */
    public void setDeclaredOnly(String declaredOnly) {
        this.declaredOnly = declaredOnly;
    }

    /**
     * @return the blockInheritanceAt
     */
    public String getBlockInheritanceAt() {
        return blockInheritanceAt;
    }

    /**
     * @param blockInheritanceAt the blockInheritanceAt to set
     */
    public void setBlockInheritanceAt(String blockInheritanceAt) {
        this.blockInheritanceAt = blockInheritanceAt;
    }
}
