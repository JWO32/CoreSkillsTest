/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author JWO
 */
public class QuizEvent implements Serializable
{
    
    private Quiz LinkedQuiz;
    
    private Calendar OpenTime;
    
    private Calendar CloseTime;
    
    private QuizMessage StartMessage;
    
    private QuizMessage EndMessage;
    
    public QuizEvent()
    {

    }
}
