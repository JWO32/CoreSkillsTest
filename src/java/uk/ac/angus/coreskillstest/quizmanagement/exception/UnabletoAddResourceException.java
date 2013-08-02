/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement.exception;

/**
 *
 * @author JWO
 */
public class UnabletoAddResourceException extends Exception {

    /**
     * Creates a new instance of
     * <code>UnabletoAddResourceException</code> without detail message.
     */
    public UnabletoAddResourceException() 
    {
    }

    /**
     * Constructs an instance of
     * <code>UnabletoAddResourceException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public UnabletoAddResourceException(String msg) 
    {
        super(msg);
    }
}
