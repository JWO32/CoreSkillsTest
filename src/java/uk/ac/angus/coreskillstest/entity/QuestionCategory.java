/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import java.io.Serializable;




/**
 *
 * @author JWO
 */

@Entity(name="QUESTION_CATEGORY")
public class QuestionCategory implements Serializable
{
    @Expose
    @Id
    @Column(name="question_category_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int QuestionCategoryId;

    @Column(name="category_question")
    private String CategoryDescription;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "QCategory", targetEntity=Question.class, fetch=FetchType.LAZY)
    @ManyToOne(optional=false, targetEntity=Question.class)
    @JoinColumn(name="question_id", referencedColumnName="question_id", insertable=false, updatable=false)
    private Question LinkedQuestion;
    
    public QuestionCategory()
    {
        
    }
    
    public int getQuestionCategoryId()
    {
        return QuestionCategoryId;
    }
    
    public void setQuestionCategoryId(int newId)
    {
        QuestionCategoryId = newId;
    }
    
    public String getCategoryDescription()
    {
        return CategoryDescription;
    }
    
    public void setCategoryDescription(String newDescription)
    {
        CategoryDescription = newDescription;
    }
    
    public Question getQuiz()
    {
        return LinkedQuestion;
    }
    
    public void setQuiz(Question newQuestion)
    {
        LinkedQuestion = newQuestion;
    }   
}
