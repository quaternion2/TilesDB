/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ken
 */
@Transactional
public class CrudServiceImpl implements CrudService {

    private static final Logger log = Logger.getLogger(CrudServiceImpl.class.getName());
    @PersistenceContext
    private EntityManager em;
    private int start;

    @Override
    public Object create(Class clazz, Object entity) {
        //TODO: consider use of clazz which is not being used at all
        return em.merge(entity);
    }

    @Override
    public Object read(Class clazz, Integer id) {
        log.log(Level.INFO, "clazz name: {0} id: {1}", new Object[]{clazz.getName(), id});
        Object entity = em.find(clazz, id);
        Object entity2 = em.find(com.kenmcwilliams.employmentsystem.orm.Qual.class, 3);
        //em.detach(entity);
        if (entity == null) {
            log.warning("em.find v1 returned null object");
        }
        if (entity2 == null) {
            log.warning("em.find v2 returned null object");
        }
        log.info("");
        em.detach(entity);
        return entity;
    }

    @Override
    public void update(Class clazz, Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Class clazz, Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> page(Class clazz, Integer start, Integer size) {
        String simpleClassName = clazz.getSimpleName();
        String queryString = "select o from " + simpleClassName + " o";
        TypedQuery createQuery = em.createQuery(queryString, clazz);
        createQuery.setFirstResult(start);
        createQuery.setMaxResults(size);
        return createQuery.getResultList();
    }

    @Override
    public Long count(Class clazz) {
        String simpleClassName = clazz.getSimpleName();
        TypedQuery query = em.createQuery("select count(o) from " + simpleClassName + " o", Long.class);
        return (Long) query.getSingleResult();
    }
}
