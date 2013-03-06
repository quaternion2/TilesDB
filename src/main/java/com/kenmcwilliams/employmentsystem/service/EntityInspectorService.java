/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import java.util.Collection;

/**
 *
 * @author ken
 */
public interface EntityInspectorService {
    /**
     * @return List of getters in the order they occur in the source file of a particular entity class 
     */
    //SortedMap<Integer, String> getOrderedProperties(Class clazz);
    Collection<String> getUnmodifiablePropertiesByEntity(Class clazz);
}
