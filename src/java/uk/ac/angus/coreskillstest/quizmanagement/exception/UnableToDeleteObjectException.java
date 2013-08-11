/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement.exception;

/**
 *
 * @author JWO
 */
public class UnableToDeleteObjectException extends Exception {

    /**
     * Creates a new instance of
     * <code>UnableToDeleteObjectException</code> without detail message.
     */
    public UnableToDeleteObjectException() 
    {
    }

    /**
     * Constructs an instance of
     * <code>UnableToDeleteObjectException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public UnableToDeleteObjectException(String msg) 
    {
        super(msg);
    }
}
