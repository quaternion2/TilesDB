/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NOT IMPLEMENTED, the idea here is to look up an Entity by id and
 * provide the name of the contained collection along with paging parameters
 * @author ken
 */
@ParentPackage("package-kjson")
@Namespace("/crud/{entityName}")
@Result(type = "kjson")
public class PageEntityCollection extends ActionSupport{
        @Autowired
    private CrudService crudService;
    private Integer count = 20;
    private Integer start = 0;
    private Object jsonModel;
    private String entityName;
    private Class clazz;
}
