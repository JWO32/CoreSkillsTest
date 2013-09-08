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
public class QuizGroupTest 
{
    private QuizGroup group;
    
    @BeforeClass
    public static void setUpClass() 
    {
       
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
    
    }
    
    @Before
    public void setUp() 
    {
        group = new QuizGroup();
    }
    
    @After
    public void tearDown() 
    {
    
    }

    /**
     * Test of setGroupName method, of class QuizGroup.
     */
    @Test
    public void testGetSetGroupName()
    {
        String groupTestName = "TestGroup1";
        System.out.println("setGroupName");
        group.setGroupName(groupTestName);
        
        assertEquals(group.getGroupName(),groupTestName);
    }
    
    /**
     * Test of setGroupID method, of class QuizGroup.
     */
    @Test
    public void testGetSetGroupID() 
    {
        int groupId = 123;
        System.out.println("setGroupID");
        
        group.setGroupID(groupId);
        
        assertEquals(group.getGroupID(), groupId);
    }

    /**
     * Test of setGroupDescription method, of class QuizGroup.
     */
    @Test
    public void testGetSetGroupDescription() 
    {
        String description = "This is a group";
        System.out.println("setGroupDescription");
        
        group.setGroupDescription(description);
        assertEquals(group.getGroupDescription(), description);
    }
}