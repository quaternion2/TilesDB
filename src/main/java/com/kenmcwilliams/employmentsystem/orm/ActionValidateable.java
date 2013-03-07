/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author ken
 */
public interface ActionValidateable {
    public void validate(ActionSupport validateableAction);
}
