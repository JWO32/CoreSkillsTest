
package uk.ac.angus.coreskillstest.quizmanagement;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * Utility class to hold the representation of the user's responses to the Quiz
 * Allows appropriate resources to be loaded and results to be calculated.
 * @author JWO
 */
public class QuizUserResponse 
{
    @Expose
    private int QuizId;
    
    @Expose
    private int UserId;
    
    @Expose
    private int QuizEventId;
    
    @Expose
    private List<QuestionUserResponse> Responses = new ArrayList<>();

    public QuizUserResponse()
    {
        
    }
    
    public int getQuizId()
    {
        return QuizId;
    }
    
    public void setQuizId(int newQuizId)
    {
        QuizId = newQuizId;
    }
    
    public int getUserId()
    {
        return UserId;
    }
    
    public int getQuizEventId()
    {
        return QuizEventId;
    }
    
    public void setQuizEventId(int newConfigId)
    {
        QuizEventId = newConfigId;
    }
    
    public void setUserId(int newUserId)
    {
        UserId = newUserId;
    }
    
    public void setResponses(List<QuestionUserResponse> responses)
    {
        Responses = responses;  
    }
    
    public List<QuestionUserResponse> getResponses()
    {
        return Responses;
    }
    
    public QuestionUserResponse getResponseByQuestionId(int questionId)
    {
        QuestionUserResponse selectedResponse = null;
        
        for(QuestionUserResponse currentResponse : Responses)
        {
            if(currentResponse.getQuestionId() == questionId)
            {
                selectedResponse = currentResponse;
            }
        }
        
        return selectedResponse;
    }
}
