/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Override
    public Integer create(Class clazz, Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
