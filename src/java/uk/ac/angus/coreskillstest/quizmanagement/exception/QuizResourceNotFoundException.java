/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.quizmanagement.exception;

/**
 *
 * @author JWO
 */
public class QuizResourceNotFoundException extends Exception {

    /**
     * Creates a new instance of
     * <code>QuizResourceNotFoundException</code> without detail message.
     */
    public QuizResourceNotFoundException() 
    {
        super();
    }

    /**
     * Constructs an instance of
     * <code>QuizResourceNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public QuizResourceNotFoundException(String msg) 
    {
        super(msg);
    }
}
