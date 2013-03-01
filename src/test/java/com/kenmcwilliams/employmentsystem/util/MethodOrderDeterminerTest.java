/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.util;

import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author ken
 */
public class MethodOrderDeterminerTest {
    
    public MethodOrderDeterminerTest() {
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
     * Test of getOrderedPropertiesFor method, of class MethodOrderDeterminer.
     */
    @Test
    public void testGetOrderedPropertiesFor(){
        System.out.println("getOrderedPropertiesFor");
        Class clazz = com.kenmcwilliams.employmentsystem.orm.Recruiter.class;
        SortedMap expResult = null;
        SortedMap<Integer, String> result = MethodOrderDeterminer.getOrderedPropertiesFor(clazz);
        System.out.println("result size: " + result.size());
        
        org.junit.Assert.assertTrue(result.size() > 0);
        //Collection<String> values = result.values();
        Set<Integer> keySet = result.keySet();
        
        String[] expected = {"id", "fname", "mname", "lname"};
        //58 fname
        //66 mname
        //74 lname
        
        int i = 0;
        for (Integer key : keySet){
            org.junit.Assert.assertTrue("Not true: " + result.get(key) + " != " + expected[i], result.get(key).compareTo(expected[i]) == 0);
            i++;
            System.out.println("key: " + key + " value: " + result.get(key));
            
        }
        
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
