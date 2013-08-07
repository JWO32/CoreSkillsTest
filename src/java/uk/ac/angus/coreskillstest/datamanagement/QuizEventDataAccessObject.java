package uk.ac.angus.coreskillstest.datamanagement;

import com.google.gson.GsonBuilder;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;
/**
 *
 * @author JWO
 */
public class QuizEventDataAccessObject 
{
    private EntityManagerFactory QuizConfigEntityManager;
    
    public QuizEventDataAccessObject()
    {
        QuizConfigEntityManager = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
    }
    
    public QuizEvent getQuizEventByIdObject(int quizConfigId)
    {
        QuizEvent qc;
        
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        // Remember - returns null if no object is found - catch this!
        // TODO: may prove unreliable, provide a named query to implement this functionality
        qc =  em.find(QuizEvent.class, quizConfigId);
        
        return qc;
    }
    
    public String getQuizEventByIdJSON(int quizConfigid)
    {
        String json = null;
        
        return json;
    }
    
    public String getAllQuizEventsJSON()
    {
        String json = null;
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        GsonBuilder gb = new GsonBuilder();
        
        
        try
        {
            Query q = em.createNamedQuery("QuizEvent.getAllEvents");
            
            List<QuizEvent> eventList = q.getResultList();
            
            
            
        }finally
        {
            em.close();
        }
        
        return json;
    }
    
    public void addQuizEvent(QuizEvent quizConfig)
    {
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(quizConfig);
        em.getTransaction().commit();
    }
    
    public void deleteQuizEvent(int quizConfigId)
    {
        
    }
    
    public void editQuizEvent(int quizConfigId, QuizEvent amendedConfig)
    {
        
    } 
}
