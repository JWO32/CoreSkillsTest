/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import com.google.gson.annotations.Expose;

import java.util.Calendar;

/**
 *
 * @author JWO
 */
public class QuizConfiguration 
{
    
    @Id
    @Column(name="quiz_config_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuizConfigId;
    
    @Column(name="set_random_order")
    private boolean SetRandomOrder;
    
    @Column(name="number_of_questions")
    private int NumberOfQuestions;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="quiz_open_time")
    private Calendar QuizOpenTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="quiz_close_time")
    private Calendar QuizCloseTime;
    
    @Column(name="return_result_user")
    private boolean ReturnResult;
    
    @OneToMany
    private Quiz LinkedQuiz;
    
    @OneToMany
    private QuizGroup LinkedGroup;
    
    public QuizConfiguration()
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
}
