package uk.ac.angus.coreskillstest.datamanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;


import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizEventDetailsToJSONTypeAdapter;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizEventFromJSONTypeAdapter;
import uk.ac.angus.coreskillstest.datamanagement.clientinterface.JSONInterface;

/**
 * Handles requests for data for the QuizEventController.  Provides parameters to 
 * 
 * TODO: Remove commented code if it is clear there will be no -ve effects.
 * @author JWO
 */
public class QuizEventDataAccessObject implements JSONInterface<QuizEvent>
{ 
    /**
     * 
     */
    public QuizEventDataAccessObject()
    {
        
    }
    
    /**
     * This method is implemented outside of the interface for convenience.
     * 
     * TODO: Refactor to use QuizEventManager
     * @param quizConfigId
     * @return 
     */
    public QuizEvent getQuizEventByIdObject(int quizConfigId)
    {
        QuizEvent qc;
        EntityManagerFactory QuizConfigEntityManager = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        // Remember - returns null if no object is found - catch this!
        // TODO: may prove unreliable, provide a named query to implement this functionality
        qc =  em.find(QuizEvent.class, quizConfigId);
        
        return qc;
    }

    //Implement interface functionality here...
    // REFACTORING STARTS HERE
    //
    @Override
    public ServerClientResponse addItemJson(String jsonString) 
    {
        QuizEntityManager<QuizEvent> qem = new QuizEntityManager<>(QuizEvent.class);
        ServerClientResponse outcome = new ServerClientResponse();
        
        GsonBuilder gsb = new GsonBuilder();
        
        gsb.registerTypeAdapter(QuizEvent.class, new QuizEventFromJSONTypeAdapter());
        
        Gson gsn = gsb.create();
        
        QuizEvent qe;
        
        qe = gsn.fromJson(jsonString, QuizEvent.class);
        try
        {
            qem.commitObject(qe);
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToCommitException ex)
        {          
            outcome.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            outcome.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error", ex.getMessage()));
            
            return outcome;
        }
        
        outcome.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        outcome.setStatusMessage(ServerClientResponseFactory.formatSuccessJSON("Event Added", "Quiz Event has been added successfully."));
        
        return outcome;      
    }

    @Override
    public ServerClientResponse addItemsJson(String jsonArrayString) 
    {
        ServerClientResponse response = null;
        
        return response;
    }

    @Override
    public ServerClientResponse getAllItems() 
    {
        ServerClientResponse response = new ServerClientResponse();
        String responseJson;
        List<QuizEvent> returnedEvents;
        QuizEntityManager<QuizEvent> qem = new QuizEntityManager<>(QuizEvent.class);
        Type eventType = new TypeToken<List<QuizEvent>>(){}.getType();
        
        GsonBuilder gb = new GsonBuilder();
        gb.excludeFieldsWithoutExposeAnnotation();
        gb.registerTypeAdapter(QuizEvent.class, new QuizEventDetailsToJSONTypeAdapter());
        
        Gson gsn = gb.create();
        
        try
        {
            String query = "QuizEvent.getAllEvents";
            
            returnedEvents = qem.getObjectList(query, null);
            
            if(returnedEvents.isEmpty())
            {
                response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
                response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("No Records Found", "There are currently no Quiz Events"));
                response.setClientJson(ServerClientResponseFactory.EMPTY_JSON_OBJECT);        
            }else
            {              
                responseJson = gsn.toJson(returnedEvents, eventType);
                //TODO: Create a response factory to generate these simple objects
                response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
                response.setClientJson(responseJson);
            }         
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error Fetching Quiz Events", "Unable to fetch Quiz Events"));
            response.setClientJson(ServerClientResponseFactory.EMPTY_JSON_OBJECT);
        }
        
        return response;
    }

    @Override
    public ServerClientResponse deleteSingleItem(int itemId) 
    {
       ServerClientResponse response = new ServerClientResponse();
       QuizEntityManager<QuizEvent> qem = new QuizEntityManager<>(QuizEvent.class);
       String query = "QuizEvent.deleteEvent";
       String parameter = "id";
       
       HashMap queryParameters = new HashMap();
       
       queryParameters.put(parameter, new Integer(itemId));

       try
       {
           qem.deleteObject(query, queryParameters);
           response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
           response.setStatusMessage(ServerClientResponseFactory.formatSuccessJSON("Quiz Event Deleted", "Quiz event has been successfully deleted."));
       
       }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException ex)
       {
           response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
           response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("No Records Deleted", "No records were deleted"));
       }
       
       return response;
    }

    @Override
    public ServerClientResponse deleteMultipleItems(String jsonString) 
    {
        ServerClientResponse response = null;
        
        return response;
    }

    @Override
    public ServerClientResponse updateItem(String jsonString) 
    {
        ServerClientResponse response = null;
        
        return response;
    }
    
    @Override
    public ServerClientResponse updateItem(String jsonObject, int itemId)
    {
        ServerClientResponse resp = null;
        
        return resp;
    }

    @Override
    public ServerClientResponse getSingleItem(QuizEvent itemObject) 
    {
        ServerClientResponse response = null;
        
        return response;
    }
    
    @Override
    public ServerClientResponse getSingleItem(int itemId)
    {
        ServerClientResponse response = null;
         
        return response;
    }
    
    /**
     * Determine a strategy to pick out and delete expired quiz events
     * don't want this to happen too often and use resources unecessarily.
     * 
     */
    private void deleteExpiredQuizEvents()
    { 
        QuizEntityManager<QuizEvent> qem = new QuizEntityManager<>(QuizEvent.class);
        Date  currentDate = new Date();
        String deleteQuery = "QuizEvent.deleteExpiredEvents";
        
        HashMap queryParameters = new HashMap();
        
        queryParameters.put("currentDate", currentDate);
        
        try
        {
            qem.deleteObject(deleteQuery, queryParameters);
        }catch (uk.ac.angus.coreskillstest.quizmanagement.exception.UnableToDeleteObjectException ex)
        {
            System.err.println("Unable to delete expired query events");
            System.err.println(ex.getMessage()); 
        }
    }
}
