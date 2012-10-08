/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.util;

import com.kenmcwilliams.employmentsystem.action.crud.ReadAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ken
 */
public class ActionUtils {

    private static final Logger log = Logger.getLogger(ReadAction.class.getName());

    public static Class initClazz(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //sets the model
        //look up enity by name
        String lowerCaseName = name.toLowerCase();
        String[] words = lowerCaseName.split("-");
        String className = "";
        for (int i = 0; i < words.length; i++) {
            //String capitalizeFirst = Character.toUpperCase(lowerCaseName.charAt(0)) + lowerCaseName.substring(1);
            className += Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1);
        }
        
        log.log(Level.INFO, "after setting string to: {0}", name);
        return Class.forName("com.kenmcwilliams.employmentsystem.orm." + className);
        //return clazz.newInstance();
    }
}
