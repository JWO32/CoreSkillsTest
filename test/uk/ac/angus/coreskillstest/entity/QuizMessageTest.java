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
public class QuizMessageTest {
    
    public QuizMessageTest() {
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
     * Test of setMessageId method, of class QuizMessage.
     */
    @Test
    public void testSetMessageId() {
        System.out.println("setMessageId");
        int newId = 0;
        QuizMessage instance = new QuizMessage();
        instance.setMessageId(newId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageId method, of class QuizMessage.
     */
    @Test
    public void testGetMessageId() {
        System.out.println("getMessageId");
        QuizMessage instance = new QuizMessage();
        int expResult = 0;
        int result = instance.getMessageId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessageTitle method, of class QuizMessage.
     */
    @Test
    public void testSetMessageTitle() {
        System.out.println("setMessageTitle");
        String newTitle = "";
        QuizMessage instance = new QuizMessage();
        instance.setMessageTitle(newTitle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageTitle method, of class QuizMessage.
     */
    @Test
    public void testGetMessageTitle() {
        System.out.println("getMessageTitle");
        QuizMessage instance = new QuizMessage();
        String expResult = "";
        String result = instance.getMessageTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContent method, of class QuizMessage.
     */
    @Test
    public void testSetContent() {
        System.out.println("setContent");
        String newContent = "";
        QuizMessage instance = new QuizMessage();
        instance.setContent(newContent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class QuizMessage.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        QuizMessage instance = new QuizMessage();
        String expResult = "";
        String result = instance.getContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassMark method, of class QuizMessage.
     */
    @Test
    public void testSetPassMark() {
        System.out.println("setPassMark");
        int newPassMark = 0;
        QuizMessage instance = new QuizMessage();
        instance.setPassMark(newPassMark);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassMark method, of class QuizMessage.
     */
    @Test
    public void testGetPassMark() {
        System.out.println("getPassMark");
        QuizMessage instance = new QuizMessage();
        int expResult = 0;
        int result = instance.getPassMark();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsStartMessage method, of class QuizMessage.
     */
    @Test
    public void testSetIsStartMessage() {
        System.out.println("setIsStartMessage");
        boolean isStart = false;
        QuizMessage instance = new QuizMessage();
        instance.setIsStartMessage(isStart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsStartMessage method, of class QuizMessage.
     */
    @Test
    public void testGetIsStartMessage() {
        System.out.println("getIsStartMessage");
        QuizMessage instance = new QuizMessage();
        boolean expResult = false;
        boolean result = instance.getIsStartMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLinkedQuiz method, of class QuizMessage.
     */
    @Test
    public void testSetLinkedQuiz() {
        System.out.println("setLinkedQuiz");
        Quiz newQuiz = null;
        QuizMessage instance = new QuizMessage();
        instance.setLinkedQuiz(newQuiz);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLinkedQuiz method, of class QuizMessage.
     */
    @Test
    public void testGetLinkedQuiz() {
        System.out.println("getLinkedQuiz");
        QuizMessage instance = new QuizMessage();
        Quiz expResult = null;
        Quiz result = instance.getLinkedQuiz();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}