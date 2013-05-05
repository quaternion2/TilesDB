/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.tiles.result;

import com.opensymphony.xwork2.ActionInvocation;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
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
public class TilesDefinitionResult extends ServletDispatcherResult {

    private static final Logger log = Logger.getLogger(TilesResult.class.getName());
    private String template;
    private String name = "dynamic-tiles-definition"; //definition name
    private String extendz;
    private String preparer;
    private String attributes;
    private Map<String, Attribute> evaluatedAttributes;
    private String addMissing = "false";

    public TilesDefinitionResult() {
        super();
    }

    public TilesDefinitionResult(String location) {
        super(location);
    }

    //location in this case refers to a previous tiles definition
    @Override
    public void doExecute(String location, ActionInvocation invocation) throws Exception {
        //location = "test.definition"; //for test
        //setLocation(location);
        //if (location != null && location.length() > 0){
        //    this.template = location;
        //String conditionalParse = this.conditionalParse(attributes, invocation);
        evaluatedAttributes = (Map<String, Attribute>) invocation.getStack().findValue(attributes);
        
        log.log(Level.INFO, "TilesResult doExecute() location: {0}", location);
        log.log(Level.INFO, "evaluatedAttributes['body']:{0}", evaluatedAttributes.get("body"));
        
        
        ServletContext context = ServletActionContext.getServletContext();
        ApplicationContext applicationContext = ServletUtil.getApplicationContext(context);
        TilesContainer container = TilesAccess.getContainer(applicationContext);
        //must use a mutable container
        if (container instanceof MutableTilesContainer) {
        } else {
            throw new Exception("A Mutable Tiles Container is required");
        }

        MutableTilesContainer mc = (MutableTilesContainer) container;
        Definition def = new Definition();

        if (template != null && template.length() > 0) {
            def.setTemplateAttribute(Attribute.createTemplateAttribute(template));
        }
        if (name != null && name.length() > 0) {
            def.setName(name);
        }
        if (extendz != null && extendz.length() > 0) {
            def.setExtends(extendz);
        }
        if (preparer != null && preparer.length() > 0) {
            def.setPreparer(preparer);
        }

        if (evaluatedAttributes != null && evaluatedAttributes.size() > 0) {
            if (addMissing.equalsIgnoreCase("true")) {
                def.addMissing(evaluatedAttributes);
            } else {
                def.addAll(evaluatedAttributes);
            }
        }
        
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletRequest servletRequest = new ServletRequest(applicationContext, request, response);
        mc.register(def, servletRequest);
        
        container.render(name, servletRequest);
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setExtends(String extendz) {
        this.extendz = extendz;
    }

    public String getExtends() {
        return extendz;
    }

    /**
     * @return the preparer
     */
    public String getPreparer() {
        return preparer;
    }

    /**
     * @param preparer the preparer to set
     */
    public void setPreparer(String preparer) {
        this.preparer = preparer;
    }

    /**
     * @return the attributes
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the addMissing
     */
    public String getAddMissing() {
        return addMissing;
    }

    /**
     * @param addMissing the addMissing to set
     */
    public void setAddMissing(String addMissing) {
        this.addMissing = addMissing;
    }
}
