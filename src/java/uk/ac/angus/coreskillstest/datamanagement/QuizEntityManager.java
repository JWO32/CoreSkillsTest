package uk.ac.angus.coreskillstest.datamanagement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * Implemented during refactoring to provide a consistent set of methods for all
 * data access objects.  Can be used in combination with the JSON interface so that
 * only JSON is returned to servlets and this is returned to the client.
 * @author JWO
 */
public class QuizEntityManager<T>
{
    private EntityManagerFactory EntityManagerFactory;
    String persistenceUnit;
    
    private Class Type;
    
    public QuizEntityManager(Class type)
    {
        EntityManagerFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        
        Type = type;
    }
    
    /**
     * Allows the runtime class of the generic type to be determined
     * @return 
     */
    private Class getType()
    {
        return Type.getClass();
    }
    
    /**
     * 
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
        }catch(javax.persistence.RollbackException ex)
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
     * Find a single object by the primary key value
     * 
     * @param objectId
     * @return
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException 
     */
    public T getSingleObject(int objectId) throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
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
    public List<T> getObjectList(String namedQuery) throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
    {
        EntityManager em = EntityManagerFactory.createEntityManager();
        Query q;
        List<T> objectList = new ArrayList<>();
        
        try
        {          
            q = em.createNamedQuery(namedQuery);
            objectList = q.getResultList();
        }catch(Exception ex)
        {
            System.err.println("Exception Occured: unable to get object list");
        }finally
        {
            em.close();
        }
        
        if(objectList.isEmpty())
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("No resources found: " + getType().getSimpleName());
        
        return objectList;
    }
    
    public void deleteObject(String deleteQuery, String parameter, int objectId) throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException
    {
        EntityManager em = EntityManagerFactory.createEntityManager();
        Query q;
        int numberOfItemsDeleted = 0;
        
        try
        {
            em.getTransaction().begin();
            q = em.createNamedQuery(deleteQuery).setParameter(parameter, objectId);
            numberOfItemsDeleted = q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex)
        {
            System.err.println("Exception Occured: unable to delete specified object");
            System.err.println(ex.getMessage());
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