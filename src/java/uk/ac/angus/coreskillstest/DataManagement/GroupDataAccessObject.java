/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.DataManagement;

import uk.ac.angus.coreskillstest.entity.UserGroup;

import com.google.gson.Gson;
 
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author JWO
 */
public class GroupDataAccessObject 
{
    private EntityManagerFactory groupDataFactory;
    private Gson jsonSerialiser;
    
    // This should be moved out to suitable defaults file in due course.
    private static final int DEFAULT_GROUP_ID = 1;
    
    
    public GroupDataAccessObject()
    {
        groupDataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        jsonSerialiser = new Gson();
    }
    
    public static UserGroup getDefaultGroup()
    {
        UserGroup ug;
        EntityManagerFactory DataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        
        EntityManager em = DataFactory.createEntityManager();
        
        ug = (UserGroup) em.find(UserGroup.class, GroupDataAccessObject.DEFAULT_GROUP_ID);
        
        em.close();
        
        if(ug == null)
            return null;
        else
            return ug;
    }
    
    public static void createDefaultGroup()
    {
        UserGroup ug = new UserGroup("Default Group", "This is the default group for users who are not otherwise assigned to a group");
        EntityManagerFactory DataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        
        EntityManager em = DataFactory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(ug);
        em.getTransaction().commit();
        em.close();
    }
    
    public void addSingleGroup(String singleGroupJson)
    {
        
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
    
    public String fetchAllGroups()
    {
        String json = "";
        
        
        return json;
    }    
}
