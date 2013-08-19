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
import java.util.Calendar;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * Represents a comlpeted result that has been processed by the system.
 * 
 * @author JWO
 */

@Entity(name="QUIZ_RESULT")
@NamedQueries({
        @NamedQuery(name="Result.getResultById",
        query="SELECT r from QUIZ_RESULT r WHERE r.ResultId=:id"),
        @NamedQuery(name="Result.deleteResultById",
        query="DELETE FROM QUIZ_RESULT r WHERE r.ResultId=:id"),
        @NamedQuery(name="Result.getResultsByGroupId",
        query="SELECT r FROM QUIZ_RESULT r JOIN r.LinkedUser lu JOIN lu.Group g WHERE g.GroupID = :groupId"),
        @NamedQuery(name="Result.getResultByUserId",
        query="SELECT r FROM QUIZ_RESULT r WHERE r.LinkedUser.UserId = :userId")
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
    @Column(name="feedback_text")
    private String LinkedFeedback;
    
    @Expose
    @Column(name="quiz_score")
    private int QuizScore;
    
    @Expose
    @Column(name="quiz_percentage")
    private float QuizPercentage;
    
    @Expose
    @Column(name="process_status")
    private String ResultStatus;
    
    @OneToOne(optional=false, targetEntity=Quiz.class)
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id")
    private Quiz LinkedQuiz;
    
    @Column(name="result_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar ResultDate;
    
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
    
    public void setResultStatus(String resultStatus)
    {
        ResultStatus = resultStatus;
    }
    
    public String getResultStatus()
    {
        return ResultStatus;
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
    
    public String getLinkedFeedback()
    {
        return LinkedFeedback;
    }
    
    public void setLinkedFeedback(String newLinkedFeedback)
    {
        LinkedFeedback = newLinkedFeedback;
    }
    
    public void setScoreandPercentage(int quizScore, int numberOfQuestions)
    {
        QuizScore = quizScore;
        
        QuizPercentage = (float) ((float)quizScore / (float)numberOfQuestions) * 100f;
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
    
    public Calendar getResultDate()
    {
        return ResultDate;
    }
    
    public void setResultDate(Calendar newResultDate)
    {
        ResultDate = newResultDate;   
    }
}