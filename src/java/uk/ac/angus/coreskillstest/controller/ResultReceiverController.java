package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JWO
 */
public class ResultReceiverController extends HttpServlet
{
    public ResultReceiverController()
    {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path = req.getRequestURI();
        
        String[] pathComponents = path.split("/");
        String quizResultJSON;
        
        switch(pathComponents[3])
        {
            case "add":
                
                quizResultJSON = req.getParameter("response");
                
                // Create response object
                // load associated quiz object
                // fetch questions and result rules
                // match responses to question options
                // calculate total score
                // match total score to quiz result rules
                // 
                
                
                break;
        }
        
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        
    }
}
