
package uk.ac.angus.coreskillstest.quizmanagement;

import java.util.List;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;

/**
 * This class represent the users response to a question
 * including the question id and the ids of the options that the user
 * has selected from the client while taking the quiz.
 * @author JWO
 */
public class QuestionUserResponse 
{
    @Expose
    private int QuestionId;
    
    @Expose
    private List<Integer> SelectedResponse = new ArrayList<>();
    
    public QuestionUserResponse()
    {

    }
    
    /**
     * Set the id of the question represented in the response
     * @param newQuestionId 
     */
    public void setQuestionId(int newQuestionId)
    {
        QuestionId = newQuestionId;
    }
    
    /**
     * Get the id of the question represented in the response
     * @return 
     */
    public int getQuestionId()
    {
        return QuestionId;
    }
    
    /**
     * Set the list of options selected by the user
     * 
     * @param optionIdList 
     */
    public void setOptionIdList(List<Integer> optionIdList)
    {
        SelectedResponse = optionIdList;
    }
    
    /**
     * Get the list of options selected by the user
     * @return 
     */
    public List<Integer> getOptionIdList()
    {
        return SelectedResponse;
    }
}
