/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.orm.Resume;
import com.kenmcwilliams.employmentsystem.orm.Role;
import com.kenmcwilliams.employmentsystem.service.ResumeService;
import java.util.List;

/**
 *
 * @author ken
 */
public class ResumeServiceImpl implements ResumeService{

    @Override
    public List<Resume> listResumes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Resume getResume(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteResume(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addResume(Resume resume) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateResume(Resume resume) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Role getRole(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addResumeRole(int resumeId, Role role) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteRole(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
