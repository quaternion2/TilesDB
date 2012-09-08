/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.old.qual;

import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ken
 */
public class DeleteQual extends ActionSupport{
    @Autowired QualService qualService;
    private int id;
    
    public void setId(int id){
        this.id = id;
    }
    
    @Override
    public String execute(){
        qualService.deleteQual(id);
        return SUCCESS;
    }
    
}
