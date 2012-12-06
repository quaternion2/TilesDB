/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.tiles.result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.XWorkException;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;
import flexjson.JSONSerializer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.apache.struts2.convention.ConventionUnknownHandler;

public class TilesUnknownHandler extends ConventionUnknownHandler {

    private static final Logger log = Logger.getLogger(TilesUnknownHandler.class.getName());

    @Inject
    public TilesUnknownHandler(Configuration configuration, ObjectFactory objectFactory,
            ServletContext servletContext, Container container,
            @Inject("struts.convention.default.parent.package") String defaultParentPackageName,
            @Inject("struts.convention.redirect.to.slash") String redirectToSlash,
            @Inject("struts.convention.action.name.separator") String nameSeparator) {
        super(configuration, objectFactory, servletContext, container, defaultParentPackageName,
                redirectToSlash, nameSeparator);
        log.info("Constructed TilesUnknownHandler");
    }

    @Override
    public ActionConfig handleUnknownAction(String namespace, String actionName)
            throws XWorkException {
        log.info("TilesUnknownHandler: before handleUnknownAction");
        ActionConfig handleUnknownAction = super.handleUnknownAction(namespace, actionName);
        log.info("TilesUnknownHandler: after handleUnknownAction, returning with:");
        log.log(Level.INFO, "...ActionConfig value: {0}", (new JSONSerializer().serialize(handleUnknownAction)));
        return handleUnknownAction;
    }

    @Override
    public Result handleUnknownResult(ActionContext actionContext, String actionName,
            ActionConfig actionConfig, String resultCode) throws XWorkException {
        log.info("TilesUnknownHandler: before handleUnknownResult");
        Result handleUnknownResult = super.handleUnknownResult(actionContext, actionName, actionConfig, resultCode);
        log.info("TilesUnknownHandler: after handleUnknownResult, returning with:");
        log.log(Level.INFO, "...Result value: {0}", (new JSONSerializer().serialize(handleUnknownResult)));
        return handleUnknownResult;
    }
}
