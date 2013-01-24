/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.tag.component;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Purpose: Determines getter/setters for a Class without needing an instance.
 *
 * @author ken
 */
public class ClassBeanUtils {
    //TODO: Use getDeclaredMethods() to reduce method size
    //public List<Method> getBaseClassMethods(Class clazz) {
    //    return Arrays.asList(clazz.getDeclaredMethods());
    //}
    public Set<Class> getInterfaces(Class clazz){
        Class[] classes = clazz.getClasses();
        Set<Class> interfaces = new HashSet();
        for(Class c : classes){
            if(c.isInterface()){
                interfaces.add(c);
            }
        }
        return interfaces;
    }
    
    //Probably not worth using...
    public Set<Method> getMethodDifference(Set<Method> minuend, Set<Method> subtrahend){
        minuend.removeAll(subtrahend);
        return minuend;
    }
    
    //Subtract types
    public Set<Class> getClassDifference(Set<Class> minuend, Set<Class> subtrahend){
        HashMap<String, Class> stringsOfClasses = new HashMap();
        for(Class m : minuend){
            stringsOfClasses.put(m.getName(), m);
        }
        for(Class s : subtrahend){
            stringsOfClasses.remove(s.getName());
        }
        return new HashSet(stringsOfClasses.values());
    }

    //Returns the set sum of methods from types        
    public Set<Method> getMethodsFormTypes(Collection<Class> clazzes) {
        Set<Method> methods = new HashSet();
        for (Class c : clazzes) {
            methods.addAll(Arrays.asList(c.getMethods()));
        }
        return methods;
    }

    public Set<Method> onlyGetters(Collection<Method> methods) {
        Set<Method> getterMethods = new HashSet();
        for (Method m : methods) {
            String name = m.getName();
            if (m.getParameterTypes().length != 0) {
                continue; //must have no parameters
            }
            //must start with "get"
            if (!name.startsWith("get")) {
                continue;
            }
            //must not return void
            if (m.getReturnType().equals(Void.TYPE)) {
                continue; //getter must return a value
            }
            getterMethods.add(m);
        }
        return getterMethods;
    }

    public Set<Method> onlySetters(Collection<Method> methods) {
        Set<Method> setterMethods = new HashSet();
        for (Method m : methods) {
            String name = m.getName();
            //must start with "set"
            if (!name.startsWith("set")) {
                continue;
            }
            //must return void
            if (!m.getReturnType().equals(Void.TYPE)) {
                continue; //returns some value
            }
            //must take at least one parameter
            if (m.getParameterTypes().length == 0) {
                continue;
            }
            setterMethods.add(m);
        }
        return setterMethods;
    }

    public HashSet<Method> decendUntilType(Class start, Class end) {
        HashSet<Method> methods = new HashSet();
        if (start == null || start.getName().equals(end.getName())) {
            return methods;
        } else {
            List<Method> list = Arrays.asList(start.getDeclaredMethods());
            methods.addAll(list);
            //TODO: consider going up the enclosing classes?
            //I don't think this is desireable in most cases
            methods.addAll(decendUntilType(start.getSuperclass(), end));
            return methods;
        }
    }
}
