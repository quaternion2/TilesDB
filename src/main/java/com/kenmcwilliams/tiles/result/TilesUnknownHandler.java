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
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.config.entities.ResultConfig.Builder;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;
import flexjson.JSONSerializer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.ConventionUnknownHandler;

public class TilesUnknownHandler extends ConventionUnknownHandler {

    private static final Logger log = Logger.getLogger(TilesUnknownHandler.class.getName());
    private static final String conventionBase = "/WEB-INF/content";

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
        ActionConfig actionConfig;
        log.info("TilesUnknownHandler: before handleUnknownAction");
        ActionConfig handleUnknownAction = super.handleUnknownAction(namespace, actionName);

        log.info("TilesUnknownHandler: after handleUnknownAction, returning with:");
        log.log(Level.INFO, "...ActionConfig value: {0}", (new JSONSerializer().serialize(handleUnknownAction)));
        log.log(Level.INFO, "Modifying handleUnknowAction result handler");

        Map<String, ResultConfig> results = handleUnknownAction.getResults();
        ResultConfig resultConfig = results.get("success");
        Builder builder = new ResultConfig.Builder("com.opensymphony.xwork2.config.entities.ResultConfig", "com.kenmcwilliams.tiles.result.TilesResult");
        Map<String, String> params = resultConfig.getParams();
        
        String tilesResultString = null;
        String location = params.get("location");
        if (location != null && !location.isEmpty()) {
            int length = conventionBase.length();
            
            if(StringUtils.startsWith(location, conventionBase)){
                String subString = location.substring(length); //chop off "/WEB-INF/content"
                int count = StringUtils.countMatches(subString, "/");//TODO: maybe check for "//", although I don't know why it would be in the string
                if (count == 1){//empty namespace
                    tilesResultString = subString.replaceFirst("/", "#"); //TODO: because I am doing a straight replacement of the last element the else can probably be removed
                }else{ //replace the last slash between the namespace and the file with "#"
                    int lastIndex = subString.lastIndexOf("/");
                    //subString.substring(lastIndex, lastIndex);
                    String nameSpace = subString.substring(0, lastIndex);
                    String file = subString.substring(lastIndex + 1);
                    tilesResultString = nameSpace + "#" + file;
                }
            }
        }
        
        Map<String, String> myParams = new LinkedHashMap<String, String>();
        myParams.put("location", tilesResultString);

        builder.addParams(myParams);
        ResultConfig build = builder.build();
        Map<String, ResultConfig> myMap = new LinkedHashMap<String, ResultConfig>();
        myMap.put("success", build);
        log.log(Level.INFO, "\n\n...results: {0}\n\n", (new JSONSerializer().serialize(results)));
        actionConfig = new ActionConfig.Builder(handleUnknownAction).addResultConfigs(myMap).build();
        //className("com.kenmcwilliams.tiles.result.TilesResult")
        return actionConfig;
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
