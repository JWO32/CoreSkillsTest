/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement.exception;

/**
 *
 * @author james
 */
public class UnableToCommitException extends Exception {

    /**
     * Creates a new instance of
     * <code>UnableToCommitException</code> without detail message.
     */
    public UnableToCommitException() 
    {
        super();
    }

    /**
     * Constructs an instance of
     * <code>UnableToCommitException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UnableToCommitException(String msg) 
    {
        super(msg);
    }
}
