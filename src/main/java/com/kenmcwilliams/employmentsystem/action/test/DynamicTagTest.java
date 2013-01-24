/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author ken
 */
public class DynamicTagTest extends ActionSupport{
    private String name = "Bob";
    
    public String getName(){
        return name;
    }
}
