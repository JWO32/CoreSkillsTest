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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

/**
 *
 * @author JWO
 */

@Entity(name="quiz_feedback_item")
public class Feedback implements Serializable 
{
    @Id
    @Column(name="feedback_id")
    private int FeedbackId;
    
    @Column(name="feedback_text")
    private String FeedbackText;
    
    @OneToOne
    @JoinColumn(name="result_rule_id")
    private ResultRule LinkedResultRule;
    
    public Feedback()
    {
        
    }
    
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
        this.setFeedbackId(LinkedResultRule.getResultRuleId());
    }
    
    public ResultRule getResultRule()
    {
        return LinkedResultRule;
    }
}
