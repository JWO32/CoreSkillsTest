/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.datamanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizUser;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizEventSerialiseTypeAdapter;
import uk.ac.angus.coreskillstest.quizmanagement.QuizPreparation;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;

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
     * 
     * @param quizId 
     */
    public void getQuizForEvent(int quizId)
    {
        
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
        gb.registerTypeAdapter(QuizEvent.class, new QuizEventSerialiseTypeAdapter());
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
        }catch (uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            System.err.println("Quiz Event: No quiz events found.");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("No Quiz Events Found", "No quiz events were found"));
            eventList = null;
        }
        
        eventJson = g.toJson(eventList, eventType);
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setClientJson(eventJson);
        
        return response;
    }
    
}