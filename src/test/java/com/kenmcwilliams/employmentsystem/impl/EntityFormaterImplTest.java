/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ken
 */
public class EntityFormaterImplTest {

    public EntityFormaterImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUnmodifiablePropertiesByEntity method, of class
     * EntityFormaterImpl.
     */
    @Test
    public void testGetUnmodifiablePropertiesByEntity() {
        System.out.println("getUnmodifiablePropertiesByEntity");
        Class clazz = com.kenmcwilliams.employmentsystem.orm.Recruiter.class;
        EntityInspectorImpl instance = new EntityInspectorImpl();
        assertNotNull("Instance is null", instance);
        ArrayList<String> expResult = new ArrayList();
        expResult.add("id");
        expResult.add("fname");
        expResult.add("mname");
        expResult.add("lname");
        expResult.add("candidateLogCollection"); 
        Collection<String> result = instance.getUnmodifiablePropertiesByEntity(clazz);
        assertNotNull(result);
        org.junit.Assert.assertTrue("Result has no values", result.iterator().hasNext());
        int i = 0;
        for (String strMethod : result) {
            //for (String strExpected : expResult) {
            //System.out.println("Comparing: " + strMethod + " to " + strExpected);
            assertEquals("Comparing: " + strMethod + " to " + expResult.get(i) + " failed.", expResult.get(i).compareTo(strMethod), 0);
            i++;
            //}
        }
    }

    /**
     * Test of getOrderedProperties method, of class EntityFormaterImpl.
     */
    @Test
    public void testGetOrderedProperties() {
        System.out.println("getOrderedProperties");
        Class clazz = com.kenmcwilliams.employmentsystem.orm.Recruiter.class;
        EntityInspectorImpl instance = new EntityInspectorImpl();
        assertNotNull("Instance is null", instance);
        ArrayList<String> expResult = new ArrayList();
        expResult.add("id");
        expResult.add("fname");
        expResult.add("mname");
        expResult.add("lname");
        expResult.add("candidateLogCollection"); 
        SortedMap<Integer, String> result = instance.getOrderedProperties(clazz);
        assertNotNull(result);
        org.junit.Assert.assertTrue("Values is not greater than zero", result.values().size() > 0);
        Collection<String> values = result.values();
        int i = 0;
        for (String strMethod : values) {
            System.out.println("Comparing: " + strMethod + " to " + expResult.get(i));
            assertEquals("Comparing: " + strMethod + " to " + expResult.get(i) + " failed.", expResult.get(i).compareTo(strMethod), 0);
            i++;
        }
    }
}
