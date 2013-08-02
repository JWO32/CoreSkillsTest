/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement;

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
    private int[] OptionIdArray;
    
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
    
    public void setOptionIdArray(int[] optionId)
    {
        OptionIdArray = optionId;
    }
    
    public int[] getOptionIdArray()
    {
        return OptionIdArray;
    }
}
