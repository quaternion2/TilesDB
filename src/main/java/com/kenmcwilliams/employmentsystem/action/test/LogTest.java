/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ken
 */
public class LogTest extends ActionSupport{
    @Override
   public String execute(){
       Logger.getLogger(LogTest.class.getName()).log(Level.SEVERE, "Kens Msg");
       return SUCCESS;
   }
}
