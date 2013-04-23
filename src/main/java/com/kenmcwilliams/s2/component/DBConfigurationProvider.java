/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.s2.component;

import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.ConfigurationProvider;
import com.opensymphony.xwork2.inject.ContainerBuilder;
import com.opensymphony.xwork2.util.location.LocatableProperties;

/**
 *
 * @author ken
 */
public class DBConfigurationProvider implements ConfigurationProvider {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(ConfigurationProvider.class.getName());

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
        log.info("DBConfigurationProvider: destroy");
    }

    @Override
    public void init(Configuration configuration) throws ConfigurationException {
        log.info("DBConfigurationProvider: init");
    }

    @Override
    public boolean needsReload() {
        log.info("DBConfigurationProvider: needsReload");
        return false;
    }

    @Override
    public void register(ContainerBuilder builder, LocatableProperties props) throws ConfigurationException {
        log.info("DBConfigurationProvider: register");
    }

    @Override
    public void loadPackages() throws ConfigurationException {
        log.info("DBConfigurationProvider: loadPackages");
    }
}
