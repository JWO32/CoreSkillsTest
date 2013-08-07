package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.angus.coreskillstest.quizmanagement.*;
import uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException;

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
        String path;
        path = req.getRequestURI();
        PrintWriter output;       
        output = resp.getWriter();
        ResultManager rm = new ResultManager();
        String[] pathComponents = path.split("/");
        
        switch(pathComponents[3])
        {
            case "get":
                String resultIdParam = pathComponents[4];
                int resultId = Integer.parseInt(resultIdParam);
               
            break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path;  
        path = req.getRequestURI();
        PrintWriter output;
        output = resp.getWriter();
        ResultManager rm = new ResultManager();
        String[] pathComponents = path.split("/");
        String quizResultJSON;
        
        switch(pathComponents[3])
        {
            case "add":              
                quizResultJSON = req.getParameter("response");               
                try
                {
                    rm.getQuizResources(quizResultJSON);
                    rm.processQuizResult();
                    
                    quizResultJSON = rm.getResultJson();
                    
                    output.write(quizResultJSON);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.setContentType("text/json");
                }catch(QuizResourceNotFoundException ex)
                {
                    String errorMessage = ex.getMessage();
                    output.write(errorMessage);
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.setContentType("text/html");
                }finally
                {
                    output.close();
                }
                break;
        }  
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path;
        
        path = req.getRequestURI();
        PrintWriter output;
        output = resp.getWriter();
        ResultManager rm = new ResultManager();
        String[] pathComponents = path.split("/");
        
        switch(pathComponents[3])
        {
            case "delete":
                
            break;
        }
    }

}
