/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

/**
 *
 * @author ken
 */
public interface CrudService {
    Integer create(Class clazz, Object entity);
    Object read(Class clazz, Integer id);
    void update(Class clazz, Object entity);
    void delete(Class clazz, Integer id);
}
