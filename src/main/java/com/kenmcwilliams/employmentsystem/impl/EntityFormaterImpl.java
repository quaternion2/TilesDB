/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.service.EntityFormaterService;
import com.kenmcwilliams.employmentsystem.util.MethodOrderDeterminer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import javax.persistence.Entity;
import org.reflections.Reflections;

/**
 *
 * @author ken
 */
public class EntityFormaterImpl implements EntityFormaterService {

    private static final Logger log = Logger.getLogger(EntityFormaterService.class.getName());
    private Set<Class> entityTypes;
    private Map<Class, Collection<String>> unmodifiablePropertiesByEntity = new HashMap<>();

    public EntityFormaterImpl() {
        //TODO: allow this class to be supplied with a different package, or multiple packages
        Reflections reflections = new Reflections("com.kenmcwilliams.employmentsystem.orm");
        Set<?> temp =
                reflections.getTypesAnnotatedWith(Entity.class);
        entityTypes = (Set<Class>) temp;
        for (Class entity : entityTypes) {
            log.log(Level.INFO, "Adding entity {0} to entityTypes", entity.getCanonicalName());
            SortedMap<Integer, String> orderedProperties = this.getOrderedProperties(entity);
            unmodifiablePropertiesByEntity.put(entity, orderedProperties.values());
        }
        unmodifiablePropertiesByEntity = Collections.unmodifiableMap(unmodifiablePropertiesByEntity);
    }

    @Override
    public Collection<String> getUnmodifiablePropertiesByEntity(Class clazz) {
        return this.unmodifiablePropertiesByEntity.get(clazz);
    }

    //TODO: should make this protected and make use of this class to build a buffer.
    public final SortedMap<Integer, String> getOrderedProperties(Class clazz) {
        //TreeMap sortedMap = null;
        //sortedMap = (TreeMap) 
        //Now change the getters into regular properties (remove the "get" and lower case the first remaining letter)
        //Collection values = sortedMap.values();
        //Set<Integer> keySet = sortedMap.keySet();
        //for (Integer key : keySet) {
            //TODO: check bounds to amke sure I can access index 3 and then 4
            //StringBuilder strBuilder = new StringBuilder();
        //    String value = (String) sortedMap.get(key);
            //strBuilder.append(Character.toLowerCase(value.charAt(3)));
            //strBuilder.append(value.substring(4));
            //String strMethod = strBuilder.toString();
        //    sortedMap.put(key, value);
        //    log.log(Level.INFO, "\tAdding key: {0} for value: {1}", new Object[]{key, strMethod});
        //}
        return MethodOrderDeterminer.getOrderedPropertiesFor(clazz);
    }
}
