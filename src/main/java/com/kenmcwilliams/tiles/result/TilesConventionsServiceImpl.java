/*
 * $Id: ConventionsServiceImpl.java 1096834 2011-04-26 18:18:08Z jogep $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.kenmcwilliams.tiles.result;

import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.config.entities.PackageConfig;
import com.opensymphony.xwork2.config.entities.ResultTypeConfig;
import com.opensymphony.xwork2.inject.Inject;
import flexjson.JSONSerializer;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.convention.ConventionsService;
import org.apache.struts2.convention.ConventionsServiceImpl;

/**
 * <p>
 * This class is the implementation of the {@link ConventionsService}
 * interface and provides all of the defaults and annotation handling.
 * </p>
 */
public class TilesConventionsServiceImpl extends ConventionsServiceImpl {
    private static final Logger log = Logger.getLogger(TilesConventionsServiceImpl.class.getName());
    //private String resultPath;

    /**
     * Constructs a new instance.
     *
     * @param   resultPath The result path that is configured in the Struts configuration files using
     *          the constant name of <strong>struts.convention.result.path</strong>.
     */
    @Inject
    public TilesConventionsServiceImpl(@Inject("struts.convention.result.path") String resultPath) {
        super(resultPath);
        log.log(Level.INFO, "TilesConventionsServiceImpl: Constructed TilesConventionsServiceImpl with resultPath : {0}", resultPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String determineResultPath(Class<?> actionClass) {
        log.log(Level.INFO, "TilesConventionsServiceImpl: Entered determineResultPath with actionClass: {0}", actionClass.getCanonicalName());
        String determineResultPath = super.determineResultPath(actionClass);
        log.log(Level.INFO, "TilesConventionsServiceImpl: Exiting determineResultPath, return with resultPath: {0}", determineResultPath);
        return determineResultPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String determineResultPath(ActionConfig actionConfig) {
        log.info("TilesConventionsServiceImpl: Entered determineResultPath with actionConfig");
        String determineResultPath = super.determineResultPath(actionConfig);
        log.log(Level.INFO, "TilesConventionsServiceImpl: Exiting determineResultPath, returning determineResultPath with: {0}", (new JSONSerializer().serialize(determineResultPath)));
        return determineResultPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, ResultTypeConfig> getResultTypesByExtension(PackageConfig packageConfig) {
        log.info("TilesConventionsServiceImpl: Entered getResultTypesByExtension with packageConfig");
        Map<String, ResultTypeConfig>  resultTypesByExtension = super.getResultTypesByExtension(packageConfig);
        log.log(Level.INFO, "TilesConventionsServiceImpl: Exiting getResultTypesByExtension, returning resultTypesByExtension: {0}", (new JSONSerializer().serialize(resultTypesByExtension)));
        return resultTypesByExtension;
    }
}
