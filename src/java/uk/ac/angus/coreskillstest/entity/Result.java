package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import com.google.gson.annotations.Expose;

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
    public static final String SUCCESS_RESULT_NOT_AVAILABLE = "Your result has been successfully stored. Due to the quiz configuration your result is not being shared at the moment";
    public static final String SUCCESS_RESULT_AVAILABLE = "Your result has been successfully stored.";
    public static final String FAILURE_RESULT_NOT_STORED = "Server Error: Your result has not been stored";

    @Expose
    @Id
    @Column(name="result_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ResultId;
    
    @Expose
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="user_id")
    private QuizUser LinkedUser;
    
    @Expose
    @OneToOne(optional=true, targetEntity=Feedback.class)
    @JoinColumn(name="feedback_id", referencedColumnName="feedback_id")
    private Feedback LinkedFeedback;
    
    @Expose
    @Column(name="quiz_score")
    private int QuizScore;
    
    @Expose
    @Column(name="quiz_percentage")
    private float QuizPercentage;
    
    @OneToOne
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id")
    private Quiz LinkedQuiz;
    
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
    
    public void setScoreandPercentage(int quizScore, int numberOfQuestions)
    {
        QuizScore = quizScore;
        
        QuizPercentage = (float) quizScore / numberOfQuestions;
    }
    
    public void setQuizPercentage(float newPercentage)
    {
        QuizPercentage = newPercentage ;
    }
    
    public float getQuizPercentage()
    {
        return QuizPercentage;
    }
    
    public void setQuizScore(int newQuizScore)
    {
        QuizScore = newQuizScore;
    }
    
    public int getQuizScore()
    {
        return QuizScore;
    }
}