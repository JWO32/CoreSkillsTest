package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.angus.coreskillstest.quizmanagement.*;
import uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException;

/**
 *
 * @author JWO
 */

@WebServlet(name = "QuizResultProcessController", urlPatterns = {"/ProcessResult/*"})
public class QuizResultProcessController extends HttpServlet
{
    public QuizResultProcessController()
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
            case "getgroup":
                String groupIdParam = pathComponents[4];
                int groupId = Integer.parseInt(groupIdParam);
                
                
                break;
        }
    }
    
    /**
     * 
     * TODO: Refactor to take ServerClientResponse object into account
     * 
     * Process a new result with the 'add' command.
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
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
                }catch(Exception ex)
                {
                    System.err.println("Error processing result");
                    System.err.println(ex.getMessage());
                } finally
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
