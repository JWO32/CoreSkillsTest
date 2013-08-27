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
public class QuizCategoryTest {
    
    public QuizCategoryTest() {
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
     * Test of getQuizCategoryId method, of class QuizCategory.
     */
    @Test
    public void testGetQuizCategoryId() {
        System.out.println("getQuizCategoryId");
        QuizCategory instance = new QuizCategory();
        int expResult = 0;
        int result = instance.getQuizCategoryId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizCategoryId method, of class QuizCategory.
     */
    @Test
    public void testSetQuizCategoryId() {
        System.out.println("setQuizCategoryId");
        int newId = 0;
        QuizCategory instance = new QuizCategory();
        instance.setQuizCategoryId(newId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuiz method, of class QuizCategory.
     */
    @Test
    public void testGetQuiz() {
        System.out.println("getQuiz");
        QuizCategory instance = new QuizCategory();
        Quiz expResult = null;
        Quiz result = instance.getQuiz();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuiz method, of class QuizCategory.
     */
    @Test
    public void testSetQuiz() {
        System.out.println("setQuiz");
        Quiz newQuiz = null;
        QuizCategory instance = new QuizCategory();
        instance.setQuiz(newQuiz);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}