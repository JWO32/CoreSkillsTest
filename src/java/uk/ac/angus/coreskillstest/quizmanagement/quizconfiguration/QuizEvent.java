package uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration;

import java.io.Serializable;

import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizGroup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

import com.google.gson.annotations.Expose;

import java.util.Calendar;
import javax.persistence.ManyToOne;

/**
 *
 * @author JWO
 */
@Entity(name="QUIZ_EVENT")

@NamedQueries(
        @NamedQuery(name="QuizEvent.getAllEvents",
        query="SELECT qe FROM QUIZ_EVENT qe")
        )
public class QuizEvent implements Serializable
{
    @Expose
    @Id
    @Column(name="quiz_config_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuizConfigId;
    
    @Expose
    @Column(name="set_random_order")
    private boolean SetRandomOrder;
    
    @Expose
    @Column(name="number_of_questions")
    private int NumberOfQuestions;
    
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="quiz_open_time")
    private Calendar QuizOpenTime;
    
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="quiz_close_time")
    private Calendar QuizCloseTime;
    
    @Expose
    @Column(name="return_result_user")
    private boolean ReturnResult;
    
    @ManyToOne(optional=true, targetEntity=Quiz.class)
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id")
    private Quiz LinkedQuiz;
    
    @ManyToOne(optional=true, targetEntity=QuizGroup.class)
    @JoinColumn(name="group_id", referencedColumnName="group_id")
    private QuizGroup LinkedGroup;
    
    public QuizEvent()
    {
        
    }
    
    public void setQuizConfigId(int newQuizConfigId)
    {
        QuizConfigId = newQuizConfigId;
    }
    
    public int getQuizConfigId()
    {
        return QuizConfigId;
    }
    
    public void setRandomOrder(boolean newRandomOrder)
    {
        SetRandomOrder = newRandomOrder;
    }
    
    public boolean getRandomOrder()
    {
        return SetRandomOrder;
    }
    
    public void setNumberOfQuestions(int newNumberOfQuestions)
    {
        NumberOfQuestions = newNumberOfQuestions;
    }
    
    public int getNumberOfQuestions()
    {
        return NumberOfQuestions;
    }
    
    public void setQuizStartTime(Calendar newQuizStartTime)
    {
        QuizOpenTime = newQuizStartTime;
    }
    
    public Calendar getQuizStartTime()
    {
        return QuizOpenTime;
    }
    
    public Calendar getQuizCloseTime()
    {
        return QuizCloseTime;
    }
    
    public void setQuizCloseTime(Calendar newQuizCloseTime)
    {
        QuizCloseTime = newQuizCloseTime;
    }
    
    public void setLinkedQuiz(Quiz newLinkedQuiz)
    {
        LinkedQuiz = newLinkedQuiz;
    }
    
    public Quiz getLinkedQuiz()
    {
        return LinkedQuiz;
    }
    
    public void setLinkedGroup(QuizGroup newGroup)
    {
        LinkedGroup = newGroup;
    }
    
    public QuizGroup getLinkedGroup()
    {
        return LinkedGroup;
    }    
    
    public void setReturnResult(boolean newReturnResult)
    {
        ReturnResult = newReturnResult;
    }
    
    public boolean getReturnResult()
    {
        return ReturnResult;
    }
    
}
