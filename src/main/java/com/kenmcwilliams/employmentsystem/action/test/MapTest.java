/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ken
 */
public class MapTest extends ActionSupport {

    private Map< String, String> m_filters = new HashMap< String, String>();

    public Map< String, String> getFilters() {
        return m_filters;
    }

    public void setFilters(Map< String, String> filters) {
        this.m_filters = filters;
    }
}
