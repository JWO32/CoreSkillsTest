/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement.exception;

/**
 *
 * @author JWO
 */
public class CannotGenerateQuizException extends Exception {

    /**
     * Creates a new instance of
     * <code>CannotGenerateQuizException</code> without detail message.
     */
    public CannotGenerateQuizException() 
    {
        super();
    }

    /**
     * Constructs an instance of
     * <code>CannotGenerateQuizException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public CannotGenerateQuizException(String msg) 
    {
        super(msg);
    }
}
