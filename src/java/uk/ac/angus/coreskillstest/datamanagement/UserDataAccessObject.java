
package uk.ac.angus.coreskillstest.datamanagement;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;

import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;
import uk.ac.angus.coreskillstest.entity.QuizUser;
import uk.ac.angus.coreskillstest.entity.QuizGroup;
import uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException;
import uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException;
import uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException;

/**
 *
 * This class is responsible for receiving User and Group XML files, marshalling and unmarshalling 
 * and serialisation to the database.
 * 
 * TODO: Refactor and implement JSON interface to allow cleaner separation between Data marshalling/conversion of objects
 * and database access object.
 * @author JWO
 */

public class UserDataAccessObject 
{   
    private EntityManagerFactory UserDataFactory;
    private Gson gsn;
    
    public UserDataAccessObject()
    {
        UserDataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        gsn = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();        
    }
    
    /**
     * add a single user to the system.
     * The user will be added to the default group, user can reassign as necessary
     * @param userJson
     * @return 
     */
    public ServerClientResponse addSingleUser(String userJson, int groupId)
    {
        ServerClientResponse response = new ServerClientResponse();
        QuizEntityManager userQEM = new QuizEntityManager(QuizUser.class);
        QuizEntityManager groupQEM = new QuizEntityManager(QuizGroup.class);
        
        QuizUser user = (QuizUser) gsn.fromJson(userJson, QuizUser.class);
        
        QuizGroup group;
        
        try 
        {
            group = (QuizGroup) groupQEM.getSingleObjectByPrimaryKey(groupId);
        } catch (QuizResourceNotFoundException ex) 
        {
            System.err.println("Unable to add user - group cannot be found, attempting to set default group");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error", "Cannot add user, group not found"));
            // Attemp to set the default group, if this is not possible, return the error response to the client, otherwise continue.
            group = GroupDataAccessObject.getDefaultGroup();
            
            if(group == null)
                return response;
        }
        
        user.setGroup(group);
        
        try 
        {
            userQEM.commitObject(user);
        } catch (UnableToCommitException ex) 
        {
            System.err.println("Unable to add user - database error");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error", "Cannot add user, database error"));
        }
        
         response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
         response.setStatusMessage(ServerClientResponseFactory.formatSuccessJSON("User Added", "The user has been added"));
        
        return response;
    }
    
    /**
     * This will involve adding a list of students from a source such as a 
     * CSV file.
     * 
     * Stub - not required in current version.
     * @param userList
     * @return 
     */
    public boolean addMultipleUsers(List<String> userList)
    {
        
        return true;
    }
    
    /**
     * Refactor to use the QuizEntityManager 
     * @param userId
     * @return 
     */
    public QuizUser fetchSingleUserObject(int userId)
    {
        QuizUser user;
        EntityManager em = UserDataFactory.createEntityManager();
        try
        {
            TypedQuery <QuizUser> userQuery = em.createNamedQuery("Users.findUserById", QuizUser.class);
            userQuery.setParameter("id", userId);
            user = userQuery.getSingleResult();
        }catch (javax.persistence.NoResultException ex)
        {
            System.err.println("Unable to locate specified user");
            System.err.println(ex.getMessage());
            user = null;
        }finally
        {
             if(em.isOpen())
                em.close();
        }     
        return user;
    }
    
    /**
     * TODO: Refactor to use the QuizEntityManger for uniform database access
     * 
     * @param userId
     * @return 
     */
    public String fetchSingleUserJson(int userId)
    {
        String json;
        
        EntityManager em = UserDataFactory.createEntityManager();
        
        TypedQuery <QuizUser> userQuery = em.createNamedQuery("Users.findUserById", QuizUser.class);
        
        userQuery.setParameter("id", userId);
        
        List <QuizUser> users = userQuery.getResultList();
        
        json = gsn.toJson(users);
        em.close();
        
        return json;
    }
    
    /**
     * TODO: refactor to use QuizEntityManager for uniform database access
     * @return 
     */
    public List<QuizUser> fetchAllUsersObject()
    {
        List<QuizUser> users;
        
        EntityManager em = UserDataFactory.createEntityManager();
        
        TypedQuery <QuizUser> userQuery = em.createNamedQuery("Users.findall", QuizUser.class);
        
        users  = userQuery.getResultList();
        
        return users;
    }
    
    /**
     * TODO: refactor to use QuizEnityManager for uniform database access
     * @return 
     */
    public String fetchAllUsersJson()
    {
        String json;
        
        EntityManager em = UserDataFactory.createEntityManager();
        
        TypedQuery <QuizUser> userQuery = em.createNamedQuery("Users.findall", QuizUser.class);
        
        List <QuizUser> users  = userQuery.getResultList();
        
        json = gsn.toJson(users);
        
        em.close();
        
        return json;     
    }
    
    /**
     * Refactored to use the QuizEntityManager()
     * @return 
     */
    public ServerClientResponse deleteUser(int userId)
    {
        ServerClientResponse response = new ServerClientResponse();
        QuizEntityManager qem = new QuizEntityManager(QuizUser.class);
        String query = "Users.findUserById";
        HashMap queryParams = new HashMap();
        
        queryParams.put("id", new Integer(userId));
        try 
        {
            qem.deleteObject(query, queryParams);
        } catch (UnableToDeleteObjectException ex) 
        {
            System.err.println("Unable to delete user");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error", "Unable to delete user"));
            return response;
        }
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setStatusMessage(ServerClientResponseFactory.formatSuccessJSON("Delete Successful", "User has been deleted"));
        
        return response;
    }
    
    /**
     * Stub method - not implemented in the current version
     * 
     * @return 
     */
    public ServerClientResponse deleteMultipleUsers()
    {
        ServerClientResponse response = new ServerClientResponse();
        
        return response;
    }

}
