/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JWO
 */
public class StoredFeedbackTest {
    
    public StoredFeedbackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDefaultFeedback method, of class StoredFeedback.
     */
    @Test
    public void testGetDefaultFeedback() {
        System.out.println("getDefaultFeedback");
        String expResult = "";
        String result = StoredFeedback.getDefaultFeedback();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFeedbackId method, of class StoredFeedback.
     */
    @Test
    public void testSetFeedbackId() {
        System.out.println("setFeedbackId");
        int newId = 0;
        StoredFeedback instance = new StoredFeedback();
        instance.setFeedbackId(newId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeedbackId method, of class StoredFeedback.
     */
    @Test
    public void testGetFeedbackId() {
        System.out.println("getFeedbackId");
        StoredFeedback instance = new StoredFeedback();
        int expResult = 0;
        int result = instance.getFeedbackId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFeedbackText method, of class StoredFeedback.
     */
    @Test
    public void testSetFeedbackText() {
        System.out.println("setFeedbackText");
        String text = "";
        StoredFeedback instance = new StoredFeedback();
        instance.setFeedbackText(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeedbackText method, of class StoredFeedback.
     */
    @Test
    public void testGetFeedbackText() {
        System.out.println("getFeedbackText");
        StoredFeedback instance = new StoredFeedback();
        String expResult = "";
        String result = instance.getFeedbackText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResultRule method, of class StoredFeedback.
     */
    @Test
    public void testSetResultRule() {
        System.out.println("setResultRule");
        ResultRule newResultRule = null;
        StoredFeedback instance = new StoredFeedback();
        instance.setResultRule(newResultRule);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultRule method, of class StoredFeedback.
     */
    @Test
    public void testGetResultRule() {
        System.out.println("getResultRule");
        StoredFeedback instance = new StoredFeedback();
        ResultRule expResult = null;
        ResultRule result = instance.getResultRule();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}