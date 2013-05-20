/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.orm.CandidateLog;
import com.kenmcwilliams.employmentsystem.orm.Recruiter;
import com.kenmcwilliams.employmentsystem.service.CandidateService;
import java.util.Date;
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
public class CandidateServiceImpl implements CandidateService{
    private static final Logger log = Logger.getLogger(CandidateServiceImpl.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public void addLog(Integer candidateId, Integer recruiterId, String entry) {
        log.log(Level.INFO, "candidateId: {0} recruiterId: {1} entry: {2}", new Object[]{candidateId, recruiterId, entry});
        Candidate candidate = em.find(com.kenmcwilliams.employmentsystem.orm.Candidate.class, candidateId);
        Recruiter recruiter = em.find(com.kenmcwilliams.employmentsystem.orm.Recruiter.class, recruiterId);
        CandidateLog candidateLog = new CandidateLog();
        candidateLog.setCandidateId(candidate);
        candidateLog.setRecruiterId(recruiter);
        candidateLog.setNote(entry);
        candidateLog.setStamp(new Date());
        em.persist(candidateLog);
    }

    //TODO: Consider getting the details from the candidates association...
    @Override
    public List<CandidateLog> getLogs(Integer candidateId) {
        //Candidate canidate = em.getReference(com.kenmcwilliams.employmentsystem.orm.Candidate.class, candidateId);
        
        TypedQuery<CandidateLog> namedQuery = em.createNamedQuery("CandidateLog.findByCandiateId", com.kenmcwilliams.employmentsystem.orm.CandidateLog.class);
        namedQuery.setParameter("candidateId", candidateId);
        return namedQuery.getResultList();
    } 
}
