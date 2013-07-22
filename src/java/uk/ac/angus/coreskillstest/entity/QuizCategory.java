/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author JWO
 * 
 *  Model representation of a Quiz Category
 */

@Entity(name="QUIZ_CATEGORY")
public class QuizCategory implements Serializable
{
    @Expose
    @Id
    @Column(name="quiz_category_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuizCategoryId;
    
    @Expose
    @Column(name="quiz_category")
    ArrayList<String> Category = new ArrayList<>();
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "QCategory", targetEntity=Quiz.class, fetch=FetchType.LAZY)
    @ManyToOne(optional=false, targetEntity=Quiz.class)
    @JoinColumn(name="quiz_id", referencedColumnName="quiz_id", insertable=false, updatable=false)
    private Quiz LinkedQuiz;
    
    public QuizCategory()
    {
        super();
    }
    
    public int getQuizCategoryId()
    {
        return QuizCategoryId;
    }
    
    public void setQuizCategoryId(int newId)
    {
        QuizCategoryId = newId;
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
