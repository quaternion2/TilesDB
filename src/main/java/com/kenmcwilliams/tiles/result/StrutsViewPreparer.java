package com.kenmcwilliams.tiles.result;

import java.util.Map;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.Request;

/**
 * The purpose of this class is to dynamically resolve templates
 * to keep with struts2 conventions
 * @author ken
 */
public class StrutsViewPreparer implements ViewPreparer {

    @Override
    public void execute(Request requestContext, AttributeContext attributeContext) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //Use spring to set a map of Tiles template attributes for various namespaces
        Map<String, Object> applicationScope = requestContext.getApplicationContext().getApplicationScope();
        attributeContext.setTemplateAttribute(null);
    }
    
}
