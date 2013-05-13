/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.util;

import com.kenmcwilliams.employmentsystem.action.crud.ReadAction;
import java.lang.reflect.Field;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.*;

/**
 *
 * @author ken
 */
public class MethodOrderDeterminer {

    private static final Logger log = Logger.getLogger(MethodOrderDeterminer.class.getName());

    public static SortedMap<Integer, String> getOrderedPropertiesFor(Class clazz) {
        TreeMap<Integer, String> properties = new TreeMap<>(); //In key sorted order
        //properties.
        //ClassPool pool = ClassPool.getDefault(); //old
        ClassPool pool = new ClassPool(); //.getDefault();
        pool.insertClassPath(new ClassClassPath(clazz));
        String canonicalName = clazz.getCanonicalName();
        //System.out.println("Looking up class for: " + canonicalName);
        CtClass cc;
        try {
            cc = pool.get(canonicalName);
            Field[] declaredFields = clazz.getDeclaredFields(); //TODO: Only gets declared fields, should be able to get other fields...
            log.log(Level.INFO, "# declared fields: {0}", declaredFields.length);
            for (Field field : declaredFields) {
                String name = field.getName();
                //System.out.println(name);
                //if (name.startsWith("get")) { //only find getters
                String strMethod = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
                //System.out.println("strMethod: " + strMethod);
                CtMethod methodX;
                try {
                    methodX = cc.getDeclaredMethod(strMethod);
                    int xlineNumber = methodX.getMethodInfo().getLineNumber(0);
                    log.log(Level.INFO, "xlineNumber: {0} name: {1}", new Object[]{xlineNumber, name});
                    properties.put(xlineNumber, name);
                } catch (NotFoundException ex) {
                    //TODO: Put this exception back, after tracking down the "getSerialVersionUID" exceptions
                    Logger.getLogger(MethodOrderDeterminer.class.getName()).log(Level.SEVERE, null, ex);
                }

                //}
            }
        } catch (javassist.NotFoundException ex) {
            Logger.getLogger(MethodOrderDeterminer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TODO: write unit test to test against expected values of current entities and an empty object
        return properties;
    }
}
