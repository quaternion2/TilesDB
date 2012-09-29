/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import java.util.List;

/**
 * TODO: add "critera" objects to implement search and "ordinal" objects
 * to specify order. 
 * 
 * @author ken
 */
public interface CrudService {
    Integer create(Class clazz, Object entity);
    Object read(Class clazz, Integer id);
    void update(Class clazz, Object entity);
    void delete(Class clazz, Integer id);
    List<Object> page(Class clazz, Integer start, Integer size);//TODO: add, parameters for sortOrder and query
    Long count(Class clazz); //total # records for entity TODO: add criteria object
}
