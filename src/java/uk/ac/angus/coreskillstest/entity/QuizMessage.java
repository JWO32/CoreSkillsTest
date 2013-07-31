/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

/**
 *  Represents a start/end message added by the a quiz creator to the Quiz
 *  Provides information about the quiz.
 * 
 * 
 * @author JWO
 */
public class QuizMessage implements Serializable
{
    
    private String Title;
    
    private String Content;
    
    // Only filled in for appropriate start messages
    private int passMark;
    
    
    public QuizMessage()
    {
        
    }
    
}
