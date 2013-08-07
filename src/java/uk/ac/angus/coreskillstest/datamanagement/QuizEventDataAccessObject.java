package uk.ac.angus.coreskillstest.datamanagement;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
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
    
    public QuizEvent getQuizConfigurationById(int quizConfigId)
    {
        QuizEvent qc;
        
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        // Remember - returns null if no object is found - catch this!
        // TODO: may prove unreliable, provide a named query to implement this functionality
        qc =  em.find(QuizEvent.class, quizConfigId);
        
        return qc;
    }
    
    public void addQuizConfiguration(QuizEvent quizConfig)
    {
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(quizConfig);
        em.getTransaction().commit();
    }
    
    public void deleteQuizConfiguration(int quizConfigId)
    {
        
    }
    
    public void editQuizConfiguration(int quizConfigId, QuizEvent amendedConfig)
    {
        
    } 
}
