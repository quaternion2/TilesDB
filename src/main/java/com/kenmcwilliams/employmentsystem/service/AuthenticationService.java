/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import com.kenmcwilliams.s2.interceptor.User;

/**
 *
 * @author ken
 */
public interface AuthenticationService {
    User login(String userName, String password);
}
