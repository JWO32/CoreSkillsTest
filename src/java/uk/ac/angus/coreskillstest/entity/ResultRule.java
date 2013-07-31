package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    
    @Column(name="high_mark_boundary")
    private int HighMarkBoundary;
    
    @ManyToOne(optional=false, targetEntity=Quiz.class)
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id")
    private Quiz LinkedQuiz;
    
    @OneToOne
    private Feedback LinkedFeedback;
    
    public ResultRule()
    {
        
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
    
    public Feedback getFeedback()
    {
        return LinkedFeedback;
    }
    
    public void setFeedback(Feedback newFeedback)
    {
        LinkedFeedback = newFeedback;
    }
    
}
