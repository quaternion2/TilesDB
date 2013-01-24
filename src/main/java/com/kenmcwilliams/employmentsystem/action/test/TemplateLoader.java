/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.kenmcwilliams.tiles.result.StrutsTilesConventionsTemplateLoader;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.tiles.Attribute;

/**
 * Test for: StrutsTilesConventionsTemplateLoader
 * @author ken
 */
@ParentPackage("package-kjson")
@Result(type="kjson")
public class TemplateLoader extends ActionSupport{
    @Override
    public String execute(){
        try {
            StrutsTilesConventionsTemplateLoader loader = new StrutsTilesConventionsTemplateLoader();
            //loader.getTemplate(namespace);
            //Map<String, Attribute> templates = loader.getTemplates();
        } catch (IOException ex) {
            Logger.getLogger(TemplateLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }
}
