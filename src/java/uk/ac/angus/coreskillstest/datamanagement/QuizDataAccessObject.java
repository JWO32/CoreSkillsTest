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
        GsonBuilder gb = new GsonBuilder();//.excludeFieldsWithoutExposeAnnotation().create();
        gb.registerTypeAdapter(Quiz.class, new QuizTypeAdapter());
        gb.registerTypeAdapter(Question.class, new QuestionTypeAdapter());
        
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
    
    public String getQuizById(int id)
    {
        String json = " ";
        
        return json;
    }
    
}
