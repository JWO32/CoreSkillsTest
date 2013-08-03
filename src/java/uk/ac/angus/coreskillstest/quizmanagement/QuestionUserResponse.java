
package uk.ac.angus.coreskillstest.quizmanagement;

import java.util.List;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;

/**
 *
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
    
    public void setQuestionId(int newQuestionId)
    {
        QuestionId = newQuestionId;
    }
    
    public int getQuestionId()
    {
        return QuestionId;
    }
    
    public void setOptionIdList(List<Integer> optionIdList)
    {
        SelectedResponse = optionIdList;
    }
    
    public List<Integer> getOptionIdList()
    {
        return SelectedResponse;
    }
}
