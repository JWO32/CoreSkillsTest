package uk.ac.angus.coreskillstest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
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
 
    /**
     * Get question option id - database id
     * @return 
     */
    public int getOptionId()
    {
        return QuestionOptionId;
    }
    
    /**
     * set Question option id - database id
     * @param newId 
     */
    public void setOptionId(int newId)
    {
        QuestionOptionId = newId;
    }
    
    /**
     * get the option's text
     * @return 
     */
    public String getOptionText()
    {
        return OptionText;
    }
    
    /**
     * set the option's text
     * 
     * @param newOptionText 
     */
    public void setOptionText(String newOptionText)
    {
        OptionText = newOptionText;
    }
    
    /**
     * Return the correct setting of this option
     * @return 
     */
    public boolean isCorrectOption()
    {
        return CorrectOption;
    }
    
    /**
     * Set the correct option
     * @param isCorrect 
     */
    public void setCorrectOption(boolean isCorrect)
    {
        CorrectOption = isCorrect;
    }
    
    /**
     * Set the question that this option is linked to
     * @param newQuestion 
     */
    public void setQuestion(Question newQuestion)
    {
        LinkedQuestion = newQuestion;
    }
    
    /**
     * Get the question that this option is link to
     * @return 
     */
    public Question getQuestion()
    {
        return LinkedQuestion;
    }
            
}
