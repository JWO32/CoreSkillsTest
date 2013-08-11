/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

/**
 *
 * @author JWO
 */
public class GlobalSettings implements Serializable
{
    
    private int DeleteQuizEventsAfter;
    
    
    public GlobalSettings()
    {
        
    }
    
    public void setDeleteQuizEventsAfter(int newNumber)
    {
        DeleteQuizEventsAfter = newNumber;
    }
    
    public int getDeleteQuizEventsAfter()
    {
        return DeleteQuizEventsAfter;
    } 
}
