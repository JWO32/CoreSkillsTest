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
 * @author JWO
 */
@Entity(name="quiz_stored_feedback")
public class StoredFeedback implements Serializable 
{
    @Expose
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="feedback_id")
    private int FeedbackId;
    
    @Expose
    @Column(name="feedback_text")
    private String FeedbackText;
    
//    @OneToMany(cascade=CascadeType.PERSIST, targetEntity=Result.class, fetch=FetchType.LAZY)
//    private List<Result> LinkedResults = new ArrayList<>();
    
    @ManyToOne(optional=false, targetEntity=ResultRule.class)
    @JoinColumn(name="result_rule_id", referencedColumnName="result_rule_id")
    private ResultRule LinkedResultRule;
 
    /**
     * Create a default feedback object if no feedback is provided by the Quiz
     * 
     * Simplified with a String for Version 1 of the Quiz System
     * @return 
     */
    public static String getDefaultFeedback()
    {
        String defaultFeedback = "No lecturer feedback was provided for this quiz.";
        
        return defaultFeedback;
    }
    
    public StoredFeedback()
    {
        
    }
    
    public StoredFeedback(String feedbackText)
    {
        FeedbackText = feedbackText;
    }
    
//    public List<Result> getResults()
//    {
//        return LinkedResults;
//    }
//    
//    public void setResults(List<Result> results)
//    {
//        LinkedResults = results;
//    }
            
    public void setFeedbackId(int newId)
    {
        FeedbackId = newId;
    }
    
    public int getFeedbackId()
    {
        return FeedbackId;
    }
    
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
