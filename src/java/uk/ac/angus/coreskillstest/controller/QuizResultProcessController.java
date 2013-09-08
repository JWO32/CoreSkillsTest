package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;

import uk.ac.angus.coreskillstest.quizmanagement.*;
import uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException;

/**
 *
 * @author JWO
 */

// @WebServlet annotation is an alternative to adding Servet Configuration details in web.xml file
@WebServlet(name = "QuizResultProcessController", urlPatterns = {"/ProcessResult/*"})
public class QuizResultProcessController extends HttpServlet
{
    public QuizResultProcessController()
    {
        super();
    }
    
    /**
     * Allows requests for results.  This method will be moved into a specific report controller.
     * 
     * /ProcessResult/getuserresult
     * Get result/s for the specific user
     * 
     * /ProcessResult/getgroupresults
     * Get result/s for the selected group
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
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
            case "getuserresult":
                String resultIdParam = pathComponents[4];
                int resultId = Integer.parseInt(resultIdParam);
               
                break;
            case "getgroupresults":
                String groupIdParam = pathComponents[4];
                int groupId = Integer.parseInt(groupIdParam);
                
                break;
        }
    }
    
    /**
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
 
        ServerClientResponse response = new ServerClientResponse();
        ResultManager rm = new ResultManager();
        String[] pathComponents = path.split("/");
        String quizResultJSON;
        
        switch(pathComponents[3])
        {
            case "add":              
                quizResultJSON = req.getParameter("response");
                //
                // catching exceptions at this level so that errors can be returned to the client
                // without missing any exceptions, should make it clear to the user when their
                // quiz has not been processed.
                //
                try
                {
                    rm.getQuizResources(quizResultJSON);
                    rm.processQuizResult();

                    response = rm.getClientResult();
                    
                    setResponse(response, resp);

                }catch(QuizResourceNotFoundException ex)
                {
                    System.err.println("Error Processing Result");
                    System.err.println(ex.getMessage());
                    response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
                    response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Result Processing Error", "Quiz resource not found, cannot process result"));
                    setResponse(response, resp);
                }catch(Exception ex)
                {
                    System.err.println("Error Processing Result");
                    System.err.println(ex.getMessage());
                    response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
                    response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Result Processing Error", "Quiz resource not found, cannot process result"));
                    setResponse(response, resp);
                } 
                break;
        }  
    }
    
    /**
     * Set the response from the server to the servlet's output which is returned to the client
     * 
     * @param clientResponse
     * @param resp
     * @throws IOException 
     */
    private void setResponse(ServerClientResponse clientResponse, HttpServletResponse resp) throws IOException
    {
        try (PrintWriter output = resp.getWriter()) {
            if(clientResponse.getResponse() == ServerClientResponse.CLIENT_STATUS_ERROR)
            {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.setContentType("text/json");
                output.write(clientResponse.getStatusMessage());
            }else if(clientResponse.getResponse() == ServerClientResponse.CLIENT_STATUS_OK)
            {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("text/json");
                output.write(clientResponse.getClientJson());
            }else if(clientResponse.getResponse() == ServerClientResponse.CLIENT_STATUS_RESPONSE)
            {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("text/json");
                output.write(clientResponse.getStatusMessage());
            }
        }
    }
    
    /**
     * This method allows results to be deleted from the database.
     * 
     * Not required and not implemented in this version of the software.  Placeholder
     * left for further development.
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
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
