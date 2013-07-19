/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.DataManagement;

import uk.ac.angus.coreskillstest.entity.QuizGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.List;
 
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author JWO
 */
public class GroupDataAccessObject 
{
    private EntityManagerFactory GroupDataFactory;
   // private Gson JsonSerialiser;
    
    // This should be moved out to suitable defaults file in due course.
    private static final int DEFAULT_GROUP_ID = 1;
    
    
    public GroupDataAccessObject()
    {
        GroupDataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        //Gson JsonSerialiser = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    /**
     * 
     * TODO: Remove this method and replace with a defaults object to create essential data.
     * 
     * @return 
     */
    public static QuizGroup getDefaultGroup()
    {
        QuizGroup ug;
        EntityManagerFactory DataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        
        EntityManager em = DataFactory.createEntityManager();
        
        ug = (QuizGroup) em.find(QuizGroup.class, GroupDataAccessObject.DEFAULT_GROUP_ID);
        
        em.close();
        
        if(ug == null)
            return null;
        else
            return ug;
    }
    
    /**
     * 
     * Creates a default group if one does not already exist.
     * 
     * TODO: Remove this method into a test method or a default data object for database initialisation
     */
    public static void createDefaultGroup()
    {
        QuizGroup ug = new QuizGroup("Default Group", "This is the default group for users who are not otherwise assigned to a group");
        EntityManagerFactory DataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        
        EntityManager em = DataFactory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(ug);
        em.getTransaction().commit();
        em.close();
    }
    
    public void addSingleGroup(String singleGroupJson)
    {
        Gson JsonSerialiser = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        EntityManager em = GroupDataFactory.createEntityManager();
        QuizGroup ug = null;
    
        ug = (QuizGroup) JsonSerialiser.fromJson(singleGroupJson, QuizGroup.class);
        
        em.getTransaction().begin();
        em.persist(ug);
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Intended for use with CSV upload use cases.
     * 
     * @param multipleGroupJson 
     */
    public void addMultipleGroups(String multipleGroupJson)
    {
        
    }
    
    /**
     * Delete the group indicated by the group ID
     * 
     * Current behaviour is to delete all users as well.  Add code to move all users
     * from the deleted group to the default group (group ID 0)
     * @param groupId 
     */
    public void deleteSingleGroup(int groupId)
    {
        
    }
    
    /**
     * Delete multiple groups indicated by the groupIdList parameter.
     * 
     * @param groupIdList 
     */
    public void deleteMultipleGroups(int[] groupIdList)
    {
        
    }
    
    /**
     * Fetch the group identified by the groupId.
     * 
     * @param groupId
     * @return 
     */
    public String fetchGroupById(int groupId)
    {
        String json = "";
        
        return json;
    }
    
    /**
     * Return all groups and users in JSON format.
     * 
     * @return 
     */
    public String fetchAllGroupsandUsers()
    {
        Gson JsonSerialiser = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json;
        
        EntityManager em =  GroupDataFactory.createEntityManager();
             
        Query q = em.createNamedQuery("Groups.getAllGroupsAndUsers");
        
        Type groupList = new TypeToken<List<QuizGroup>>(){}.getType();
        
        List<QuizGroup> queryResults = q.getResultList();
        
        json = JsonSerialiser.toJson(queryResults, groupList);
        
        
        em.close();
       
        return json;
    }    
    
    /**
     * Return group details only.  Exclude users.
     * 
     * @return 
     */
    
    public String fetchAllGroups()
    {
        Gson JsonSerialiser = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String json;
        
        EntityManager em =  GroupDataFactory.createEntityManager();
        
        Query q = em.createNamedQuery("Groups.getAllGroups");
        
        Type groupList = new TypeToken<List<QuizGroup>>(){}.getType();
        
        List<QuizGroup> queryResults = q.getResultList();
        
        json = JsonSerialiser.toJson(queryResults, groupList);
        
        em.close();
        
        return json;
    }
}
