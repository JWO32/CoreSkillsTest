
package uk.ac.angus.coreskillstest.quizmanagement;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author JWO
 */
public class QuizUserResponse 
{
    @Expose
    private int QuizId;
    
    @Expose
    private int UserId;
    
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
