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
import javax.persistence.JoinColumn;

import com.google.gson.annotations.Expose;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * This class represents a quiz event set by a user so that a group of studnets can sit a defined quiz with
 * set options between a particular set of dates.
 * 
 * Although this class is a part of the quiz configuration package, it is an entity class that is managed by 
 * JPA.  It has been moved to this package for semantic separation.
 * 
 * @author JWO
 */
@Entity(name="QUIZ_EVENT")

@NamedQueries({
        @NamedQuery(name="QuizEvent.getAllEvents",
        query="SELECT qe FROM QUIZ_EVENT qe ORDER BY qe.QuizOpenDate"),
        @NamedQuery(name="QuizEvent.deleteEvent",
        query="DELETE FROM QUIZ_EVENT qe WHERE qe.QuizConfigId=:id"),
        @NamedQuery(name="QuizEvent.getLiveEventsByEmail",
        query="SELECT qe FROM QUIZ_EVENT qe JOIN qe.LinkedGroup lg JOIN lg.UserList qu WHERE qu.Email=:email AND :date BETWEEN qe.QuizOpenDate AND qe.QuizCloseDate"),
        @NamedQuery(name="QuizEvent.getQuizByEventId",
        query="SELECT qe FROM QUIZ_EVENT qe JOIN FETCH qe.LinkedQuiz WHERE qe.QuizConfigId=:id"),
        @NamedQuery(name="QuizEvent.deleteExpiredEvents",
        query="DELETE FROM QUIZ_EVENT qe WHERE qe.QuizCloseDate < :currentDate")
})
public class QuizEvent implements Serializable
{
    @Expose
    @Id
    @Column(name="quiz_config_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuizConfigId;
    
    @Expose
    @Column(name="set_random_order")
    private boolean RandomOrder;
    
    @Expose
    @Column(name="number_of_questions")
    private int NumberOfQuestions;
    
    @Expose
    @Temporal(TemporalType.DATE)
    @Column(name="quiz_open_date")
    private Date QuizOpenDate;
    
    @Expose
    @Temporal(TemporalType.DATE)
    @Column(name="quiz_close_date")
    private Date QuizCloseDate;
    
    @Expose
    @Column(name="return_result_user")
    private boolean ReturnResult;
    
    @Expose
    @Transient
    private String QuizName;
    
    @Expose
    @Transient
    private String GroupName;
    
    @Expose
    @Transient
    private int UserId;
    
    @ManyToOne(optional=true, targetEntity=Quiz.class)
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id")
    private Quiz LinkedQuiz;
    
    @ManyToOne(optional=true, targetEntity=QuizGroup.class)
    @JoinColumn(name="group_id", referencedColumnName="group_id")
    private QuizGroup LinkedGroup;
    
    public String getQuizName()
    {
        return QuizName;
    }
    
    public void setQuizName(String newQuizName)
    {
        QuizName = newQuizName;
    }
    
    public String getGroupName()
    {
        return GroupName;
    }
    
    public void setGroupName(String newGroupName)
    {
        GroupName = newGroupName;
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
        RandomOrder = newRandomOrder;
    }
    
    public boolean isRandomOrder()
    {
        return RandomOrder;
    }
    
    public void setNumberOfQuestions(int newNumberOfQuestions)
    {
        NumberOfQuestions = newNumberOfQuestions;
    }
    
    public int getNumberOfQuestions()
    {
        return NumberOfQuestions;
    }
    
    public void setQuizStartDate(Date newQuizStartTime)
    {
        QuizOpenDate = newQuizStartTime;
    }
    
    public Date getQuizStartDate()
    {
        return QuizOpenDate;
    }
    
    public Date getQuizCloseDate()
    {
        return QuizCloseDate;
    }
    
    public void setQuizCloseDate(Date newQuizCloseTime)
    {
        QuizCloseDate = newQuizCloseTime;
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
    
    public boolean isReturnResult()
    {
        return ReturnResult;
    }
    
    public void setUserId(int newUserId)
    {
        UserId = newUserId;
    }
    
    public int getUserId()
    {
        return UserId;
    }
}
