/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.old.qual;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.service.QualService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author ken
 */
//@ParentPackage("json-default")
//@Result(type = "json", params = {
//    "excludeProperties",
//    "^quals\\[\\d+\\]\\.qualLineCollection"})
public class ListQuals extends ActionSupport{
    private static final Logger log = Logger.getLogger(ListQuals.class.getName());
    @Autowired QualService qualService;
    private List<Qual> quals;
    
    @Override
    public String execute(){
        quals = qualService.listQuals();
        //PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(quals.get(0));
        BeanMap qualMap = new BeanMap(quals.get(0));
        Set keySet = qualMap.keySet();
        for(Object key: keySet){
            System.out.println(key.toString());
        }
        //log.info(ReflectionToStringBuilder.toString(quals.get(0)));
        return SUCCESS;
    }
    
    public List<Qual> getQuals(){
        return quals;
    }
}
