/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.service.CriteriaConstraints;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.commons.beanutils.BeanUtils;
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
        //TODO: add check to test that entity is a clazz or derived from clazz
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
    public void update(Class clazz, Map map) throws Exception {
        Integer id = Integer.decode((String) map.get("id"));//should be checked before it gets here
        Object found = em.find(clazz, id);
        BeanUtils.populate(found, map);
        em.persist(found);
    }

    @Override
    public void delete(Class clazz, Integer id) {
        //String simpleClassName = clazz.getSimpleName();
        Object found = em.getReference(clazz, id);
        em.remove(found);
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

    @Override
    public List<Object> search(Class clazz, Object entity, Map<String, Map<CriteriaConstraints, List>> constraints) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(clazz);
        //cq.
        //
        //return new ArrayList();//just to get rid of compiler error
        throw new Error();//NOT IMPLEMENTED YET
    }
}
