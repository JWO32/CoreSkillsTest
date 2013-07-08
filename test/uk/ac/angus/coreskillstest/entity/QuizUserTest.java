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
public class QuizUserTest 
{

    @BeforeClass
    public static void setUpClass() 
    {
    
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
    
    }
    
    @Before
    public void setUp() throws Exception
    {

    }
    
    @After
    public void tearDown() 
    {
    
    }

    /**
     * Test of setUserId method, of class User.
     */
    @Test
    public void testUserId() {
        System.out.println("Testing Get/Set User Id");
        int newID = 0;
        QuizUser instance = new QuizUser();
        instance.setUserId(newID);

        assertEquals(instance.getUserId(), newID);
    }
    
    /**
     * Test of setUserName method, of class User.
     */
    @Test
    public void testFirstName() {
        System.out.println("Testing Get/Set User Name");
        String newUserName = "James";
        QuizUser instance = new QuizUser();
        instance.setFirstName(newUserName);
        
        assertEquals(instance.getFirstName(), newUserName);
    }

    /**
     * Test of setUserDescripton method, of class User.
     */
    @Test
    public void testUserDescription() {
        System.out.println("Testing Get/Set User Description");
        String newUserDescription = "This is a new user";
        QuizUser instance = new QuizUser();
        instance.setUserDescription(newUserDescription);
        
        assertEquals(instance.getUserDescription(), newUserDescription);
    }

    /**
     * Test of setUserEmail method, of class User.
     */
    @Test
    public void testUserEmail() {
        System.out.println("Testing Get/Set User E-mail");
        String newUserEmail = "testuser@test.com";
        QuizUser instance = new QuizUser();
        instance.setUserEmail(newUserEmail);

        assertEquals(instance.getUserEmail(), newUserEmail);
    }

    /**
     * Test of setUserDateAdded method, of class User.
     */
    @Test
    public void testDateAdded() {
        System.out.println("Testing Get/Set User Date Added");
        Calendar dateAdded = Calendar.getInstance();
        QuizUser instance = new QuizUser();
        instance.setUserDateAdded(dateAdded);
        
        assertEquals(instance.getUserDateAdded(), dateAdded);
    }
}