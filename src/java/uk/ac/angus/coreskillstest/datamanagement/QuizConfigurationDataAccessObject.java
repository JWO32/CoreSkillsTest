package uk.ac.angus.coreskillstest.datamanagement;


import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizConfiguration;
/**
 *
 * @author JWO
 */
public class QuizConfigurationDataAccessObject 
{
    private EntityManagerFactory QuizConfigEntityManager;
    
    public QuizConfigurationDataAccessObject()
    {
        QuizConfigEntityManager = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
    }
    
    public QuizConfiguration getQuizConfigurationById(int quizConfigId)
    {
        QuizConfiguration qc;
        
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        // Remember - returns null if no object is found - catch this!
        // TODO: may prove unreliable, provide a named query to implement this functionality
        qc =  em.find(QuizConfiguration.class, quizConfigId);
        
        return qc;
    }
    
    public void addQuizConfiguration(QuizConfiguration quizConfig)
    {
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(quizConfig);
        em.getTransaction().commit();
    }
    
    public void deleteQuizConfiguration(int quizConfigId)
    {
        
    }
    
    public void editQuizConfiguration(int quizConfigId, QuizConfiguration amendedConfig)
    {
        
    } 
}
