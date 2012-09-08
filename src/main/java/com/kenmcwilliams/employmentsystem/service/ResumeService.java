/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import com.kenmcwilliams.employmentsystem.orm.Resume;
import com.kenmcwilliams.employmentsystem.orm.Role;
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
    
    Role getRole(int id);
    void updateRole(Role role);
    void addResumeRole(int resumeId, Role role);
    void addRole(Role role);
    void deleteRole(int id);
}
