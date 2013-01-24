/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.tiles.result;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tiles.Attribute;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author ken
 */
public class StrutsTilesConventionsTemplateLoaderTest {

    public StrutsTilesConventionsTemplateLoaderTest() {
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
     * Test of getTemplate method, of class
     * StrutsTilesConventionsTemplateLoader.
     */
    //@Test
    public void testConstructor() {
        //StrutsTilesConventionsTemplateLoader instance = null;
        try {
            StrutsTilesConventionsTemplateLoader instance = new StrutsTilesConventionsTemplateLoader();
            Map<String, Attribute> templates = instance.getTemplates();
            assertNotNull("templates is null", templates);
            org.junit.Assert.assertTrue("there are no templates", templates.size() > 0);
            //assert(templates.size();
        } catch (IOException ex) {
            Logger.getLogger(StrutsTilesConventionsTemplateLoaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTemplate method, of class
     * StrutsTilesConventionsTemplateLoader.
     */
    //@Test
    public void testGetTemplate() {
        System.out.println("getTemplate");
        String namespace = "";
        StrutsTilesConventionsTemplateLoader instance = null;
        try {
            instance = new StrutsTilesConventionsTemplateLoader();
        } catch (IOException ex) {
            Logger.getLogger(StrutsTilesConventionsTemplateLoaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Attribute expResult = null;
        Attribute result = instance.getTemplate(namespace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplates method, of class
     * StrutsTilesConventionsTemplateLoader.
     */
    //@Test
    public void testGetTemplates() {
        System.out.println("getTemplates");
        StrutsTilesConventionsTemplateLoader instance = null;
        try {
            instance = new StrutsTilesConventionsTemplateLoader();
        } catch (IOException ex) {
            Logger.getLogger(StrutsTilesConventionsTemplateLoaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map expResult = null;
        Map result = instance.getTemplates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
