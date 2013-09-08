package uk.ac.angus.coreskillstest.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

import com.google.gson.annotations.Expose;
import javax.persistence.ManyToOne;
/**
 *
 * This represents  the final stored feedback provided to a student after they have completed a quiz.
 * Usually a text classification such as "SCQF Level 5".  May be a statement of "Pass/Fail".
 * 
 * This object is serialised to the database and returned to the client as a JSON object
 * 
 * At the moment this is user set, a number of defaults will be worked in as development progresses.
 * 
 * @author JWO
 */

@Entity(name="quiz_result_feedback")
public class ResultFeedback implements Serializable
{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="result_feedback_id")
    private int ResultFeedbackId;
    
    @Expose
    @Column(name="feedback_text")
    private String FeedbackText;
           
    @Expose
    @Column(name="percentage_score")
    private float PercentageScore;
    
    @Expose
    @Column(name="pass_fail")
    private boolean PassOrFail;
    
    @ManyToOne(optional=false, targetEntity=QuizCategory.class)
    @JoinColumn(name="result_id", referencedColumnName="result_id")
    private Result LinkedResult;
  
    /**
     * Set the id of the feedback object - primarily database link
     * @param newFeedbackId 
     */
    public void setResultFeedbackId(int newFeedbackId)
    {
        ResultFeedbackId = newFeedbackId;
    }
    
    /**
     * get the id of the feedback object - primarily from database
     * @return 
     */
    public int getResultFeedbackId()
    {
        return ResultFeedbackId;
    }
    
    /**
     * Set feedback text associated with this result feedback
     * @param newFeedbackText 
     */
    public void setFeedbackText(String newFeedbackText)
    {
        FeedbackText = newFeedbackText;
    }
    
    /**
     * get feedback text associated with this result feedback
     * @return 
     */
    public String getFeedbackText()
    {
        return FeedbackText;
    }
    
    /**
     * Set the result associated with the feedback
     * @param newResult 
     */
    public void setResult(Result newResult)
    {
        LinkedResult = newResult;
    }
    
    /**
     * get the result associated with the feedback
     * @return 
     */
    public Result getResult()
    {
        return LinkedResult;
    }
    
    /**
     * set the percentage score of this feedback
     * @param newPercentageScore 
     */
    public void setPercentageScore(float newPercentageScore)
    {
        PercentageScore = newPercentageScore;
    }
    
    
    /**
     * Return the percentage score of this feedback
     * @return 
     */
    public float getPercentageScore()
    {
        return PercentageScore;
    }
    
    /**
     * Set whether this feedback is from a pass/fail quiz
     * @param newPassFail 
     */
    public void setIsPassOrFail(boolean newPassFail)
    {
        PassOrFail = newPassFail;
    }
    
    /**
     * Get whether the feedback was generated by a pass/fail quiz
     * @return 
     */
    public boolean isPassOrFail()
    {
        return PassOrFail;
    }
}
