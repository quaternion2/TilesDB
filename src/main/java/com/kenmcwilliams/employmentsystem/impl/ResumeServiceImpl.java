/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.orm.Position;
import com.kenmcwilliams.employmentsystem.orm.Resume;
import com.kenmcwilliams.employmentsystem.service.ResumeService;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.javatuples.Pair;

/**
 *
 * @author ken
 */
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private static final Logger log = Logger.getLogger(ResumeServiceImpl.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Resume> listResumes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pair<Integer, String>> listResumeNamesByCandidate(Integer candidateId) {
        Query query = em.createQuery("select new org.javatuples.Pair(r.id, r.name) from Resume r where r.candidateId.id = :candidateId");
        query.setParameter("candidateId", candidateId);
        return query.getResultList();
    }

    @Override
    public Resume getResume(int id) {
        //TypedQuery<Resume> query = em.createQuery("select r from Resume r join fetch r.positionCollection pc join fetch pc.positionPointCollection ppc where r.id = :id", Resume.class);
        //query.setParameter("id", id);
        //List<Resume> resultList = query.getResultList();
        //Resume resume = null;
        //if (resultList.size() > 0){
        //    resume = resultList.get(0);
        //}
        //return null;
        Resume found = em.find(Resume.class, id);
        return found;
    }

    @Override
    public void deleteResume(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveResume(Resume resume) {
        if (resume.getId() != null) {
            log.info("Merging resume...");
            em.merge(resume);
        } else {
            log.info("Persisting resume...");
            em.persist(resume);
        }
    }

    @Override
    public void updateResume(Resume resume) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position getRole(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateRole(Position role) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addResumeRole(int resumeId, Position role) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addRole(Position position) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteRole(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
