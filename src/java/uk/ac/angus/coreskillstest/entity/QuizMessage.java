package uk.ac.angus.coreskillstest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import java.io.Serializable;




/**
 *  Represents a start/end message added by the a quiz creator to the Quiz
 *  Provides information about the quiz.
 * 
 * 
 * @author JWO
 */

@Entity(name="QUIZ_MESSAGES")

public class QuizMessage implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="message_id")
    private int MessageId;
    
    @Column(name="message_title")
    private String Title;
    
    @Column(name="message_content")
    private String Content;
    
    // Only filled in for appropriate start messages
    @Column(name="pass_mark")
    private int PassMark;
    
    @Column(name="start_message")
    private boolean IsStartMessage;
    
    @ManyToOne
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id")
    private Quiz LinkedQuiz;
    
    
    public QuizMessage()
    {
        
    }
    
    public void setMessageId(int newId)
    {
        MessageId = newId;
    }
    
    public int getMessageId()
    {
        return MessageId;
    }
    
    public void setMessageTitle(String newTitle)
    {
        Title = newTitle;
    }
    
    
    public String getMessageTitle()
    {
        return Title;
    }
    
    public void setContent(String newContent)
    {
        Content = newContent;
    }
    
    public String getContent()
    {
        return Content;
    }
    
    public void setPassMark(int newPassMark)
    {
        PassMark = newPassMark;
    }
    
    public int getPassMark()
    {
        return PassMark;
    }
    
    public void setIsStartMessage(boolean isStart)
    {
        IsStartMessage = isStart;
    }
    
    public boolean getIsStartMessage()
    {
        return IsStartMessage;
    }
    
    public void setLinkedQuiz(Quiz newQuiz)
    {
        LinkedQuiz = newQuiz;
    }
    
    public Quiz getLinkedQuiz()
    {
        return LinkedQuiz;
    }
}
