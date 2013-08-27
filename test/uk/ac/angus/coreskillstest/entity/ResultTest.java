/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import java.util.Calendar;
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
public class ResultTest {
    
    public ResultTest() {
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
     * Test of getResultId method, of class Result.
     */
    @Test
    public void testGetResultId() {
        System.out.println("getResultId");
        Result instance = new Result();
        int expResult = 0;
        int result = instance.getResultId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResultId method, of class Result.
     */
    @Test
    public void testSetResultId() {
        System.out.println("setResultId");
        int newResultId = 0;
        Result instance = new Result();
        instance.setResultId(newResultId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResultStatus method, of class Result.
     */
    @Test
    public void testSetResultStatus() {
        System.out.println("setResultStatus");
        String resultStatus = "";
        Result instance = new Result();
        instance.setResultStatus(resultStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultStatus method, of class Result.
     */
    @Test
    public void testGetResultStatus() {
        System.out.println("getResultStatus");
        Result instance = new Result();
        String expResult = "";
        String result = instance.getResultStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLinkedQuiz method, of class Result.
     */
    @Test
    public void testGetLinkedQuiz() {
        System.out.println("getLinkedQuiz");
        Result instance = new Result();
        Quiz expResult = null;
        Quiz result = instance.getLinkedQuiz();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLinkedQuiz method, of class Result.
     */
    @Test
    public void testSetLinkedQuiz() {
        System.out.println("setLinkedQuiz");
        Quiz newLinkedQuiz = null;
        Result instance = new Result();
        instance.setLinkedQuiz(newLinkedQuiz);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizUser method, of class Result.
     */
    @Test
    public void testGetQuizUser() {
        System.out.println("getQuizUser");
        Result instance = new Result();
        QuizUser expResult = null;
        QuizUser result = instance.getQuizUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizUser method, of class Result.
     */
    @Test
    public void testSetQuizUser() {
        System.out.println("setQuizUser");
        QuizUser newLinkedUser = null;
        Result instance = new Result();
        instance.setQuizUser(newLinkedUser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLinkedFeedback method, of class Result.
     */
    @Test
    public void testGetLinkedFeedback() {
        System.out.println("getLinkedFeedback");
        Result instance = new Result();
        String expResult = "";
        String result = instance.getLinkedFeedback();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLinkedFeedback method, of class Result.
     */
    @Test
    public void testSetLinkedFeedback() {
        System.out.println("setLinkedFeedback");
        String newLinkedFeedback = "";
        Result instance = new Result();
        instance.setLinkedFeedback(newLinkedFeedback);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScoreandPercentage method, of class Result.
     */
    @Test
    public void testSetScoreandPercentage() {
        System.out.println("setScoreandPercentage");
        int quizScore = 0;
        int numberOfQuestions = 0;
        Result instance = new Result();
        instance.setScoreandPercentage(quizScore, numberOfQuestions);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizPercentage method, of class Result.
     */
    @Test
    public void testSetQuizPercentage() {
        System.out.println("setQuizPercentage");
        float newPercentage = 0.0F;
        Result instance = new Result();
        instance.setQuizPercentage(newPercentage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizPercentage method, of class Result.
     */
    @Test
    public void testGetQuizPercentage() {
        System.out.println("getQuizPercentage");
        Result instance = new Result();
        float expResult = 0.0F;
        float result = instance.getQuizPercentage();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuizScore method, of class Result.
     */
    @Test
    public void testSetQuizScore() {
        System.out.println("setQuizScore");
        int newQuizScore = 0;
        Result instance = new Result();
        instance.setQuizScore(newQuizScore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizScore method, of class Result.
     */
    @Test
    public void testGetQuizScore() {
        System.out.println("getQuizScore");
        Result instance = new Result();
        int expResult = 0;
        int result = instance.getQuizScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultDate method, of class Result.
     */
    @Test
    public void testGetResultDate() {
        System.out.println("getResultDate");
        Result instance = new Result();
        Calendar expResult = null;
        Calendar result = instance.getResultDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResultDate method, of class Result.
     */
    @Test
    public void testSetResultDate() {
        System.out.println("setResultDate");
        Calendar newResultDate = null;
        Result instance = new Result();
        instance.setResultDate(newResultDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}