/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.mgmt.MutableTilesContainer;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;

/**
 *
 * @author ken
 */
@Result(type = "tiles", location = "dynamic-tiles-definition")
public class DynamicTilesDefinition extends ActionSupport {

    @Override
    public String execute() throws Exception {
        ServletContext context = ServletActionContext.getServletContext();
        ApplicationContext applicationContext = ServletUtil.getApplicationContext(context);
        TilesContainer container = TilesAccess.getContainer(applicationContext);
        if (container instanceof MutableTilesContainer) {
        } else {
            throw new Exception("A Mutable Tiles Container is required [TODO: Write example web.xml code example]");
        }
        MutableTilesContainer mc = (MutableTilesContainer) container;
        Definition def = new Definition();
        Attribute templateAttribute = Attribute.createTemplateAttribute("/WEB-INF/test/test-template.jsp");
        def.setTemplateAttribute(templateAttribute);
        def.putAttribute("body", new Attribute("/WEB-INF/test/test-dynamic-definition.jsp"));
        def.setName("dynamic-tiles-definition");

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletRequest servletRequest = new ServletRequest(applicationContext, request, response);

        //def values
        //def.setTemplateAttribute(Attribute); //Attribute.createTemplateAttribute(String)
        //def.setName(String);
        //def.setExtends(String);
        //def.setPreparer(String);
        //def.addAll(Map<String, Attribute>);
        //def.addMissing(Map<String, Attribute>);



        mc.register(def, servletRequest);
        return SUCCESS;
    }
}
