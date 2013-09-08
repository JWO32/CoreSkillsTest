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
    /**
     * This is the JSON string that contains the quiz object.
     */
    private String QuizJSON;
    
    /**
     * This contains the start message displayed on screen at the start of the quiz
     */
    private String StartMessage;
    
    /**
     * This contains the end message displayed on screen at the end of the quiz
     */
    private String EndMessage;
    
    /**
     * This is the database id of the user that is taking the quiz, this is necessary
     * so the user can be identified when the response is set from the client to the server
     */
    private int UserId;
    
    /**
     * This is quiz quiz event id, necessary when the response is sent back to the server
     * after the student has completed the quiz -- the correct quiz options can be looked
     * up for processing the result
     */
    private int QuizEventId;
    
    public QuizPackage()
    {
        
    }
    
    /**
     * Utility function to return a default message if none already exists - currently the default
     * @return 
     */
    public static String setDefaultStartMessage()
    {
        return "{\"Title\":\"Quiz Attempt Ready\", \"Content\":\"The quiz is ready.  Please click on begin to start.\"}";
    }
    
    /**
     * 
     * Utility function to return a default message if none already exists -- currently the default
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
    
    /**
     * Returns the user id as a JSON string - necessary for integration with the client
     * 
     * @return 
     */
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
    
    /**
     * Returns the quiz event id as a JSON string, necessary for integration with the client
     * @return 
     */
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
    
