/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author ken
 */
public class CheckAPCJob extends QuartzJobBean {
    //@Autowired
    //private CheckAPCService checkAPCService;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        //checkAPCService.checkAPC();
        System.out.println("zxcv: THIS IS THE JOB!!!");
    }

}