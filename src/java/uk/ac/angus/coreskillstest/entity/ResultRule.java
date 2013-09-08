package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class represent a result rule as defined in the Quiz Creator.
 * The setting allow the user to set the mark boundaries at which the rule will apply
 * 
 * 
 * Each quiz is intended to have multiple result rules, so it is possible for the 
 * quiz designer to set appropriate boundaries for particular feedback
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
    
    /**
     * Set whether or not this is a pass/fail rule
     * @param passFail 
     */
    public void setPassFail(boolean passFail)
    {
        PassFail = passFail;
    }
    
    /**
     * Get whether or not this is a pass/fail rule
     * @return 
     */
    public boolean isPassFail()
    {
        return PassFail;
    }
    
    /**
     * Set the id of this result rule - database
     * @param newId 
     */
    public void setResultRuleId(int newId)
    {
        ResultRuleId = newId;
    }
    
    /**
     * Get the id of this result rule - database
     * @return 
     */
    public int getResultRuleId()
    {
        return ResultRuleId;
    }
    
    /**
     * set the name of this result rule
     * @param newRuleName 
     */
    public void setRuleName(String newRuleName)
    {
        RuleName = newRuleName;
    }
    
    /**
     * Get the name of this result rule
     * @return 
     */
    public String getRuleName()
    {
        return RuleName;
    }
    
    /**
     * Get the lower mark boundary of this result rule
     * @return 
     */
    public int getLowMarkBoundary()
    {
        return LowMarkBoundary;
    }
    
    /**
     * Set the lower mark boundary of this result rule
     * @param newLowMarkBoundary 
     */
    public void setLowMarkBoundary(int newLowMarkBoundary)
    {
        LowMarkBoundary = newLowMarkBoundary;
    }
    
    /**
     * get the high mark boundary of this result rule
     * @return 
     */
    public int getHighMarkBoundary()
    {
        return HighMarkBoundary;
    }
    
    /**
     * set the high mark boundary of this result rule
     * @param newHighMarkBoundary 
     */
    public void setHighMarkBoundary(int newHighMarkBoundary)
    {
        HighMarkBoundary = newHighMarkBoundary;
    }
    
    /**
     * Get the quiz that this result rule applies to
     * @return 
     */
    public Quiz getQuiz()
    {
        return LinkedQuiz;
    }
    
    /**
     * Set the quiz that this result rule applies to
     * @param newQuiz 
     */
    public void setQuiz(Quiz newQuiz)
    {
        LinkedQuiz = newQuiz;
    }
    
    /**
     * Get the feedback defined for this rule
     * @return 
     */
    public String getFeedback()
    {
        return LinkedFeedback;
    }
    
    /**
     * Set the feedback defined for this rule
     * @param newFeedback 
     */
    public void setFeedback(String newFeedback)
    {
        LinkedFeedback = newFeedback;
    }
    
    /**
     * Set whether the high mark boundary should be treated as >=
     * otherwise it will be treated as > only
     * @param highMarkGtEq 
     */
    public void setHighMarkGtEq(boolean highMarkGtEq)
    {
        HighMarkGtEq = highMarkGtEq;
    }
    
    /**
     * Return whether the high mark boundary should be treated as >=
     * @return 
     */
    public boolean isHighMarkGtEq()
    {
        return HighMarkGtEq;
    }
    
    /**
     * Set whether the low mark boundary should be treated as <=
     * otherwise it should be treated as < only
     * @param lowMarkGtEq 
     */
    public void setLowMarkGtEq(boolean lowMarkGtEq)
    {
        LowMarkGtEq = lowMarkGtEq;
    }
    
    /**
     * Return whether the low mark boundary should be treated as <
     * @return 
     */
    public boolean isLowMarkGtEq()
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
