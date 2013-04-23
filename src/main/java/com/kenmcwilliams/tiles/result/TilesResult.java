/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.tiles.result;

import com.opensymphony.xwork2.ActionInvocation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;

public class TilesResult extends ServletDispatcherResult {

    private static final Logger log = Logger.getLogger(TilesResult.class.getName());

    public TilesResult() {
        super();
    }

    public TilesResult(String location) {
        super(location);
    }

    @Override
    public void doExecute(String location, ActionInvocation invocation) throws Exception {
        //location = "test.definition"; //for test
        log.log(Level.INFO, "TilesResult doExecute() location: {0}", location);
        //Start simple conventions
        //
        if (/** tiles && **/location == null) {
            String namespace = invocation.getProxy().getNamespace();
            String actionName = invocation.getProxy().getActionName();
            location = namespace + "#" + actionName + ".jsp"; //Warning forcing extension
            log.log(Level.INFO, "TilesResult namespace: {0}", namespace);
            log.log(Level.INFO, "TilesResult actionName: {0}", actionName);
            log.log(Level.INFO, "TilesResult location: {0}", location);
        }
        //End simple conventions
        setLocation(location);
        ServletContext context = ServletActionContext.getServletContext();
        ApplicationContext applicationContext = ServletUtil.getApplicationContext(context);
        TilesContainer container = TilesAccess.getContainer(applicationContext);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletRequest servletRequest = new ServletRequest(applicationContext, request, response);
        container.render(location, servletRequest);
    }
}
