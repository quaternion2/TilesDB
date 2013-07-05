/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import com.kenmcwilliams.employmentsystem.orm.Apc;

/**
 *
 * @author ken
 */
public interface CheckAPCService {
    public void checkAPC();
    public Apc getLatestOpportuntiy();
}
