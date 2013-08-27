
package uk.ac.angus.coreskillstest.datamanagement;

import uk.ac.angus.coreskillstest.entity.QuizGroup;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.GroupToJSONTypeAdaptor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;
import uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException;
import uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException;

/**
 *
 * @author JWO
 */
public class GroupDataAccessObject
{
    private EntityManagerFactory GroupDataFactory;
    
    // This should be moved out to suitable defaults file in due course.
    private static final int DEFAULT_GROUP_ID = 1;
       
    public GroupDataAccessObject()
    {
        GroupDataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");       
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
    
    /**
     * 
     * @param singleGroupJson 
     */
    public ServerClientResponse addSingleGroup(String singleGroupJson)
    {
        Gson JsonSerialiser = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        ServerClientResponse response = new ServerClientResponse();
        QuizEntityManager qem = new QuizEntityManager(QuizGroup.class);
        
        QuizGroup ug = (QuizGroup) JsonSerialiser.fromJson(singleGroupJson, QuizGroup.class);
        
        try 
        {
            qem.commitObject(ug);
        } catch (UnableToCommitException ex) 
        {
            System.err.println("Unable to add group to database");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error", "Unable to add group to database"));
            return response;            
        }
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setStatusMessage(ServerClientResponseFactory.formatSuccessJSON("Group Added", "The group has been added"));
        
        return response;
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
    public ServerClientResponse deleteGroup(int groupId)
    {
        ServerClientResponse response = new ServerClientResponse();
        QuizEntityManager qem = new QuizEntityManager(QuizGroup.class);
        String query = "Groups.findGroupById";
        HashMap parameters = new HashMap();
        
        parameters.put("id", new Integer(groupId));
        try 
        {
            qem.deleteObject(query, parameters);
        } catch (UnableToDeleteObjectException ex) 
        {
           System.err.println("Database Error: Cannot delete group");
           System.err.println(ex.getMessage());
           response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
           response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error", "Unable to delete selected group"));
           return response;
        }
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setStatusMessage(ServerClientResponseFactory.formatSuccessJSON("Group Deleted", "The selected group has been deleted."));
        
        return response;
    }
    
    /**
     * Delete multiple groups indicated by the groupIdList parameter.
     * 
     * Stub method: not implemented in the current version.
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
    public String fetchGroupByIdJSON(int groupId)
    {
        String json = "";
        
        return json;
    }
    
    /**
     * 
     * @param groupId
     * @return 
     */
    public QuizGroup fetchGroupByIdObject(int groupId)
    {
        QuizGroup qg;
        
        EntityManager em = GroupDataFactory.createEntityManager();
        
        qg = em.find(QuizGroup.class, groupId);
        
        return qg;
    }
    
    /**
     * Return all groups and users in JSON format.
     * 
     * @return 
     */
    public String fetchAllGroupsandUsersJSON()
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
     * 
     * @return 
     */
    public String fetchGroupDetailsJSON()
    {
        String json;
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(QuizGroup.class, new GroupToJSONTypeAdaptor());
        gb.excludeFieldsWithoutExposeAnnotation();
        Gson gsn = gb.create();
        
        EntityManager em = GroupDataFactory.createEntityManager();
        
        try
        {
            Query q = em.createNamedQuery("Groups.getAllGroups");
            Type groupDetails = new TypeToken<List<QuizGroup>>(){}.getType();
            
            List<QuizGroup> queryResults = q.getResultList();
            
            json = gsn.toJson(queryResults, groupDetails);
            
        }finally
        {
            em.close();
        }
        
        return json;
    }
    
    /**
     * 
     * @return 
     */
    public QuizGroup fetchAllGroupsandUsersObject()
    {
        QuizGroup qg = null;
        
        return qg;
    }
    
    /**
     * Return group details only.  Exclude users.
     * 
     * @return 
     */
    
    public String fetchAllGroupsJSON()
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
