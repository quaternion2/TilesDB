/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.tiles.result;

import com.kenmcwilliams.employmentsystem.action.crud.AddAction;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.tiles.Attribute;

/**
 * Searches all packages for templates currently only files called
 * "template.jsp" will be used templates are selected in the following way: the
 * current folder is checked for the "template.jsp" then then each higher folder
 * is checked.
 *
 * @author ken
 */
public class StrutsTilesConventionsTemplateLoader {

    private static final Logger log = Logger.getLogger(StrutsTilesConventionsTemplateLoader.class.getName());
    private final Map<String, Attribute> templates;
    private String conventionsRoot;

    public StrutsTilesConventionsTemplateLoader() throws IOException {
        HashMap<String, Attribute> temp = new HashMap<String, Attribute>();
        Enumeration<URL> urls = this.getClass().getClassLoader().getResources("template.jsp"); //web.xml must exist for this to work...
        URL resource = this.getClass().getClassLoader().getResource("/WEB-INF/web.xml");
        if (resource != null) {
            conventionsRoot = resource.getPath();
            log.log(Level.INFO, "conventions root is: {0}", conventionsRoot);
        }else{
            log.warning("Path to WEB-INF/web.xml not found!");
        }

        while (urls.hasMoreElements() == true) {
            URL url = urls.nextElement();
            String absolutePath = url.getPath();
            String nameSpace = absolutePath.substring(conventionsRoot.length() - 1);
            log.log(Level.INFO, "putting: {0} into map.", nameSpace);
            temp.put(nameSpace, Attribute.createTemplateAttribute(absolutePath));
        }
        templates = temp;


        //TODO: log -info conventionsRoot path
        //TODO: consider if there is a way to make this work without looking for web.xml
    }

    public Attribute getTemplate(String namespace) {
        //check for empty, otherwise number of slashes will 
        //determine depth...
        int depth = 0;
        if (namespace != null && !namespace.isEmpty()) {
            depth = StringUtils.countMatches(namespace, "/");
        }

        //THERE must be a template for "/"
        String searchString = namespace;
        Attribute template = null;
        for (int i = 0; i < depth; i++) {
            template = templates.get(namespace);
            if (template != null) {
                break;
            } else {
                int lastIndex = indexOfLastSlash(searchString);
                searchString = searchString.substring(0, lastIndex);//cut off everything after the last "/"
            }
        }
        assert (template != null); //TODO: remove assert
        return template;
    }

    public Map<String, Attribute> getTemplates() {
        return Collections.unmodifiableMap(templates);
    }

    private int indexOfLastSlash(String searchString) {
        if (searchString == null || searchString.isEmpty()) {
            return 0;
        }
        for (int i = searchString.length(); i > 0; i--) {
            if (searchString.charAt(i - 1) == '/') {
                return i;
            }
        }
        return 0;//not found or first
    }
}
