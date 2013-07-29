package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

/**
 *
 * @author JWO
 */
public class ResultRule implements Serializable
{
    private String RuleName;
    private int LowMarkBoundary;
    private int HighMarkBoundary;
    
    private Quiz LinkedQuiz;
    private Feedback LinkedFeedback;
    
    public ResultRule()
    {
        
    }
    
    public int getLowMarkBoundary()
    {
        return LowMarkBoundary;
    }
    
    public void setLowMarkBoundary(int newLowMarkBoundary)
    {
        LowMarkBoundary = newLowMarkBoundary;
    }
    
    public int getHighMarkBoundary()
    {
        return HighMarkBoundary;
    }
    
    public void setHighMarkBoundary(int newHighMarkBoundary)
    {
        HighMarkBoundary = newHighMarkBoundary;
    }
    
    public Quiz getQuiz()
    {
        return LinkedQuiz;
    }
    
    public void setQuiz(Quiz newQuiz)
    {
        LinkedQuiz = newQuiz;
    }
    
    public Feedback getFeedback()
    {
        return LinkedFeedback;
    }
    
    public void setFeedback(Feedback newFeedback)
    {
        LinkedFeedback = newFeedback;
    }
    
}
