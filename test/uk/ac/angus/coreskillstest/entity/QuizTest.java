/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;

/**
 *
 * @author JWO
 */
public class QuizTest {
    
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
     * Test of getQuizId method, of class Quiz.
     */
    @Test
    public void testGetQuizId() {
        System.out.println("getQuizId");
        Quiz instance = new Quiz();
        int expResult = 0;
        int result = instance.getQuizId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizId method, of class Quiz.
     */
    @Test
    public void testSetQuizId() {
        System.out.println("setQuizId");
        int newId = 0;
        Quiz instance = new Quiz();
        instance.setQuizId(newId);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizLevel method, of class Quiz.
     */
    @Test
    public void testGetQuizLevel() {
        System.out.println("getQuizLevel");
        Quiz instance = new Quiz();
        String expResult = "";
        String result = instance.getQuizLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizLevel method, of class Quiz.
     */
    @Test
    public void testSetQuizLevel() {
        System.out.println("setQuizLevel");
        String newQuizLevel = "";
        Quiz instance = new Quiz();
        instance.setQuizLevel(newQuizLevel);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizTitle method, of class Quiz.
     */
    @Test
    public void testGetQuizTitle() {
        System.out.println("getQuizTitle");
        Quiz instance = new Quiz();
        String expResult = "";
        String result = instance.getQuizTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizTitle method, of class Quiz.
     */
    @Test
    public void testSetQuizName() {
        System.out.println("setQuizName");
        String newName = "";
        Quiz instance = new Quiz();
        instance.setQuizTitle(newName);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizSubject method, of class Quiz.
     */
    @Test
    public void testGetQuizSubject() {
        System.out.println("getQuizSubject");
        Quiz instance = new Quiz();
        String expResult = "";
        String result = instance.getQuizSubject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizSubject method, of class Quiz.
     */
    @Test
    public void testSetQuizSubject() {
        System.out.println("setQuizSubject");
        String newDescription = "";
        Quiz instance = new Quiz();
        instance.setQuizSubject(newDescription);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalMarks method, of class Quiz.
     */
    @Test
    public void testGetTotalMarks() {
        System.out.println("getTotalMarks");
        Quiz instance = new Quiz();
        int expResult = 0;
        int result = instance.getTotalMarks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalMarks method, of class Quiz.
     */
    @Test
    public void testSetTotalMarks() {
        System.out.println("setTotalMarks");
        int newMarks = 0;
        Quiz instance = new Quiz();
        instance.setTotalMarks(newMarks);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calcTotalMarks method, of class Quiz.
     */
    @Test
    public void testCalcTotalMarks() {
        System.out.println("calcTotalMarks");
        Quiz instance = new Quiz();
        instance.calcTotalMarks();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getQuestions method, of class Quiz.
     */
    @Test
    public void testGetQuestions() {
        System.out.println("getQuestions");
        Quiz instance = new Quiz();
        List expResult = null;
        List result = instance.getQuestions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuestions method, of class Quiz.
     */
    @Test
    public void testSetQuestions() {
        System.out.println("setQuestions");
        List<Question> newQuestions = null;
        Quiz instance = new Quiz();
        instance.setQuestions(newQuestions);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizCategory method, of class Quiz.
     */
    @Test
    public void testGetQuizCategory() {
        System.out.println("getQuizCategory");
        Quiz instance = new Quiz();
        QuizCategory expResult = null;
        QuizCategory result = instance.getQuizCategory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizCategory method, of class Quiz.
     */
    @Test
    public void testSetQuizCategory() {
        System.out.println("setQuizCategory");
        QuizCategory newCategory = null;
        Quiz instance = new Quiz();
        instance.setQuizCategory(newCategory);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDateAdded method, of class Quiz.
     */
    @Test
    public void testGetDateAdded() {
        System.out.println("getDateAdded");
        Quiz instance = new Quiz();
        Calendar expResult = null;
        Calendar result = instance.getDateAdded();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDateAdded method, of class Quiz.
     */
    @Test
    public void testSetDateAdded() {
        System.out.println("setDateAdded");
        Calendar newDateAdded = null;
        Quiz instance = new Quiz();
        instance.setDateAdded(newDateAdded);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDuration method, of class Quiz.
     */
    @Test
    public void testGetDuration() {
        System.out.println("getDuration");
        Quiz instance = new Quiz();
        int expResult = 0;
        int result = instance.getDuration();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDuration method, of class Quiz.
     */
    @Test
    public void testSetDuration() {
        System.out.println("setDuration");
        int newDuration = 0;
        Quiz instance = new Quiz();
        instance.setDuration(newDuration);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setResultRules method, of class Quiz.
     */
    @Test
    public void testSetResultRules() {
        System.out.println("setResultRules");
        List<ResultRule> newResultRules = null;
        Quiz instance = new Quiz();
        instance.setResultRules(newResultRules);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getResultRules method, of class Quiz.
     */
    @Test
    public void testGetResultRules() {
        System.out.println("getResultRules");
        Quiz instance = new Quiz();
        List expResult = null;
        List result = instance.getResultRules();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizEvents method, of class Quiz.
     */
    @Test
    public void testSetQuizConfiguration() {
        System.out.println("setQuizConfiguration");
        List<QuizEvent> qcList = null;
        Quiz instance = new Quiz();
        instance.setQuizEvents(qcList);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizEvents method, of class Quiz.
     */
    @Test
    public void testGetQuizConfiguration() {
        System.out.println("getQuizConfiguration");
        Quiz instance = new Quiz();
        List expResult = null;
        List result = instance.getQuizEvents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizMessages method, of class Quiz.
     */
    @Test
    public void testSetQuizMessages() {
        System.out.println("setQuizMessages");
        List<QuizMessage> newQuizMessages = null;
        Quiz instance = new Quiz();
        instance.setQuizMessages(newQuizMessages);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizMessages method, of class Quiz.
     */
    @Test
    public void testGetQuizMessages() {
        System.out.println("getQuizMessages");
        Quiz instance = new Quiz();
        List expResult = null;
        List result = instance.getQuizMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfQuestions method, of class Quiz.
     */
    @Test
    public void testGetNumberOfQuestions() {
        System.out.println("getNumberOfQuestions");
        Quiz instance = new Quiz();
        int expResult = 0;
        int result = instance.getNumberOfQuestions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}