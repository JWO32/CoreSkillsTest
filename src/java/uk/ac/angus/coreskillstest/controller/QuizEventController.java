/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.datamanagement.QuizEventDataAccessObject;

/**
 *
 * This class manages requests around quiz events.  These requests come from the setupquiz.jsp file
 * 
 * 
 * @author JWO
 */
public class QuizEventController extends HttpServlet 
{
    /**
     * 
     * /Event/eventlist
     * Initiates get all quiz events.  Returns to web client.
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
        ServerClientResponse response;

        switch(pathComponents[3])
        {
            case "eventlist":
                QuizEventDataAccessObject qeDAO = new QuizEventDataAccessObject();
                
                response = qeDAO.getAllItems();
                
                setResponse(response, resp);
                break;
        }      
    }

    /**
     * Allows quiz events to be added and edited.  Response (success) returned to client
     * 
     * Rest request /Event/addevent
     * Adds a new quiz event
     * 
     * Rest request /Event/editevent
     * Edits selected quiz event
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
        QuizEventDataAccessObject qeDAO = new QuizEventDataAccessObject();
        String eventJSON = req.getParameter("event");
        ServerClientResponse clientResponse;
        
        switch(pathComponents[3])
        {
            case "addevent":          
                clientResponse = qeDAO.addItemJson(eventJSON);
                
                setResponse(clientResponse, resp);
                
                break;
            case "editevent":
                clientResponse = qeDAO.updateItem(eventJSON);
                
                setResponse(clientResponse, resp);
                
                break;
        }     
    }
     
    /**
     * Set the response from the server operations to the client may be success/fail message or data
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
            }
        }
    }
    
    /**
     * /Event/delete
     * Deletes the quiz event by specified ID (passed as a parameter)
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = req.getRequestURI();
        String[] pathComponents = path.split("/");
        ServerClientResponse clientResponse;
        //int quizEventId = Integer.parseInt(req.getParameter("quizeventid"));
        
        //Delete requests don't come with parameters so the id to delete is included
        //in the request URI.
        int quizEventId = Integer.parseInt(URLDecoder.decode(pathComponents[4], "UTF-8"));
        
        switch(pathComponents[3])
        {
            case "delete":
                QuizEventDataAccessObject qDAO = new QuizEventDataAccessObject();
                
                clientResponse = qDAO.deleteSingleItem(quizEventId);
                
                setResponse(clientResponse, resp);               
                break;
        }
    }
}
