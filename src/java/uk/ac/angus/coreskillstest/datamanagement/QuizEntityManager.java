package uk.ac.angus.coreskillstest.datamanagement;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JWO
 */
public class QuizEntityManager<T>
{
    private EntityManagerFactory EntityManagerFactory;
    
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
    
    private void commitObject(T objectToCommit)
    {
        
    }
    
    private void commitObjectList(List<T> objectsToCommit)
    {
        
    }
    
    private T getSingleObject(int objectId) throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
    {
        T object = null;
        
        EntityManager em = EntityManagerFactory.createEntityManager();
        try
        {
            object = (T) em.find(getType(), objectId);
        }catch(javax.persistence.NoResultException ex)
        {
            System.err.println("Locate resource by id: resource not found.  Object Type"+getType().getCanonicalName());
        }finally
        {
            em.close();
        }
        
        if(object == null)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Quiz resource not found: "+getType().getCanonicalName()+" by id: "+ objectId);
        }
        
        return object;
    }
    
    /**
     * Query q must be prepared in calling function
     * @param q
     * @return
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException 
     */
    private List<T> getObjectList(Query q) throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
    {
        List<T> objectList;
        
        objectList = q.getResultList();
        
        if(objectList.isEmpty())
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("No resources found: " + getType().getSimpleName());
        
        return objectList;
    }
}