/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author JWO
 */
public class QuizUserResponse 
{
    private int QuizId;
    private int UserId;
    
    

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
    
}
