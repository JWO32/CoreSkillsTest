package uk.ac.angus.coreskillstest.entity;

import java.util.ArrayList;

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


@Entity(name="QUESTIONS")

public class Question implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Expose
    @Id
    @Column(name="question_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuestionId;
    
    @Expose
    @Column(name="question_text")
    private String QuestionText;
    
    @Expose
    @Column(name="question_category")
    private Category QuestionCategory;
    
    @Expose
    @Column(name="question_mark")
    private int QuestionMark;
    
    @Expose
    @Column(name="question_optoins")
    @OneToMany(cascade=CascadeType.ALL, mappedBy="Question", targetEntity=QuestionOption.class, fetch=FetchType.EAGER)
    private ArrayList<QuestionOption> QuestionOptions = new ArrayList<>();
    
    @ManyToOne(optional=false, targetEntity=Quiz.class)
    @JoinColumn(name="question_id", referencedColumnName="question_id")
    private Quiz LinkedQuiz;
    
    
    public Question()
    {
        
    }
    
    public int getQuestionId()
    {
        return QuestionId;
    }
    
    public void setQuestionId(int newQuestionId)
    {
        QuestionId = newQuestionId;
    }
    
    public String getQuestionText()
    {
        return QuestionText;
    }
    
    public void setQuestiontext(String newQuestionText)
    {
        QuestionText = newQuestionText;
    }
    
    public Category getCategory()
    {
        return QuestionCategory;
    }
    
    public void setCategory(Category newCat)
    {
        QuestionCategory = newCat;
    }
    
    public int getMark()
    {
        return QuestionMark;
    }
    
    public void setMark(int mark)
    {
        QuestionMark = mark;
    }
    
    public ArrayList<QuestionOption> getOptions()
    {
        return QuestionOptions;
    }
    
    public void setOptions(ArrayList<QuestionOption> newOptions)
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
}
