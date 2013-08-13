/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.angus.coreskillstest.datamanagement.QuizDispatcher;

/**
 *
 * @author JWO
 */
@WebServlet(name = "QuizDispatcherController", urlPatterns = {"/Dispatcher/*"})
public class QuizDispatcherController extends HttpServlet {

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path = req.getRequestURI();
        String [] pathComponents = path.split("/");
        
        
        switch(pathComponents[3])
        {
            case "getevents":
                String emailAddress = req.getParameter("email");
                QuizDispatcher dispatcher = new QuizDispatcher();
                
                dispatcher.getUserByEmail(emailAddress);
                break;
        }
                
        
    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
    }

}
