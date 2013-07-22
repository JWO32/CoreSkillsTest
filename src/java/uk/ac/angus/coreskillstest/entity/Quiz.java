package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

@Entity(name="QUIZ")

@NamedQueries({
    @NamedQuery(name="Quiz.getAllQuizzes",
        query="SELECT DISTINCT q FROM QUIZ q LEFT JOIN FETCH q.Questions"),
    @NamedQuery(name="Quiz.getQuizById",
        query="SELECT q FROM QUIZ q WHERE q.QuizId = :id ")
})
public class Quiz implements Serializable
{
    @Expose
    @Id
    @Column(name="quiz_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuizId;
    
    @Expose
    @Column(name="quiz_title")
    private String QuizName;
    
    @Expose
    @Column(name="quiz_description")
    private String QuizDescription;
    
    @Expose
    @Column(name="total_marks")
    private int TotalMarks;
    
    @Expose
    @Column(name="category")
    private Category QuizCategory;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="Quiz", targetEntity=Question.class, fetch=FetchType.EAGER)
    private ArrayList<Question> Questions = new ArrayList<>();
    
    public Quiz()
    {
        
    }
    
    public int getQuizId()
    {
        return QuizId;
    }
    
    public void setQuizId(int newId)
    {
        QuizId = newId;
    }
    
    public String getQuizName()
    {
        return QuizName;
    }
    
    public void setQuizName(String newName)
    {
        QuizName = newName;
    }
    
    public String getQuizDescription()
    {
        return QuizDescription;
    }
    
    public void setQuizDescription(String newDescription)
    {
        QuizDescription = newDescription;
    }
    
    public int getTotalMarks()
    {
        return TotalMarks;
    }
    
    public void setTotalMarks(int newMarks)
    {
        TotalMarks = newMarks;
    }
    
    public ArrayList<Question> getQuestions()
    {
        return Questions;
    }
    
    public void setQuestions(ArrayList<Question> newQuestions)
    {
        Questions = newQuestions;
    }
}
