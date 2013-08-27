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
public class ResultFeedbackTest {
    
    public ResultFeedbackTest() {
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
     * Test of setResultFeedbackId method, of class ResultFeedback.
     */
    @Test
    public void testSetResultFeedbackId() {
        System.out.println("setResultFeedbackId");
        int newFeedbackId = 0;
        ResultFeedback instance = new ResultFeedback();
        instance.setResultFeedbackId(newFeedbackId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultFeedbackId method, of class ResultFeedback.
     */
    @Test
    public void testGetResultFeedbackId() {
        System.out.println("getResultFeedbackId");
        ResultFeedback instance = new ResultFeedback();
        int expResult = 0;
        int result = instance.getResultFeedbackId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFeedbackText method, of class ResultFeedback.
     */
    @Test
    public void testSetFeedbackText() {
        System.out.println("setFeedbackText");
        String newFeedbackText = "";
        ResultFeedback instance = new ResultFeedback();
        instance.setFeedbackText(newFeedbackText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeedbackText method, of class ResultFeedback.
     */
    @Test
    public void testGetFeedbackText() {
        System.out.println("getFeedbackText");
        ResultFeedback instance = new ResultFeedback();
        String expResult = "";
        String result = instance.getFeedbackText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResult method, of class ResultFeedback.
     */
    @Test
    public void testSetResult() {
        System.out.println("setResult");
        Result newResult = null;
        ResultFeedback instance = new ResultFeedback();
        instance.setResult(newResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResult method, of class ResultFeedback.
     */
    @Test
    public void testGetResult() {
        System.out.println("getResult");
        ResultFeedback instance = new ResultFeedback();
        Result expResult = null;
        Result result = instance.getResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPercentageScore method, of class ResultFeedback.
     */
    @Test
    public void testSetPercentageScore() {
        System.out.println("setPercentageScore");
        float newPercentageScore = 0.0F;
        ResultFeedback instance = new ResultFeedback();
        instance.setPercentageScore(newPercentageScore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPercentageScore method, of class ResultFeedback.
     */
    @Test
    public void testGetPercentageScore() {
        System.out.println("getPercentageScore");
        ResultFeedback instance = new ResultFeedback();
        float expResult = 0.0F;
        float result = instance.getPercentageScore();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassOrFail method, of class ResultFeedback.
     */
    @Test
    public void testSetPassOrFail() {
        System.out.println("setPassOrFail");
        boolean newPassFail = false;
        ResultFeedback instance = new ResultFeedback();
        instance.setPassOrFail(newPassFail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassOrFail method, of class ResultFeedback.
     */
    @Test
    public void testGetPassOrFail() {
        System.out.println("getPassOrFail");
        ResultFeedback instance = new ResultFeedback();
        boolean expResult = false;
        boolean result = instance.getPassOrFail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}