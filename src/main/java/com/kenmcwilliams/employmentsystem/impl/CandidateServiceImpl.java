/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.orm.Recruiter;
import com.kenmcwilliams.employmentsystem.service.CandidateService;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        Candidate candidate = em.find(com.kenmcwilliams.employmentsystem.orm.Candidate.class, candidateId);
        Recruiter recruiter = em.find(com.kenmcwilliams.employmentsystem.orm.Recruiter.class, candidateId);
        //TODO: create a new log and persist it
    }
    
}
