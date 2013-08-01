/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement;


/**
 *
 * @author JWO
 */
public class QuestionUserResponse 
{
    private int QuestionId;
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
