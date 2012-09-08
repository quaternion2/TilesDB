/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.test;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Vector;

/**
 *
 * @author ken
 */
public class VectorTest extends ActionSupport{
    private Vector<String> testVector = new Vector();

    /**
     * @return the testVector
     */
    public Vector<String> getTestVector() {
        return testVector;
    }

    /**
     * @param testVector the testVector to set
     */
    public void setTestVector(Vector<String> testVector) {
        this.testVector = testVector;
    }
}
