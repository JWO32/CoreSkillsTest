package uk.ac.angus.coreskillstest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

@Entity(name="QUESTION_OPTION")
public class QuestionOption implements Serializable
{
    @Expose
    @Id
    @Column(name="option_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuestionOptionId;
    
    @Expose
    @Column(name="option_text")
    private String OptionText;
    
    @Expose
    @Column(name="option_correct")
    private boolean CorrectOption;
    
    @ManyToOne(optional=false, targetEntity=Question.class)
    @JoinColumn(name="question_id", referencedColumnName="question_id")
    private Question LinkedQuestion;
    
    public QuestionOption()
    {

    }
    
    public int getOptionId()
    {
        return QuestionOptionId;
    }
    
    public void setOptionId(int newId)
    {
        QuestionOptionId = newId;
    }
    
    public String getOptionText()
    {
        return OptionText;
    }
    
    public void setOptionText(String newOptionText)
    {
        OptionText = newOptionText;
    }
    
    public boolean getCorrectOption()
    {
        return CorrectOption;
    }
    
    public void setCorrectOption(boolean isCorrect)
    {
        CorrectOption = isCorrect;
    }
    
    public void setQuestion(Question newQuestion)
    {
        LinkedQuestion = newQuestion;
    }
    
    public Question getQuestion()
    {
        return LinkedQuestion;
    }
            
}
