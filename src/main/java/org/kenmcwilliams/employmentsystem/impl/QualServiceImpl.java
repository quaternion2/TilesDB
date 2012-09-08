/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.orm.QualLine;
import com.kenmcwilliams.employmentsystem.service.QualService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ken
 */
@Transactional
public class QualServiceImpl implements QualService {

    private static final Logger log = Logger.getLogger(QualServiceImpl.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Qual> listQuals() {
        TypedQuery<Qual> query = em.createNamedQuery("Qual.findAll", Qual.class);
        return query.getResultList();
    }

    @Override
    public Qual getQual(int id) {
        log.log(Level.INFO, "getQual() with id: {0}", id);
        Qual found = em.find(Qual.class, id);
        found.getQualLineCollection().iterator().hasNext();//force loading
        return found;
    }

    @Override
    public void updateQual(Qual qual) {
        Qual found = em.find(Qual.class, qual.getId());
        log.log(Level.INFO, "updateQual() with id: {0}", found.getId());
        String[] ingnoreProperties = {"id"};
        BeanUtils.copyProperties(qual, found, ingnoreProperties); //TODO: not an issue to copy id, remove this later
    }

    @Override
    public void deleteQual(int id) {
        Qual found = em.find(Qual.class, id);
        em.remove(found);
    }

    @Override
    public void addQual(Qual qual) {
        em.persist(qual);
    }

    @Override
    public QualLine getQualLine(int id) {
        return em.find(QualLine.class, id);
    }

    @Override
    public void updateQualLine(QualLine qualLine) {
        QualLine qualLinefound = em.find(QualLine.class, qualLine.getId());
        String[] ingnoreProperties = {"id", "qualId"};
        BeanUtils.copyProperties(qualLine, qualLinefound, ingnoreProperties); //Ignore properties is because DB does not allow updating of PK
    }

    @Override
    public void addQualLine(int qualId, QualLine qualLine) {
        Qual qual = em.find(Qual.class, qualId);
        //int size = qual.getQualLineCollection().size();

        Long count;
        //if mandatory true, get mandatory true count
        if (qualLine.getMandatory() == true) {
            
            TypedQuery<Long> tq_count = em.createNamedQuery("QualLine.mandatory.count", Long.class);
            count = tq_count.getSingleResult();
        } else {
            TypedQuery<Long> tq_count = em.createNamedQuery("QualLine.desirable.count", Long.class);
            count = tq_count.getSingleResult();
        }
        qualLine.setNumber(count.intValue() + 1);
        qualLine.setQualId(qual);
        em.persist(qualLine);
    }
}
