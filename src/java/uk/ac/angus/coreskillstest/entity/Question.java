package uk.ac.angus.coreskillstest.entity;

import java.util.ArrayList;
import java.util.List;

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

/**
 *
 * @author JWO
 */

@Entity(name="QUESTIONS")
@NamedQueries({
    @NamedQuery(name="Question.getQuestionById",
        query="SELECT q from QUESTIONS q WHERE q.QuestionId = :id"),
    @NamedQuery(name="Question.deleteQuestionById",
        query="DELETE FROM QUESTIONS q WHERE q.QuestionId=:id"),
    @NamedQuery(name="Question.deleteAllQuestionsByQuiz",
        query="DELETE FROM QUESTIONS q WHERE q.LinkedQuiz.QuizId=:id")
})

public class Question implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Expose
    @Id
    @Column(name="question_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuestionId;
    
    @Expose
    @Column(name="question_text")
    private String QuestionText;
    
    @Expose
    @ManyToOne
    @JoinColumn(name="question_category_id", referencedColumnName="question_category_id")
    private QuestionCategory QCategory;
    
    @Expose
    @Column(name="question_level")
    private String QuestionLevel;
    
    @Expose
    @Column(name="question_mark")
    private int QuestionScore;
    
    @Expose
    @OneToMany(cascade=CascadeType.ALL, mappedBy="LinkedQuestion", targetEntity=QuestionOption.class, fetch=FetchType.LAZY)
    private List<QuestionOption> QuestionOptions = new ArrayList<>();
    
    @ManyToOne(optional=false, targetEntity=Quiz.class)
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id")
    private Quiz LinkedQuiz;
    
    /**
     * Default constructor - set initial values
     */
    public Question()
    {
        QuestionId = 0;
        QuestionText = "Uninitialised Question";
        QuestionLevel = "No Level - Not initialised";
        QuestionScore = 0;
    }
    
    /**
     * Argument constructor - set common values
     * @param questionText
     * @param questionLevel
     * @param questionScore 
     */
    public Question(String questionText, String questionLevel, int questionScore)
    {
        QuestionText = questionText;
        QuestionLevel = questionLevel;
        QuestionScore = questionScore;
    }
    
    public int getQuestionId()
    {
        return QuestionId;
    }
    
    public void setQuestionId(int newQuestionId)
    {
        QuestionId = newQuestionId;
    }
    
    public void setQuestionLevel(String newLevel)
    {
        QuestionLevel = newLevel;
    }
    
    public String getQuestionLevel()
    {
        return QuestionLevel;
    }
    
    public String getQuestionText()
    {
        return QuestionText;
    }
    
    public void setQuestiontext(String newQuestionText)
    {
        QuestionText = newQuestionText;
    }
    
    public QuestionCategory getCategory()
    {
        return QCategory;
    }
    
    public void setCategory(QuestionCategory newCat)
    {
        QCategory = newCat;
    }
    
    public int getMark()
    {
        return QuestionScore;
    }
    
    public void setMark(int mark)
    {
        QuestionScore = mark;
    }
    
    public List<QuestionOption> getOptions()
    {
        return QuestionOptions;
    }
    
    public void setOptions(List<QuestionOption> newOptions)
    {
        QuestionOptions = newOptions;
    }
    
    public Quiz getQuiz()
    {
        return LinkedQuiz;
    }
    
    public void setQuiz(Quiz newQuiz)
    {
        LinkedQuiz = newQuiz;
    }
    
    /**
     * Returns a List containing the correct options for the question
     * @return 
     */
    public List<Integer> getCorrectOptions()
    {
        List<Integer> correctOptionIds = new ArrayList<>();
        int currentOptionId;
        
        for(QuestionOption currentOption : QuestionOptions)
        {
            if(currentOption.isCorrectOption())
            {
                currentOptionId = currentOption.getOptionId();
                
                correctOptionIds.add(new Integer(currentOptionId));
            }
        }
        
        return correctOptionIds;
    }
}
