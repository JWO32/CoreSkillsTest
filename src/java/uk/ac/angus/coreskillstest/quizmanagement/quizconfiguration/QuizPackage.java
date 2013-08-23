package uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration;

/**
 *This class is the endpoint for the serialised QuizPackage which is returned to
 * the quizplayer jsp.
 * 
 * This is a container class, the JSON should be provided.
 * 
 * @author JWO
 */
public class QuizPackage 
{
    
    private String QuizJSON;
    
    private String StartMessage;
    
    private String EndMessage;
    
    private int UserId;
    
    private int QuizEventId;
    
    public QuizPackage()
    {
        
    }
    
    /**
     * Utility function to return a default message if none already exists
     * @return 
     */
    public static String setDefaultStartMessage()
    {
        return "{\"Title\":\"Quiz Attempt Ready\", \"Content\":\"The quiz is ready.  Please click on begin to start.\"}";
    }
    
    /**
     * 
     * Utility function to return a default message if none already exists
     * @return 
     */
    public static String setDefaultEndMessage()
    {
        return "{\"Title\":\"Quiz Complete\", \"Content\":\"You have completed the quiz.  Please see below for any feedback.\"}";
    }
    
    public QuizPackage(String quizJson, String startMessage, String endMessage)
    {
       QuizJSON = quizJson;
       StartMessage = startMessage;
       EndMessage = endMessage;
    }
    
    public void setQuizJSON(String quizJson)
    {
        QuizJSON = quizJson;
    }
    
    public String getQuizJSON()
    {
        return QuizJSON;
    }
    
    public void setStartMessage(String newStartMessage)
    {
        StartMessage = newStartMessage;
    }
    
    public String getStartMessage()
    {
        return StartMessage;
    }
    
    public void setEndMessage(String newEndMessage)
    {
        EndMessage = newEndMessage;
    }
    
    public String getEndMessage()
    {
        return EndMessage;
    }
    
    public String getUserIdJSON()
    {
        return "{\"UserId\":"+UserId+"}";
    }
    
    public void setUserId(int newUserID)
    {
        UserId = newUserID;
    }
    
    public int getUserId()
    {
        return UserId;
    }
    
    public String getEventIdJSON()
    {
        return "{\"QuizEventId\":"+QuizEventId+"}";
    }
    
    public void setEventId(int newId)
    {
        QuizEventId = newId;
    }
    
    public int getEventId()
    {
        return QuizEventId;
    }
}
    
