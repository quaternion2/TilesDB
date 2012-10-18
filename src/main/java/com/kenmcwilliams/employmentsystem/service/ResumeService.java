/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import com.kenmcwilliams.employmentsystem.orm.Position;
import com.kenmcwilliams.employmentsystem.orm.Resume;
import java.util.List;

/**
 *
 * @author ken
 */
public interface ResumeService {
    //list all quals
    List<Resume> listResumes();
    Resume getResume(int id);
    void deleteResume(int id);
    void addResume(Resume resume);
    void updateResume(Resume resume);
    
    Position getRole(int id);
    void updateRole(Position role);
    void addResumeRole(int resumeId, Position role);
    void addRole(Position role);
    void deleteRole(int id);
}
