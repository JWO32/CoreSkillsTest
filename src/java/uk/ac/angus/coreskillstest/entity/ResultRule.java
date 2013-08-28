package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author JWO
*/

@Entity(name="result_rule")
public class ResultRule implements Serializable
{
    @Id
    @Column(name="result_rule_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ResultRuleId;
    
    @Column(name="rule_name")
    private String RuleName;
    
    @Column(name="low_mark_boundary")
    private int LowMarkBoundary;
    
    @Column(name="low_mark_gteq")
    private boolean LowMarkGtEq;
    
    @Column(name="high_mark_boundary")
    private int HighMarkBoundary;
    
    @Column(name="high_mark_gteq")
    private boolean HighMarkGtEq;
    
    @Column(name="pass_fail_only")
    private boolean PassFail;
    
    @ManyToOne(optional=false, targetEntity=Quiz.class)
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id")
    private Quiz LinkedQuiz;
    
    @Column(name="feedback")
    private String LinkedFeedback;
    
    public ResultRule()
    {
        
    }
    
    public void setPassFail(boolean passFail)
    {
        PassFail = passFail;
    }
    
    public boolean getPassFail()
    {
        return PassFail;
    }
    
    public void setResultId(int newId)
    {
        ResultRuleId = newId;
    }
    
    public int getResultRuleId()
    {
        return ResultRuleId;
    }
    
    public void setRuleName(String newRuleName)
    {
        RuleName = newRuleName;
    }
    
    public String getRuleName()
    {
        return RuleName;
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
    
    public String getFeedback()
    {
        return LinkedFeedback;
    }
    
    public void setFeedback(String newFeedback)
    {
        LinkedFeedback = newFeedback;
    }
    
    public void setHighMarkGtEq(boolean highMarkGtEq)
    {
        HighMarkGtEq = highMarkGtEq;
    }
    
    public boolean getHighMarkGtEq()
    {
        return HighMarkGtEq;
    }
    
    public void setLowMarkGtEq(boolean lowMarkGtEq)
    {
        LowMarkGtEq = lowMarkGtEq;
    }
    
    public boolean getLowMarkGtEq()
    {
        return LowMarkGtEq;
    }
    
    /**
     * Take the user's score from a quiz attempt and run through
     * rules to see if the score applies to this rule.
     * 
     * TODO: look at the possibility of using a regular expression instead of 
     * if statements
     * 
     * @param percentScore
     * @return 
     */
    public boolean appliesTo(float percent)
    {
        boolean applies = false;
        //Convert to int for simplicity
        int percentScore = (int) percent;
        
        if(percentScore > LowMarkBoundary && PassFail == true)
        {
            applies = true;
        }
        
        if(percentScore >= LowMarkBoundary && percentScore <= HighMarkBoundary)
        {
            applies = true;
        }
        
        if(HighMarkGtEq == true)
        {
            if(percentScore > LowMarkBoundary && percentScore <=HighMarkBoundary)
            {
                applies = true;
            }
        }else if(LowMarkGtEq == true)
        {
            if(percentScore >= LowMarkBoundary && percentScore < HighMarkBoundary)
            {
                applies = true;
            }
        }else if(HighMarkGtEq == true && LowMarkGtEq == true)
        {
            if(percentScore >= LowMarkBoundary && percentScore <= HighMarkBoundary)
            {
                applies = true;
            }
        }
        
        return applies;
    }
    
}
