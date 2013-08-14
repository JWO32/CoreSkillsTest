/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.datamanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizMessage;
import uk.ac.angus.coreskillstest.entity.QuizUser;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizEventDetailsDeserialiseTypeAdapter;
import uk.ac.angus.coreskillstest.quizmanagement.QuizPreparation;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizPackage;

/**
 *
 * @author JWO
 */
public class QuizDispatcher 
{
    private Quiz SelectedQuiz;
    private QuizEvent SelectedQuizEvent;
    private QuizUser SelectedUser;
    private QuizPreparation QuizPrepare;
    
    public QuizDispatcher()
    {
        
    }
    
    public QuizDispatcher(Quiz newQuiz, QuizEvent newQuizEvent, QuizUser newUser)
    {
        SelectedQuiz = newQuiz;
        SelectedQuizEvent = newQuizEvent;
        SelectedUser = newUser;
        
        QuizPrepare = new QuizPreparation(SelectedQuiz, SelectedQuizEvent);
    }
   
    public void setQuiz(Quiz newQuiz)
    {
        SelectedQuiz = newQuiz;
    }
    
    public Quiz getQuiz()
    {
        return SelectedQuiz;
    }
    
    public void setQuizEvent(QuizEvent newQuizEvent)
    {
        SelectedQuizEvent = newQuizEvent;
    }
    
    public QuizEvent getQuizEvent()
    {
        return SelectedQuizEvent;
    }
    
    public void setUser(QuizUser newUser)
    {
        SelectedUser = newUser;
    }
    
    public QuizUser getUser()
    {
        return SelectedUser;
    }
    
    /**
     * 
     * @param email 
     */
    public void getUserByEmail(String email)
    {
        QuizEntityManager<QuizUser> user = new QuizEntityManager<>(QuizUser.class);
        String query = "Users.findUserByEmail";
        String parameterId = "email";
        String parameterValue = email;
        
        Map params = new HashMap();
        
        params.put(parameterId, parameterValue);
        try
        {
            SelectedUser = user.getSingleObjectByNonKey(query, params);
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            System.err.println("Database Error: No user returned when searching by e-mail");
            System.err.println(ex.getMessage());
            SelectedUser = null;
        }   
    }
    
    /**
     * Takes a quizEventId and returns the associated Quiz object as JSON.
     * 
     * TODO: QuizId is not necessary for this stage of the process as working
     * from the specified quiz event.  Other details from quiz event are necessary so
     * QuizEventId is required.
     * 
     * Final stage of Quiz dispatch process.
     * 
     * @param quizId 
     */
    public QuizPackage getQuizForEvent(int userId, int eventId)
    {
        QuizEntityManager<QuizEvent> quizEvent = new QuizEntityManager<>(QuizEvent.class);
        GsonBuilder gb = new GsonBuilder();
        gb.excludeFieldsWithoutExposeAnnotation();
        String quizJson;
        QuizPackage quizPackage = new QuizPackage();
        QuizPreparation prep = new QuizPreparation();
        
        Gson g = gb.create();
        
        QuizEvent qe;
        Quiz q;
        Map parameters = new HashMap();
        String query = "QuizEvent.getQuizByEventId";
        parameters.put("id", new Integer(eventId));
        
        try
        {
            qe = quizEvent.getSingleObjectByNonKey(query, parameters);         
            q = qe.getLinkedQuiz();
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            System.err.println("Database Error: Unable to locate Quiz by id");
            System.err.println(ex.getMessage());
            // If we get an error, set the package to null and let the client deal with the error.
            //
            quizPackage.setStartMessage(null);
            quizPackage.setEndMessage(null);
            quizPackage.setQuizJSON(null);
            return quizPackage;
        }
        
        q.calcTotalMarks();
        
        prep.setQuiz(q);
        prep.setQuizEvent(qe);
        try
        {
            q = prep.processQuiz();
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.CannotGenerateQuizException ex)
        {
            System.err.println("Quiz Generation Error: Cannot process quiz");
            
            quizPackage.setStartMessage(null);
            quizPackage.setEndMessage(null);
            quizPackage.setQuizJSON(null);
            return quizPackage;
        }
           
        if(q.getQuizMessages().isEmpty())
        {
            quizPackage.setStartMessage(QuizPackage.setDefaultStartMessage());
            quizPackage.setEndMessage(QuizPackage.setDefaultEndMessage());               
        }else
        {
            //Find and set start quiz message then find and set end quiz message
        
        }
        
        quizJson = g.toJson(q, Quiz.class);
        
        // Complete configuration of quiz package by setting event and user id
        //
        quizPackage.setEventId(eventId);
        quizPackage.setUserId(userId);
        quizPackage.setQuizJSON(quizJson);

        return quizPackage;
    }
    
    /**
     * 
     * @return 
     */
    public ServerClientResponse getValidQuizEventsForUser()
    {
        ServerClientResponse response = new ServerClientResponse();
        
        if(SelectedUser == null)
        {
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("No User Found", "Unable to search for Quiz Events because no user found"));
            
            return response;
        }
 
        QuizEntityManager<QuizEvent> qem = new QuizEntityManager<>(QuizEvent.class);       
        List<QuizEvent> eventList;

        String eventJson;
        Type eventType = new TypeToken<List<QuizEvent>>(){}.getType();
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(QuizEvent.class, new QuizEventDetailsDeserialiseTypeAdapter());
        gb.excludeFieldsWithoutExposeAnnotation();
        
        Gson g = gb.create();
        
        String query = "QuizEvent.getLiveEventsByEmail";
        
        String emailParam = "email";
        String emailValue = SelectedUser.getUserEmail();
        
        String dateParam = "date";
        Date dateValue = new Date();
        
        Map paramMap = new HashMap();
        
        try
        {
            paramMap.put(emailParam, emailValue);
            paramMap.put(dateParam, dateValue);
            eventList = qem.getObjectList(query, paramMap);
            
            // Surgically attach the user id to the QuizEvent object
            // Bit of a kludge but necessary because at the moment
            // Groups are associate with QuizEvents, not individual students
            // Future change will be to allow QuizEvents to be set for individual
            // Students
            for(QuizEvent currentEvent: eventList)
            {
                currentEvent.setUserId(SelectedUser.getUserId());
            }
            
        }catch (uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            System.err.println("Quiz Event: No quiz events found.");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("No Quiz Events Found", "No quiz events were found"));
            return response;
        }
        
        eventJson = g.toJson(eventList, eventType);
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setClientJson(eventJson);
        
        return response;
    }
    
}
