/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.service.EntityInspectorService;
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
public class EntityInspectorImpl implements EntityInspectorService {

    private static final Logger log = Logger.getLogger(EntityInspectorService.class.getName());
    private Set<Class> entityTypes;
    private Map<Class, Collection<String>> unmodifiablePropertiesByEntity = new HashMap<>();

    public EntityInspectorImpl(String packageName) {
        Reflections reflections = new Reflections(packageName);
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

    public EntityInspectorImpl() {
        this("com.kenmcwilliams.employmentsystem.orm");
    }

    @Override
    public Collection<String> getUnmodifiablePropertiesByEntity(Class clazz) {
        return this.unmodifiablePropertiesByEntity.get(clazz);
    }

    //TODO: should make this protected and make use of this class to build a buffer.
    public final SortedMap<Integer, String> getOrderedProperties(Class clazz) {
        return MethodOrderDeterminer.getOrderedPropertiesFor(clazz);
    }
}
