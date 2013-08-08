/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.angus.coreskillstest.datamanagement.QuizEventDataAccessObject;

/**
 *
 * @author JWO
 */
public class QuizEventController extends HttpServlet 
{
    /**
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path = req.getRequestURI();
        String[] pathComponents = path.split("/");
        PrintWriter output = resp.getWriter();
        String json;

        switch(pathComponents[3])
        {
            case "eventlist":
                QuizEventDataAccessObject qeDAO = new QuizEventDataAccessObject();
                
                json = qeDAO.getAllQuizEventsJSON();
                
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("text/json");
                output.write(json);
                break;
        }      
    }

    /**
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path = req.getRequestURI();
        String[] pathComponents = path.split("/");
        PrintWriter output = resp.getWriter();
        String eventJSON = req.getParameter("event");
        
        switch(pathComponents[3])
        {
            case "addevent":
                QuizEventDataAccessObject qeDAO = new QuizEventDataAccessObject();
                
                qeDAO.addQuizEventJSON(eventJSON);
                
                break;
        }
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        
    }
}
