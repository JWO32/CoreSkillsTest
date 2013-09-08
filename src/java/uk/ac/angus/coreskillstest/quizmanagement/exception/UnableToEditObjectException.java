/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement.exception;

/**
 *
 * @author JWO
 */
public class UnableToEditObjectException extends Exception 
{
    public UnableToEditObjectException()
    {
        super();
    }
    
    public UnableToEditObjectException(String msg)
    {
        super(msg);
    }
}
