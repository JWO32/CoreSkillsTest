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

import java.util.ArrayList;
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
    private Gson JsonSerialiser;
    
    // This should be moved out to suitable defaults file in due course.
    private static final int DEFAULT_GROUP_ID = 1;
    
    
    public GroupDataAccessObject()
    {
        GroupDataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        JsonSerialiser = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
    
    //TODO: Remove this method and replace with a defaults object to create essential data.
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
    
    //Encompass this in a test case  
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
        EntityManager em = GroupDataFactory.createEntityManager();
        QuizGroup ug = null;
    
        ug = (QuizGroup) JsonSerialiser.fromJson(singleGroupJson, QuizGroup.class);
        
        em.getTransaction().begin();
        em.persist(ug);
        em.getTransaction().commit();
        em.close();
    }
    
    public void addMultipleGroups(String multipleGroupJson)
    {
        
    }
    
    public void deleteSingleGroup(int groupId)
    {
        
    }
    
    public void deleteMultipleGroups(int[] groupIdList)
    {
        
    }
    
    public String fetchGroupById(int groupId)
    {
        String json = "";
        
        return json;
    }
    
    public String fetchAllGroupsandUsers()
    {
        String json;
        
        EntityManager em =  GroupDataFactory.createEntityManager();
             
        Query q = em.createNamedQuery("Groups.getAllGroupsAndUsers");
        
        Type groupList = new TypeToken<List<QuizGroup>>(){}.getType();
        
        List<QuizGroup> queryResults = q.getResultList();
        
        json = JsonSerialiser.toJson(queryResults, groupList);
        
        em.close();
       
        return json;
    }    
    
    public String fetchAllGroups()
    {
        String json;
        
        EntityManager em =  GroupDataFactory.createEntityManager();
        
        Query q = em.createNamedQuery("Groups.getAllGroups");
        
        Type groupList = new TypeToken<List<QuizGroup>>(){}.getType();
        
        List<QuizGroup> queryResults = q.getResultList();
        
        json = JsonSerialiser.toJson(queryResults, groupList);
        
        return json;
    }
}
