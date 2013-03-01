/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensymphony.xwork2.util;

import com.opensymphony.xwork2.XWorkTestCase;
import java.util.HashMap;
import org.junit.*;

/**
 *
 * @author ken
 */
public class WildcardHelperTest extends XWorkTestCase {

    public WildcardHelperTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    
    //Test unrelated to application, testing xwork package against suspected issue
    //tests all passed, there is no issue
    @Test
    public void testMatch() {
        WildcardHelper wild = new WildcardHelper();
        HashMap<String, String> matchedPatterns = new HashMap<String, String>();
        int[] pattern = wild.compilePattern("*b*");
        assertEquals(wild.match(matchedPatterns, "b", pattern), true);
        assertEquals(wild.match(matchedPatterns, "abc", pattern), true);
        assertEquals(wild.match(matchedPatterns, "xxx", pattern), false);

        pattern = wild.compilePattern("*_*");
        assertEquals(wild.match(matchedPatterns, "a_a", pattern), true);
        assertEquals(wild.match(matchedPatterns, "a", pattern), false);
    }
}
