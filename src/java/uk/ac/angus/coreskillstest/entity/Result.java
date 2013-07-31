package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author JWO
 */

@Entity(name="QUIZ_RESULT")

@NamedQueries({
        @NamedQuery(name="Result.getResultById",
        query="SELECT r from QUIZ_RESULT r WHERE r.ResultId=:id"),
        @NamedQuery(name="Result.deleteResultById",
        query="DELETE FROM QUIZ_RESULT r WHERE r.ResultId=:id")
})

public class Result implements Serializable
{

    @Id
    @Column(name="result_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ResultId;
    
    @Column(name="user_id")
    @ManyToOne
    private QuizUser LinkedUser;
    
    @Column(name="quiz_id")
    @OneToOne
    private Quiz LinkedQuiz;
    
    @Column(name="feedback_id")
    @OneToOne
    private Feedback LinkedFeedback;
    
    public Result()
    {
        
    }
    
    public int getResultId()
    {
        return ResultId;
    }
    
    public void setResultId(int newResultId)
    {
        ResultId = newResultId;
    }
    
    public Quiz getLinkedQuiz()
    {
        return LinkedQuiz;
    }
    
    public void setLinkedQuiz(Quiz newLinkedQuiz)
    {
        LinkedQuiz = newLinkedQuiz;
    }
    
    public QuizUser getQuizUser()
    {
        return LinkedUser;
    }
    
    public void setQuizUser(QuizUser newLinkedUser)
    {
        LinkedUser = newLinkedUser;
    }
    
    public Feedback getLinkedFeedback()
    {
        return LinkedFeedback;
    }
    
    public void setLinkedFeedback(Feedback newLinkedFeedback)
    {
        LinkedFeedback = newLinkedFeedback;
    }
    
}