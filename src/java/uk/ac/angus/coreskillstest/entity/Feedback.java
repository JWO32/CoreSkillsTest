/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author JWO
 */
@Entity(name="quiz_feedback_item")
public class Feedback implements Serializable 
{
    @Expose
    @Id
    @OneToOne
    private ResultRule LinkedResultRule;
    
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    //@Column(name="feedback_id")
    //private int FeedbackId;
    
    @Expose
    @Column(name="feedback_text")
    private String FeedbackText;
    
    @OneToMany(cascade=CascadeType.PERSIST, targetEntity=Result.class, fetch=FetchType.LAZY)
    private List<Result> LinkedResults = new ArrayList<>();
    
    
    //@PrimaryKeyJoinColumn
    
    
    /**
     * Create a default feedback object if no feedback is provided by the Quiz
     * 
     * @return 
     */
    public static Feedback getDefaultFeedback()
    {
        Feedback defaultFeedback = new Feedback("No feedback was provided for this quiz.");
        
        return defaultFeedback;
    }
    
    public Feedback()
    {
        
    }
    
    public Feedback(String feedbackText)
    {
        FeedbackText = feedbackText;
    }
    
    public List<Result> getResults()
    {
        return LinkedResults;
    }
    
    public void setResults(List<Result> results)
    {
        LinkedResults = results;
    }
            
//    public void setFeedbackId(int newId)
//    {
//        FeedbackId = newId;
//    }
//    
//    public int getFeedbackId()
//    {
//        return FeedbackId;
//    }
    
    public void setFeedbackText(String text)
    {
        FeedbackText = text;
    }
    
    public String getFeedbackText()
    {
        return FeedbackText;
    }
    
    public void setResultRule(ResultRule newResultRule)
    {
        LinkedResultRule = newResultRule;
        //this.setFeedbackId(LinkedResultRule.getResultRuleId());
    }
    
    public ResultRule getResultRule()
    {
        return LinkedResultRule;
    }
}
