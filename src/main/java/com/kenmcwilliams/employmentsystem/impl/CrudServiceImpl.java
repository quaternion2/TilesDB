/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.service.CriteriaConstraints;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
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
    /**
     * Parameters: clazz: Entity type to search for, remove this later and use
     * the class of the entity parameter entity: Entity populated with required
     * parameters constrains: contraints applied against the entity fields
     */
    public List<Object> search(Class clazz, Object entity, Map<String, Map<CriteriaConstraints, List>> constraints) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(clazz);
        Root root = cq.from(clazz);
        ArrayList<Predicate> prdcts = new ArrayList();
        for (String field : constraints.keySet()) {
            Map<CriteriaConstraints, List> fieldConstraints = constraints.get(field);
            Path fieldPath = root.get(field);
            for (CriteriaConstraints constraint : fieldConstraints.keySet()) {
                List values = fieldConstraints.get(constraint);
                Predicate predicate = null;
                if (constraint == CriteriaConstraints.Between) {
                    predicate = cb.between(fieldPath, (Comparable) values.get(0), (Comparable) values.get(1));
                } else if (constraint == CriteriaConstraints.Eq) {
                    predicate = cb.equal(fieldPath, values.get(0));
                } else if (constraint == CriteriaConstraints.Gt) {
                    predicate = cb.gt(fieldPath, (Number) values.get(0));
                } else if (constraint == CriteriaConstraints.GtOrEq) {
                    predicate = cb.ge(fieldPath, (Number) values.get(0));
                } else if (constraint == CriteriaConstraints.In) {
                    predicate = fieldPath.in(values);//ge(fieldPath, (Number)values.get(0));
                } else if (constraint == CriteriaConstraints.IsEmpty) {
                    predicate = cb.isEmpty(root);
                } else if (constraint == CriteriaConstraints.IsNotEmpty) {
                    predicate = cb.isNotEmpty(root);
                } else if (constraint == CriteriaConstraints.IsNotNull) {
                    predicate = cb.isNotNull(root);
                } else if (constraint == CriteriaConstraints.IsNull) {
                    predicate = cb.isNull(root);
                } else if (constraint == CriteriaConstraints.Like) {
                    predicate = cb.like(fieldPath, (String)values.get(0));
                } else if (constraint == CriteriaConstraints.Lt) {
                    predicate = cb.ge(fieldPath, (Number) values.get(0));
                } else if (constraint == CriteriaConstraints.LtOrEq) {
                    predicate = cb.ge(fieldPath, (Number) values.get(0));
                } else if (constraint == CriteriaConstraints.NotEq) {
                    predicate = cb.ge(fieldPath, (Number) values.get(0));
                } else if (constraint == CriteriaConstraints.NotLike) {
                    predicate = cb.like(fieldPath, "*" + values.get(0) + "*");
                } else {
                    throw new Error("Sanity Test : Unreachable block 111");
                }
                prdcts.add(predicate);
            }
        }
        //Predicate[] prdcts = new ArrayList()
        if (prdcts.size() > 0) {
            Predicate [] predicate =  new Predicate[prdcts.size()];
            predicate = prdcts.toArray(predicate);
            cq.where(cb.and(predicate));
        }
        TypedQuery query = em.createQuery(cq);
        return query.getResultList();
    }
}
