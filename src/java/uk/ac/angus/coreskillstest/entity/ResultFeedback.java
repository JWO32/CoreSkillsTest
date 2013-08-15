package uk.ac.angus.coreskillstest.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    public ResultFeedback()
    {
        
    }
    
    public void setResultFeedbackId(int newFeedbackId)
    {
        ResultFeedbackId = newFeedbackId;
    }
    
    public int getResultFeedbackId()
    {
        return ResultFeedbackId;
    }
    
    public void setFeedbackText(String newFeedbackText)
    {
        FeedbackText = newFeedbackText;
    }
    
    public String getFeedbackText()
    {
        return FeedbackText;
    }
    
    public void setResult(Result newResult)
    {
        LinkedResult = newResult;
    }
    
    public Result getResult()
    {
        return LinkedResult;
    }
    
    public void setPercentageScore(float newPercentageScore)
    {
        PercentageScore = newPercentageScore;
    }
    
    public float getPercentageScore()
    {
        return PercentageScore;
    }
    
    public void setPassOrFail(boolean newPassFail)
    {
        PassOrFail = newPassFail;
    }
    
    public boolean getPassOrFail()
    {
        return PassOrFail;
    }
}
