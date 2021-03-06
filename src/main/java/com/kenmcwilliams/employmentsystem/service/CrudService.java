/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import java.util.List;
import java.util.Map;

/**
 * TODO: add "critera" objects to implement search and "ordinal" objects
 * to specify order. 
 * 
 * @author ken
 */
public interface CrudService {
    Object create(Object entity); //return the created object from database with id
    List<Object> search(Class clazz, Object entity, Map<String, Map<CriteriaConstraints, List>> constraints);
    Object read(Class clazz, Integer id); //TODO: change to long
    //void update(Class clazz, Map map) throws Exception;\
    void update(Object entity);
    List<Object> page(Class clazz, Integer start, Integer size);//TODO: add, parameters for sortOrder and query
    Long count(Class clazz); //total # records for entity TODO: add criteria object
    public void delete(Class clazz, Integer id);
    public Map describe(Class clazz);
}
