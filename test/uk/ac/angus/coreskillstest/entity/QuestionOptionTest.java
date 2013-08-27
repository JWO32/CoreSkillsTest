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
public class QuestionOptionTest {
    
    public QuestionOptionTest() {
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
     * Test of getOptionId method, of class QuestionOption.
     */
    @Test
    public void testGetOptionId() {
        System.out.println("getOptionId");
        QuestionOption instance = new QuestionOption();
        int expResult = 0;
        int result = instance.getOptionId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOptionId method, of class QuestionOption.
     */
    @Test
    public void testSetOptionId() {
        System.out.println("setOptionId");
        int newId = 0;
        QuestionOption instance = new QuestionOption();
        instance.setOptionId(newId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOptionText method, of class QuestionOption.
     */
    @Test
    public void testGetOptionText() {
        System.out.println("getOptionText");
        QuestionOption instance = new QuestionOption();
        String expResult = "";
        String result = instance.getOptionText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOptionText method, of class QuestionOption.
     */
    @Test
    public void testSetOptionText() {
        System.out.println("setOptionText");
        String newOptionText = "";
        QuestionOption instance = new QuestionOption();
        instance.setOptionText(newOptionText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCorrectOption method, of class QuestionOption.
     */
    @Test
    public void testGetCorrectOption() {
        System.out.println("getCorrectOption");
        QuestionOption instance = new QuestionOption();
        boolean expResult = false;
        boolean result = instance.getCorrectOption();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCorrectOption method, of class QuestionOption.
     */
    @Test
    public void testSetCorrectOption() {
        System.out.println("setCorrectOption");
        boolean isCorrect = false;
        QuestionOption instance = new QuestionOption();
        instance.setCorrectOption(isCorrect);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuestion method, of class QuestionOption.
     */
    @Test
    public void testSetQuestion() {
        System.out.println("setQuestion");
        Question newQuestion = null;
        QuestionOption instance = new QuestionOption();
        instance.setQuestion(newQuestion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuestion method, of class QuestionOption.
     */
    @Test
    public void testGetQuestion() {
        System.out.println("getQuestion");
        QuestionOption instance = new QuestionOption();
        Question expResult = null;
        Question result = instance.getQuestion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}