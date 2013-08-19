package uk.ac.angus.coreskillstest.datamanagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * Implemented during refactoring to provide a consistent set of methods for all
 * data access objects.  Can be used in combination with the JSON interface so that
 * only JSON is returned to servlets and this is returned to the client.
 * 
 * All database interaction will go through this class.  Queries and parameters are
 * passed in to methods as strings and hashmaps, methods determine data types using 
 * Java type introspection.
 * 
 * Exceptions are thrown when errors occur so that appropriate messages can be returned
 * to the client.
 * @author JWO
 */
public class QuizEntityManager<T>
{
    private EntityManagerFactory EntityManagerFactory;
    String PersistenceUnit;
    
    private Class Type;
    
    public QuizEntityManager(Class type)
    {
        EntityManagerFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        
        Type = type;
    }
    
    /**
     * Allows the runtime class of the generic type to be determined for operations when
     * the class type is required, such as serialising and deserialising JSON.
     * @return 
     */
    private Class getType()
    {
        return Type.getClass();
    }
    
    /**
     * 
     * @param newPersistenceUnit 
     */
    public void setPersistenceUnit(String newPersistenceUnit)
    {
        PersistenceUnit = newPersistenceUnit;
    }
    
    /**
     * Takes an object of the generically specified type and serialises it to the database
     * 
     * Throws an UnableToCommitException is the operation is unsuccessful.
     * 
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException
     * @param objectToCommit 
     */
    public void commitObject(T objectToCommit) throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException
    {
        EntityManager em = EntityManagerFactory.createEntityManager();
        
        try
        {
            em.getTransaction().begin();
            em.persist(objectToCommit);
            em.getTransaction().commit();
        }catch(Exception ex)
        {
            System.err.println("Database Error: unable to commit to database");
            System.err.println(ex.getMessage());
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException("Database Error: Unable to commit "+getType().getSimpleName()+" object to database");
        }finally
        {
            em.close();
        }  
    }
    
    /**
     * 
     * @param objectsToCommit
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException 
     */
    public void commitObjectList(List<T> objectsToCommit) throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException
    {
        EntityManager em = EntityManagerFactory.createEntityManager();
        
        try
        {
            em.getTransaction().begin();
            for(T currentObject: objectsToCommit)
            {
                em.persist(currentObject);
            }
            em.getTransaction().commit();
            
        }catch(javax.persistence.RollbackException ex)
        {
            System.err.println("Database Error: Unable to commit mulitple objects to database");
            System.err.println(ex.getMessage());
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException("Database Error: unable to commit mulitple objects of type "+getType().getSimpleName());
        }finally
        {
            em.close();
        }
        
    }
    
    /**
     * 
     * @param query
     * @param parameters
     * @return
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException 
     */
    public T getSingleObjectByNonKey(String query, Map parameters) throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
    {
        T object;
        EntityManager em = EntityManagerFactory.createEntityManager();
        
        Query q;
        
        Object[] parameterKeys = parameters.keySet().toArray();
        Object value = null;
        
        try
        {
            q = em.createNamedQuery(query);
            
            for(Object currentKey : parameterKeys)
            {
                 String keyString = (String) currentKey;
                 Object valueObj = parameters.get(keyString);
                 
                if(String.class.isAssignableFrom(valueObj.getClass()))
                {
                    value = (String) valueObj;
                    
                }else if(Integer.class.isAssignableFrom(valueObj.getClass()))
                {                  
                    value = (Integer) valueObj;
                }
                q.setParameter(keyString, value);
            }
            
            object = (T) q.getSingleResult();
            
        }catch(javax.persistence.NoResultException ex)
        {
            System.err.println("Get single object: No result found for parameters");
            System.err.println(ex.getMessage());
            object = null;
        }finally
        {
            em.close();
        }
        
        if(object == null)
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Unable to locate object by non-key value");
        
        return object;
    }
    
    /**
     * Find a single object by the primary key value
     * 
     * @param objectId
     * @return
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException 
     */
    public T getSingleObjectByPrimaryKey(int objectId) throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
    {
        T object = null;
        
        EntityManager em = EntityManagerFactory.createEntityManager();
        try
        {
            object = (T) em.find(getType(), objectId);
        }catch(javax.persistence.NoResultException ex)
        {
            System.err.println("Locate resource by id: resource not found.  Object Type"+getType().getSimpleName());
        }finally
        {
            em.close();
        }
        
        if(object == null)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Quiz resource not found: "+getType().getSimpleName()+" by id: "+ objectId);
        }
        
        return object;
    }
    
    /**
     * Query q must be prepared in calling function with the appropriate named query
     * 
     * @param namedQuery
     * @return
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException 
     */
    public List<T> getObjectList(String namedQuery, Map queryParameters) throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
    {
        EntityManager em = EntityManagerFactory.createEntityManager();
        Query q;
        List<T> objectList = new ArrayList<>();
        q = em.createNamedQuery(namedQuery);
        Object value = null;
          
        //Get Keys     
        // Since there is the possibility that the map may contain mixed objects,
        // it's necessary to carry out some introspection on the objects to determine types
        // at the moment this is restricted to Strings and Calendars... will use the Adaptor
        // pattern if number of types increases significantly.
        if(queryParameters != null)
        {
            Object[] keys =  queryParameters.keySet().toArray();
            for(Object key : keys)
            {
                String currentKey = (String) key;
                Object valueObj = queryParameters.get(key);
                
                // Deal with possible value types from the hash map
                //
                if(String.class.isAssignableFrom(valueObj.getClass()))
                {
                    value = (String) valueObj;
                }else if(Calendar.class.isAssignableFrom(valueObj.getClass()))
                {
                    value = (Calendar) valueObj;
                }else if(Date.class.isAssignableFrom(valueObj.getClass()))
                {
                    value = (Date) valueObj;
                }else if(Integer.class.isAssignableFrom(valueObj.getClass()))
                {
                    value = (Integer) valueObj;
                }
                
                q.setParameter(currentKey, value);
            }
        }

        try
        {                       
            objectList = q.getResultList();
        }catch(Exception ex)
        {
            System.err.println("Exception Occured: unable to get object list");
            System.err.println(ex.getMessage());
        }finally
        {
            em.close();
        }
        
        if(objectList.isEmpty())
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("No resources found: " + getType().getSimpleName());
        
        return objectList;
    }
    
    /**
     * 
     * @param namedQuery
     * @param queryParameters
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToEditObjectException 
     */
    public void editObject(String namedQuery, Map queryParameters) throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToEditObjectException
    {
        
    }
    
    /**
     * 
     * @param namedQuery
     * @param queryParameters
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException 
     */
    public void deleteObject(String namedQuery, Map queryParameters) throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException
    {
        EntityManager em = EntityManagerFactory.createEntityManager();
        Query q;
        int numberOfItemsDeleted = 0;
        Object value = null;
        
         q = em.createNamedQuery(namedQuery);
        
        if(queryParameters != null)
        {
            Object[] keys = queryParameters.keySet().toArray();
            for(Object key : keys)
            {
                String currentKey = (String) key;
                
                Object parameterValue = queryParameters.get(currentKey);
                
                if(String.class.isAssignableFrom(parameterValue.getClass()))
                {
                    value = (String) parameterValue;
                }else if(Date.class.isAssignableFrom(parameterValue.getClass()))
                {
                    value = (Date) parameterValue;
                }else if(Integer.class.isAssignableFrom(parameterValue.getClass()))
                {
                    value = (Integer) parameterValue;
                }
                
                q.setParameter(currentKey, value);
            }  
        }
        
        try
        {
            em.getTransaction().begin();           
            numberOfItemsDeleted = q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex)
        {
            System.err.println("Exception Occured: unable to delete specified object");
            System.err.println(ex.getMessage());
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException("Database Error: Unable to delete object");  
        }finally
        {
            em.close();
        }
        
        if(numberOfItemsDeleted == 0)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException("Delete Error: no objects deleted");
        }
    }
}