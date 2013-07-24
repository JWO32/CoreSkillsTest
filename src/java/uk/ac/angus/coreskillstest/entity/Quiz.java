package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import com.google.gson.annotations.Expose;
import javax.persistence.TemporalType;

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
    private String QuizTitle;
    
    @Expose
    @Column(name="quiz_subject")
    private String QuizSubject;
    
    @Expose
    @Column(name="quiz_duration")
    private int QuizDuration;
    
    @Expose
    @Column(name="total_marks")
    private int TotalMarks;
    
    //@Expose
    @Column(name="date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar DateAdded = Calendar.getInstance();
      
    //Replace this with a level object
    //
    @Expose
    @Column(name="quiz_level")
    private String QuizLevel;
    
    @ManyToOne(optional=false, targetEntity=QuizCategory.class)
    @JoinColumn(name="quiz_category_id", referencedColumnName="quiz_category_id")
    private QuizCategory QCategory;
    
    @Expose
    @OneToMany(cascade=CascadeType.ALL, mappedBy="LinkedQuiz", targetEntity=Question.class, fetch=FetchType.EAGER)
    private List<Question> Questions = new ArrayList<>();
    
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
    
    public String getQuizLevel()
    {
        return QuizLevel;
    }
    
    public void setQuizLevel(String newQuizLevel)
    {
        QuizLevel = newQuizLevel;
    }
    
    public String getQuizTitle()
    {
        return QuizTitle;
    }
    
    public void setQuizName(String newName)
    {
        QuizTitle = newName;
    }
    
    public String getQuizSubject()
    {
        return QuizSubject;
    }
    
    public void setQuizSubject(String newDescription)
    {
        QuizSubject = newDescription;
    }
    
    public int getTotalMarks()
    {
        return TotalMarks;
    }
    
    public void setTotalMarks(int newMarks)
    {
        TotalMarks = newMarks;
    }
    
    public List<Question> getQuestions()
    {
        return Questions;
    }
    
    public void setQuestions(List<Question> newQuestions)
    {
        Questions = newQuestions;
    }
    
    public QuizCategory getQuizCategory()
    {
        return QCategory;
    }
    
    public void setQuizCategory(QuizCategory newCategory)
    {
        QCategory = newCategory;
    }
    
    public Calendar getDateAdded()
    {
        return DateAdded;
    }
    
    public void setDateAdded(Calendar newDateAdded)
    {
        DateAdded = newDateAdded;
    }
    
    public int getDuration()
    {
        return QuizDuration;
    }
    
    public void setDuration(int newDuration)
    {
        QuizDuration = newDuration;
    }
}
