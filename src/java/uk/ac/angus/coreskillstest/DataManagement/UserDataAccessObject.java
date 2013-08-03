
package uk.ac.angus.coreskillstest.datamanagement;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.ac.angus.coreskillstest.entity.QuizUser;
import uk.ac.angus.coreskillstest.entity.QuizGroup;

/**
 *
 * This class is responsible for receiving User and Group XML files, marshalling and unmarshalling 
 * and serialisation to the database.
 * 
 * @author JWO
 */

public class UserDataAccessObject 
{   
    private EntityManagerFactory factory;
    private Gson gsn;
    
    public UserDataAccessObject()
    {
        factory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        gsn = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();        
    }
    
    /**
     * add a single user to the system.
     * The user will be added to the default group, user can reassign as necessary
     * @param userJson
     * @return 
     */
    public boolean addSingleUser(String userJson)
    {
        QuizUser newUser = (QuizUser) gsn.fromJson(userJson, QuizUser.class);
              
        QuizGroup defaultGroup = GroupDataAccessObject.getDefaultGroup();
        
        if(defaultGroup == null)
        {
            GroupDataAccessObject.createDefaultGroup();
            return false;
        }
        
        newUser.setGroup(defaultGroup);
        
        //defaultGroup.getUserList().add(newUser);
        
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(newUser);
        
        em.getTransaction().commit();
        em.close();
        
        return true;
    }
    
    
    public boolean addMultipleUsers(List<String> userList)
    {
        
        return true;
    }
    
    public QuizUser fetchSingleUserObject(int userId)
    {
        QuizUser user;
        EntityManager em = factory.createEntityManager();
        try
        {
            TypedQuery <QuizUser> userQuery = em.createNamedQuery("Users.findUserById", QuizUser.class);
            userQuery.setParameter("id", userId);
            user = userQuery.getSingleResult();
        }catch (javax.persistence.NoResultException ex)
        {
            System.err.println("Unable to locate specified user");
            user = null;
        }finally
        {
             if(em.isOpen())
                em.close();
        }     
        return user;
    }
    
    public String fetchSingleUserJson(int userId)
    {
        String json;
        
        EntityManager em = factory.createEntityManager();
        
        TypedQuery <QuizUser> userQuery = em.createNamedQuery("Users.findUserById", QuizUser.class);
        
        userQuery.setParameter("id", userId);
        
        List <QuizUser> users = userQuery.getResultList();
        
        json = gsn.toJson(users);
        em.close();
        
        return json;
    }
    
    public List<QuizUser> fetchAllUsersObject()
    {
        List<QuizUser> users;
        
        EntityManager em = factory.createEntityManager();
        
        TypedQuery <QuizUser> userQuery = em.createNamedQuery("Users.findall", QuizUser.class);
        
        users  = userQuery.getResultList();
        
        return users;
    }
    
    public String fetchAllUsersJson()
    {
        String json;
        
        EntityManager em = factory.createEntityManager();
        
        TypedQuery <QuizUser> userQuery = em.createNamedQuery("Users.findall", QuizUser.class);
        
        List <QuizUser> users  = userQuery.getResultList();
        
        json = gsn.toJson(users);
        
        em.close();
        
        return json;     
    }
    
    public boolean deleteUser()
    {
        
        return true;
    }
    
    public boolean deleteMultipleUsers()
    {
        
        return true;
    }

}
