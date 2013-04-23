/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import com.kenmcwilliams.employmentsystem.orm.CandidateLog;
import java.util.List;

/**
 *
 * @author ken
 */
public interface CandidateService {
    void addLog(Integer candidateId, Integer recruiterId, String entry);
    List<CandidateLog> getLogs(Integer candidateId);
}
