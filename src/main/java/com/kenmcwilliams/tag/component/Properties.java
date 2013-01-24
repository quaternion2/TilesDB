/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.tag.component;

/**
 *
 * @author ken
 */
import com.opensymphony.xwork2.util.ValueStack;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.*;
import org.apache.struts2.components.Set;

//TODO: create test caases, this should be easily testable
public class Properties extends Set {

    //private String[] actionSupportProperties = {"texts", "actionErrors",
    //    "errors", "fieldErrors", "errorMessages", "locale", "actionMessages", "class"};
    private String mode; //get, set, both, all to generate list
    private java.util.Set<Class> excludeTypes;
    private java.util.Set<String> excludeProperties;//can't be type method because I don't know if it is get/set or neither
    private boolean declaredOnly = true;
    private Class blockInheritanceAt = null;
    //private java.util.Set<Class> omitTypes; //TODO: filter out interfaces

    protected Object getValueObject(String body) {
        Object o;
        if (value == null) {
            if (body != null && !body.equals("")) {
                o = body;
            } else {
                o = findValue("top");
            }
        } else {
            o = findValue(value);
        }
        return o;
    }

    protected java.util.Set<Method> getSelectedMethods(Class clazz) {
        ClassBeanUtils beanUtil = new ClassBeanUtils();
        java.util.Set<Method> methods = new HashSet();
        if (isDeclaredOnly()) {
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        } else if (getBlockInheritanceAt() != null) {
            methods.addAll(beanUtil.decendUntilType(clazz, getBlockInheritanceAt()));
        } else {
            methods.addAll(Arrays.asList(clazz.getMethods()));
        }
        java.util.Set<Method> selectedMethods;
        switch (getMode()) {
            case "get":
                selectedMethods = beanUtil.onlyGetters(methods);
                break;
            case "set":
                selectedMethods = beanUtil.onlySetters(methods);
                break;
            case "both":
                selectedMethods = beanUtil.onlyGetters(methods);
                selectedMethods.addAll(beanUtil.onlySetters(methods));
                break;
            default://all
                selectedMethods = methods;
        }
        return selectedMethods;
    }

    private List<String> methodsToStrings(Collection<Method> selectedMethods) {
        //TODO: consider use of HashSet as it is more appropriate
        List<String> methodsAsString = new ArrayList();
        for (Method m : selectedMethods) {
            methodsAsString.add(m.getName());
        }
        return methodsAsString;
    }

    @Override
    public boolean end(Writer writer, String body) {
        Class clazz = getValueObject(body).getClass();
        body = "";
        java.util.Set<Method> selectedMethods = getSelectedMethods(clazz);
        //must push what I want to top of value stack,
        List<String> methodsAsStrings = methodsToStrings(selectedMethods);
        System.out.println(methodsAsStrings);
        stack.push(methodsAsStrings);
        boolean end = super.end(writer, body);
        //TODO: pop the value I pushed on top
        stack.pop();
        return end;
    }

    //has var property from parent alread
    public Properties(ValueStack v) {
        super(v);
    }

    /**
     * @return the type
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param type the type to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the excludeTypes
     */
    public java.util.Set<Class> getExcludeTypes() {
        return excludeTypes;
    }

    /**
     * @param excludeTypes the excludeTypes to set
     */
    public void setExcludeTypes(java.util.Set<Class> excludeTypes) {
        this.excludeTypes = excludeTypes;
    }

    /**
     * @return the excludeProperties
     */
    public java.util.Set<String> getExcludeProperties() {
        return excludeProperties;
    }

    /**
     * @param excludeProperties the excludeProperties to set
     */
    public void setExcludeProperties(java.util.Set<String> excludeProperties) {
        this.excludeProperties = excludeProperties;
    }

    /**
     * @return the declaredOnly
     */
    public boolean isDeclaredOnly() {
        return declaredOnly;
    }

    /**
     * @param declaredOnly the declaredOnly to set
     */
    public void setDeclaredOnly(boolean declaredOnly) {
        this.declaredOnly = declaredOnly;
    }

    /**
     * @return the blockInheritanceAt
     */
    public Class getBlockInheritanceAt() {
        return blockInheritanceAt;
    }

    /**
     * @param blockInheritanceAt the blockInheritanceAt to set
     */
    public void setBlockInheritanceAt(Class blockInheritanceAt) {
        this.blockInheritanceAt = blockInheritanceAt;
    }
}
