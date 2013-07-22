package uk.ac.angus.coreskillstest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

@Entity(name="QUESTION_OPTION")
public class QuestionOption implements Serializable
{
    @Expose
    @Id
    @Column(name="option_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuestionOptionId;
    
    @Expose
    @Column(name="option_text")
    private String QuestionOptionText;
    
    @Expose
    @Column(name="option_correct")
    private boolean OptionCorrect;
    
    @ManyToOne(optional=false, targetEntity=Question.class)
    @JoinColumn(name="question_id", referencedColumnName="question_id", insertable=false, updatable=false)
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
        return QuestionOptionText;
    }
    
    public void setOptionText(String newOptionText)
    {
        QuestionOptionText = newOptionText;
    }
    
    public boolean getOptionCorrect()
    {
        return OptionCorrect;
    }
    
    public void setOptionCorrect(boolean isCorrect)
    {
        OptionCorrect = isCorrect;
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
