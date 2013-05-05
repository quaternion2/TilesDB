package com.kenmcwilliams.employmentsystem.action.test;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.tiles.Attribute;

//Attributes can be list attributes and are nested or regular attributes
//Attributes have a number of paramers
//as such a string based only configuration would be terribly complex to use
//or only partially implemented
@Result(type = "tiles-definition", location = "dynamic-tiles-definition",
params = {
    "template", "/WEB-INF/test/test-template.jsp",
    "attributes", "attrs" //the value of "attributes" (which is "attrs") is evaluated using the value stack in the request
})
public class TestTilesDynamicDefinitionResult extends ActionSupport {

    private Map<String, Attribute> attrs = new HashMap();

    @Override
    public String execute(){
        Attribute attribute = new Attribute("/WEB-INF/test/test-dynamic-definition.jsp");
        attrs.put("body", attribute);
        return SUCCESS;
    }
    
    public Map<String, Attribute> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Attribute> attrs) {
        this.attrs = attrs;
    }
}
