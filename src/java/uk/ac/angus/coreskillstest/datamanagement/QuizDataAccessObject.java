
package uk.ac.angus.coreskillstest.datamanagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.ArrayList;

import uk.ac.angus.coreskillstest.entity.Quiz;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import javax.persistence.Query;

import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizDeserialiseTypeAdapter;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizDetailsDeserialiseTypeAdaptor;


/**
 *
 * @author JWO
 */
public class QuizDataAccessObject 
{
    private EntityManagerFactory emf;
    
    
    public QuizDataAccessObject()
    {
        emf = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
    }
    
    public void addNewQuizByJson(String json) throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnabletoAddResourceException
    {
        EntityManager em = emf.createEntityManager();
        Quiz newQuiz;
        GsonBuilder gb = new GsonBuilder();
        boolean serialiseSuccess = true;
        
        gb.registerTypeAdapter(Quiz.class, new QuizDeserialiseTypeAdapter());        
        Gson g = gb.excludeFieldsWithoutExposeAnnotation().create();
        
        newQuiz = (Quiz) g.fromJson(json, Quiz.class);
        
        try
        {
            em.getTransaction().begin();
            em.persist(newQuiz);
            em.getTransaction().commit(); 
        }catch (javax.persistence.EntityExistsException ex)
        {
            System.err.println("Unable to write Quiz object -- already exists");
            serialiseSuccess = false;
        }catch(javax.persistence.TransactionRequiredException ex)
        {
           System.err.println("Unable to write Quiz Object -- transaction required");
           serialiseSuccess = false;
        }finally
        {
            if(em.isOpen())
                em.close();
        }        
        
        if(serialiseSuccess == false)
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.UnabletoAddResourceException("Serialisation Error: Unable to Add Quiz to database");
    }
    
    public boolean addNewQuizbyObject(Quiz quiz)
    {
        
        return true;
    }
    
    public void deleteQuizById(int quizId)
    {
        
    }
    
    public void editQuizById(int quizId)
    {
        
    }
    
    /**
     *  Select all Quiz Objects and return
     * 
     * @return 
     */
    public List<Quiz> getAllQuizzes()
    {
        List<Quiz> allQuizzes = new ArrayList<>();
        
        return allQuizzes;
    }
    
    public String getShortQuizList()
    {
        String json;
        EntityManager em = emf.createEntityManager();
        List<Quiz> allQuizzes = new ArrayList<>();
        
        try
        {
            Query q = em.createNamedQuery("Quiz.getAllQuizzes");
            
            allQuizzes = q.getResultList();
            
        }catch(javax.persistence.NoResultException ex)
        {
            System.err.println("No Quizzes found");
        }finally
        {
            if(em.isOpen())
                em.close();
        }
        
        //Probably better to remove this to another function
        //
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Quiz.class, new QuizDetailsDeserialiseTypeAdaptor());
        
        Gson g = gb.create();
        
        json = g.toJson(allQuizzes);
        
        return json;
    }
    
    /**
     * Return a single Quiz object by the selected id
     * 
     * @param id
     * @return 
     */
    public Quiz getQuizById(int id) throws javax.persistence.NoResultException
    {
        EntityManager em = emf.createEntityManager();    
        Quiz returnQuiz;
        
        try
        {
            Query q = em.createNamedQuery("Quiz.getQuizById");
            q.setParameter("id", id);
        
            returnQuiz = (Quiz) q.getSingleResult();  
        }catch(javax.persistence.NoResultException ex)
        {
            System.err.println("Unable to locate specified quiz");
            returnQuiz = null;
        }finally
        {
            if(em.isOpen())
                em.close();
        }
       
        return returnQuiz;
    }
}
