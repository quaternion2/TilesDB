/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.util;

import com.kenmcwilliams.employmentsystem.action.crud.ReadAction;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author kenl
 */
//TODO: add unit tests!!
public class ActionUtils {

    private static final Logger log = Logger.getLogger(ReadAction.class.getName());

    public static Class initClazz(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //sets the model
        //look up enity by name
        String simpleName = urlEntityNameToSimpleName(name);
        return Class.forName("com.kenmcwilliams.employmentsystem.orm." + simpleName);
        //return clazz.newInstance();
    }

    public static Set convertClazzList(String trimPackage, Set<Class> clazzes) {
        HashSet<String> returnSet = new HashSet();
        for (Class c : clazzes) {
            String s = c.getSimpleName();
            returnSet.add(simpleNameToUrlEntityName(s));
        }
        return returnSet;
    }

    /*
     * Converts simple java class name to the defualt struts2 conventions action name 
     * TODO: appending "-" is wrong, need to look up struts2 conventions separator character 
     */
    public static String simpleNameToUrlEntityName(String entityName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entityName.length(); i++) {
            Character c = entityName.charAt(i);
            if (Character.isUpperCase(c) && (i > 0)) {
                sb.append("-");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    /*
     * Converts the default conventions action name (has hypens to a java class
     * name)
     */
    public static String urlEntityNameToSimpleName(String urlEntityName) {
        String lowerCaseName = urlEntityName.toLowerCase();
        String[] words = lowerCaseName.split("-");
        String className = "";
        for (int i = 0; i < words.length; i++) {
            //String capitalizeFirst = Character.toUpperCase(lowerCaseName.charAt(0)) + lowerCaseName.substring(1);
            className += Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1);
        }
        //log.log(Level.INFO, "after setting string to: {0}", name);
        return className;
    }
}
