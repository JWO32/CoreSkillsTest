package uk.ac.angus.coreskillstest.datamanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponses;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizEventDetailsDeserialiseTypeAdapter;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.QuizEventSerialiseTypeAdapter;

/**
 *
 * @author JWO
 */
public class QuizEventDataAccessObject 
{
    private EntityManagerFactory QuizConfigEntityManager;
    
    /**
     * 
     */
    public QuizEventDataAccessObject()
    {
        QuizConfigEntityManager = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
    }
    
    /**
     * 
     * @param quizConfigId
     * @return 
     */
    public QuizEvent getQuizEventByIdObject(int quizConfigId)
    {
        QuizEvent qc;
        
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        // Remember - returns null if no object is found - catch this!
        // TODO: may prove unreliable, provide a named query to implement this functionality
        qc =  em.find(QuizEvent.class, quizConfigId);
        
        return qc;
    }
    
    /**
     * 
     * @param quizConfigid
     * @return 
     */
    public String getQuizEventByIdJSON(int quizConfigid)
    {
        String json = null;
        
        return json;
    }
    
    /**
     * 
     * @return 
     */
    public String getAllQuizEventsJSON()
    {
        String json = null;
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        GsonBuilder gb = new GsonBuilder();
        gb.excludeFieldsWithoutExposeAnnotation();
        gb.registerTypeAdapter(QuizEvent.class, new QuizEventDetailsDeserialiseTypeAdapter());
        
        Gson gsn = gb.create();
        
        try
        {
            Query q = em.createNamedQuery("QuizEvent.getAllEvents");
            Type eventType = new TypeToken<List<QuizEvent>>(){}.getType();
            
            List<QuizEvent> eventList = q.getResultList();
            
            if(eventList.isEmpty())
            {
                //
                json = ServerClientResponses.getEmptyJSONObject();//Empty json array - might not be the best way to do this. TODO: Setup a static constant.
            }else
            {              
                json = gsn.toJson(eventList, eventType);
            }         
        }finally
        {
            em.close();
        }
        
        return json;
    }
    
    /**
     * 
     * @param event 
     */
    public void addQuizEventObject(QuizEvent event)
    {
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
    }
    
    /**
     * 
     * @param jsonEvent 
     */
    public void addQuizEventJSON(String jsonEvent)
    {
        EntityManager em = QuizConfigEntityManager.createEntityManager();
        GroupDataAccessObject gDAO = new GroupDataAccessObject();
        QuizDataAccessObject qDAQ = new QuizDataAccessObject();
        GsonBuilder gsb = new GsonBuilder();
        gsb.registerTypeAdapter(QuizEvent.class, new QuizEventSerialiseTypeAdapter());
        
        Gson gsn = gsb.create();
        
        QuizEvent qe;
        
        qe = gsn.fromJson(jsonEvent, QuizEvent.class);
        
        try
        {
            em.getTransaction().begin();
            em.persist(qe);
            em.getTransaction().commit();
        }finally
        {
            em.close();
        } 
    }
    
    private void commitObject()    
    {
        
    }
    
    /**
     * 
     * @param quizConfigId 
     */
    public void deleteQuizEvent(int quizConfigId)
    {
        
    }
    
    /**
     * 
     * @param quizConfigId
     * @param amendedConfig 
     */
    public void editQuizEventObject(int quizConfigId, QuizEvent amendedConfig)
    {
        
    } 
}
