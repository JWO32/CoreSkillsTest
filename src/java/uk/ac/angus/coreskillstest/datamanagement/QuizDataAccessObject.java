/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.datamanagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.Question;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import javax.persistence.Query;

import uk.ac.angus.coreskillstest.entity.QuestionTypeAdapter;
import uk.ac.angus.coreskillstest.entity.QuizTypeAdapter;


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
    
    public void addNewQuiz(String json)
    {
        EntityManager em = emf.createEntityManager();
        Quiz newQuiz;
        GsonBuilder gb = new GsonBuilder();
        
        gb.registerTypeAdapter(Quiz.class, new QuizTypeAdapter());        
        Gson g = gb.excludeFieldsWithoutExposeAnnotation().create();
        
        newQuiz = (Quiz) g.fromJson(json, Quiz.class);
        
        em.getTransaction().begin();
        em.persist(newQuiz);
        em.getTransaction().commit();
        em.close();  
    }
    
    public void deleteQuizById(int quizId)
    {
        
    }
    
    public void editQuizById(int quizId)
    {
        
    }
    
    public String getAllQuizzes()
    {
        String json = " ";
        
        return json;
    }
    
    /**
     * Return a single Quiz object by the selected id in JSON format.
     * 
     * @param id
     * @return 
     */
    public String getQuizById(int id)
    {
        String json = " ";
        EntityManager em = emf.createEntityManager();
        
        Quiz returnQuiz;
        
        Query q = em.createNamedQuery("Quiz.getQuizById");
        q.setParameter("id", id);
        
        returnQuiz = (Quiz) q.getSingleResult();
               
        return json;
    }
    
}
