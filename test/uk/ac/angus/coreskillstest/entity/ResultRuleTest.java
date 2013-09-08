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
public class ResultRuleTest {
    
    
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
     * Test of setPassFail method, of class ResultRule.
     */
    @Test
    public void testSetPassFail() {
        System.out.println("setPassFail");
        boolean passFail = false;
        ResultRule instance = new ResultRule();
        instance.setPassFail(passFail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPassFail method, of class ResultRule.
     */
    @Test
    public void testGetPassFail() {
        System.out.println("getPassFail");
        ResultRule instance = new ResultRule();
        boolean expResult = false;
        boolean result = instance.isPassFail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResultRuleId method, of class ResultRule.
     */
    @Test
    public void testSetResultId() {
        System.out.println("setResultId");
        int newId = 0;
        ResultRule instance = new ResultRule();
        instance.setResultRuleId(newId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultRuleId method, of class ResultRule.
     */
    @Test
    public void testGetResultRuleId() {
        System.out.println("getResultRuleId");
        ResultRule instance = new ResultRule();
        int expResult = 0;
        int result = instance.getResultRuleId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRuleName method, of class ResultRule.
     */
    @Test
    public void testSetRuleName() {
        System.out.println("setRuleName");
        String newRuleName = "";
        ResultRule instance = new ResultRule();
        instance.setRuleName(newRuleName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRuleName method, of class ResultRule.
     */
    @Test
    public void testGetRuleName() {
        System.out.println("getRuleName");
        ResultRule instance = new ResultRule();
        String expResult = "";
        String result = instance.getRuleName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowMarkBoundary method, of class ResultRule.
     */
    @Test
    public void testGetLowMarkBoundary() {
        System.out.println("getLowMarkBoundary");
        ResultRule instance = new ResultRule();
        int expResult = 0;
        int result = instance.getLowMarkBoundary();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLowMarkBoundary method, of class ResultRule.
     */
    @Test
    public void testSetLowMarkBoundary() {
        System.out.println("setLowMarkBoundary");
        int newLowMarkBoundary = 0;
        ResultRule instance = new ResultRule();
        instance.setLowMarkBoundary(newLowMarkBoundary);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHighMarkBoundary method, of class ResultRule.
     */
    @Test
    public void testGetHighMarkBoundary() {
        System.out.println("getHighMarkBoundary");
        ResultRule instance = new ResultRule();
        int expResult = 0;
        int result = instance.getHighMarkBoundary();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHighMarkBoundary method, of class ResultRule.
     */
    @Test
    public void testSetHighMarkBoundary() {
        System.out.println("setHighMarkBoundary");
        int newHighMarkBoundary = 0;
        ResultRule instance = new ResultRule();
        instance.setHighMarkBoundary(newHighMarkBoundary);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuiz method, of class ResultRule.
     */
    @Test
    public void testGetQuiz() {
        System.out.println("getQuiz");
        ResultRule instance = new ResultRule();
        Quiz expResult = null;
        Quiz result = instance.getQuiz();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuiz method, of class ResultRule.
     */
    @Test
    public void testSetQuiz() {
        System.out.println("setQuiz");
        Quiz newQuiz = null;
        ResultRule instance = new ResultRule();
        instance.setQuiz(newQuiz);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeedback method, of class ResultRule.
     */
    @Test
    public void testGetFeedback() {
        System.out.println("getFeedback");
        ResultRule instance = new ResultRule();
        String expResult = "";
        String result = instance.getFeedback();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFeedback method, of class ResultRule.
     */
    @Test
    public void testSetFeedback() {
        System.out.println("setFeedback");
        String newFeedback = "";
        ResultRule instance = new ResultRule();
        instance.setFeedback(newFeedback);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHighMarkGtEq method, of class ResultRule.
     */
    @Test
    public void testSetHighMarkGtEq() {
        System.out.println("setHighMarkGtEq");
        boolean highMarkGtEq = false;
        ResultRule instance = new ResultRule();
        instance.setHighMarkGtEq(highMarkGtEq);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isHighMarkGtEq method, of class ResultRule.
     */
    @Test
    public void testGetHighMarkGtEq() {
        System.out.println("getHighMarkGtEq");
        ResultRule instance = new ResultRule();
        boolean expResult = false;
        boolean result = instance.isHighMarkGtEq();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLowMarkGtEq method, of class ResultRule.
     */
    @Test
    public void testSetLowMarkGtEq() {
        System.out.println("setLowMarkGtEq");
        boolean lowMarkGtEq = false;
        ResultRule instance = new ResultRule();
        instance.setLowMarkGtEq(lowMarkGtEq);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLowMarkGtEq method, of class ResultRule.
     */
    @Test
    public void testGetLowMarkGtEq() {
        System.out.println("getLowMarkGtEq");
        ResultRule instance = new ResultRule();
        boolean expResult = false;
        boolean result = instance.isLowMarkGtEq();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appliesTo method, of class ResultRule.
     */
    @Test
    public void testAppliesTo() {
        System.out.println("appliesTo");
        int Score = 0;
        ResultRule instance = new ResultRule();
        boolean expResult = false;
        boolean result = instance.appliesTo(Score);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}