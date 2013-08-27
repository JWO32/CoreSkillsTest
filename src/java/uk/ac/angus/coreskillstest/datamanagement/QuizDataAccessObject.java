
package uk.ac.angus.coreskillstest.datamanagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.ArrayList;

import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;

import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizDetailsFromJSONTypeAdapter;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizDetailsToJSONTypeAdapter;
import uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException;
import uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException;
import uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToEditObjectException;


/**
 *
 * @author JWO
 */

//TODO: Refactor and implement JSON interface for consistency of access.
//
public class QuizDataAccessObject 
{
    private EntityManagerFactory QuizEntityManagerFactory;
    
    
    public QuizDataAccessObject()
    {
        QuizEntityManagerFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
    }
    
    public void addNewQuizByJson(String json) throws uk.ac.angus.coreskillstest.quizmanagement.exception.UnabletoAddResourceException
    {
        EntityManager em = QuizEntityManagerFactory.createEntityManager();
        Quiz newQuiz;
        GsonBuilder gb = new GsonBuilder();
        boolean serialiseSuccess = true;
        
        gb.registerTypeAdapter(Quiz.class, new QuizDetailsFromJSONTypeAdapter());        
        Gson g = gb.excludeFieldsWithoutExposeAnnotation().create();
        
        newQuiz = (Quiz) g.fromJson(json, Quiz.class);
        
        //Ensure the total number of marks is calculated.
        newQuiz.calcTotalMarks();
        
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
    
    public ServerClientResponse deleteQuizById(int quizId)
    {
        ServerClientResponse response = new ServerClientResponse();
        QuizEntityManager qem = new QuizEntityManager(Quiz.class);
        String deleteQuery = "Quiz.getQuizById";
        HashMap deleteParameters = new HashMap();
        
        deleteParameters.put("id", quizId);
        try 
        {
            qem.deleteObject(deleteQuery, deleteParameters);
        } catch (UnableToDeleteObjectException ex) 
        {
            System.err.println("Error: unable to delete quiz");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Quiz Delete Error", "Unable to delete selected quiz"));
            return response;
        }
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setStatusMessage(ServerClientResponseFactory.formatSuccessJSON("Delete Successful", "The quiz has been deleted."));
        
        return response;
    }
    
    /**
     * Fetch a single quiz for the client.
     * @param quizId
     * @return 
     */
    public ServerClientResponse getQuizById(int quizId)
    {
        ServerClientResponse response = new ServerClientResponse();
        QuizEntityManager qem = new QuizEntityManager(Quiz.class);
        GsonBuilder gb = new GsonBuilder();
        gb.excludeFieldsWithoutExposeAnnotation();
        Gson gsn = gb.create();
        String json;
        
        String getQuery = "Quiz.getQuizById";
        Quiz returnedQuiz;
        
        HashMap parameters = new HashMap();
        
        parameters.put("id", new Integer(quizId));
        
        try
        {
            returnedQuiz = (Quiz) qem.getSingleObjectByNonKey(getQuery, parameters);
        } catch (QuizResourceNotFoundException ex) 
        {
            System.err.println("Edit Quiz: Unable to find quiz");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Unable to get Quiz", "Server was not able to find quiz"));
            return response;
        }
        
        json = gsn.toJson(returnedQuiz);
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setClientJson(json);
        
        return response;
    }
    
    public ServerClientResponse editQuizById(int quizId, String amendedQuizJson)
    {
        ServerClientResponse response = new ServerClientResponse();
        QuizEntityManager qem = new QuizEntityManager(Quiz.class);
        
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Quiz.class, new QuizDetailsFromJSONTypeAdapter());        
        Gson g = gb.excludeFieldsWithoutExposeAnnotation().create();

        Quiz editedQuiz = (Quiz) g.fromJson(amendedQuizJson, Quiz.class);
        
        try
        {
            qem.editObjectbyId(quizId, editedQuiz, Quiz.class);
        } catch (UnableToEditObjectException ex) 
        {
            System.err.println("Unable to edit quiz");
            System.err.println(ex.getMessage());
        }
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setStatusMessage(ServerClientResponseFactory.formatSuccessJSON("Edit Success", "The quiz has been successfully edited."));
        
        return response;
    }
    
    /**
     *  Select all Quiz Objects and return to Servlet for client.
     * 
     * @return 
     */
    public ServerClientResponse getAllQuizzes() throws QuizResourceNotFoundException
    {
        QuizEntityManager qem = new QuizEntityManager(Quiz.class);
        String query = "Quiz.getAllQuizzes";
        ServerClientResponse response = new ServerClientResponse();
        List<Quiz> allQuizzes;
        Type quizType = new TypeToken<List<Quiz>>(){}.getType();
        GsonBuilder gbuilder = new GsonBuilder();
        gbuilder.excludeFieldsWithoutExposeAnnotation();
        gbuilder.registerTypeAdapter(Quiz.class, new QuizDetailsToJSONTypeAdapter());
        String quizListJSON;
        Gson g = gbuilder.create();
        
        try
        {
            allQuizzes = qem.getObjectList(query, null);
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            System.err.println("Cannot find quizzes");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error", "Unable to find quizzes"));
            return response;
        }
        
        if(allQuizzes.isEmpty())
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("No quizzes found.");
        
        quizListJSON = g.toJson(allQuizzes, quizType);
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setClientJson(quizListJSON);
        
        return response;
    }
    
    /**
     * Refactor this method to use QuizEntityManager class
     * @return 
     */
    public ServerClientResponse getShortQuizList()
    {
        String json;
        ServerClientResponse response = new ServerClientResponse();
        EntityManager em = QuizEntityManagerFactory.createEntityManager();
        List<Quiz> allQuizzes = new ArrayList<>();
        
        try
        {
            Query q = em.createNamedQuery("Quiz.getAllQuizzes");
            
            allQuizzes = q.getResultList();
            
        }catch(javax.persistence.NoResultException ex)
        {
            System.err.println("No Quizzes found");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Unable to get Quiz List", "Unable to fetch list of quizzes from the database."));
            return response;
        }finally
        {
            if(em.isOpen())
                em.close();
        }
        
        //Probably better to remove this to another function
        //
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Quiz.class, new QuizDetailsToJSONTypeAdapter());
        
        Gson g = gb.create();
        
        json = g.toJson(allQuizzes);
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setClientJson(json);
        
        return response;
    }
    
    /**
     * Return a single Quiz object by the selected id
     * 
     * @param id
     * @return 
     */
    public Quiz getQuizObjectById(int id) throws javax.persistence.NoResultException
    {
        EntityManager em = QuizEntityManagerFactory.createEntityManager();    
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
