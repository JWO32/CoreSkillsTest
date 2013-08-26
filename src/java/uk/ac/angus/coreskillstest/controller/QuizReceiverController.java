package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;


import uk.ac.angus.coreskillstest.datamanagement.QuizDataAccessObject;

import uk.ac.angus.coreskillstest.quizmanagement.exception.UnabletoAddResourceException;
/**
 * Servlet implementation class QuizReceiver
 * 
 * Web browser question creator client directs quiz specification here.
 * 
 * Servlet processes and stores the quiz and returns a success code.
 * 
 */

//TODO: Refactor to take ServerClientResponse into account
//TODO: Refactor to return status and error messages to the client.

public class QuizReceiverController extends HttpServlet 
{   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizReceiverController() 
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * 
     * Needs to be refactored
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path = req.getRequestURI();
        String[] pathComponents = path.split("/");
        QuizDataAccessObject qDAO = new QuizDataAccessObject();
        ServerClientResponse response = new ServerClientResponse();

        switch(pathComponents[3])
        {
            case "getfulldetails":

                try
                {
                    response = qDAO.getAllQuizzes();
                }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
                {
                    System.err.println("Error: Quizzes not available.");   
                }
                
                setResponse(response, resp);

            break;                
            case "quizlist":
                response = qDAO.getShortQuizList();
                
                setResponse(response, resp);
            break;
            case "edit":
                HttpSession session = req.getSession();
                String quizParam = pathComponents[4];//req.getParameter("quizId");
                int quizId = Integer.parseInt(quizParam);
 
                response = qDAO.getQuizById(quizId);
                
                // No need to send the respons object, return JSON for client.
                session.setAttribute("Quiz", response.getClientJson());
                
                resp.sendRedirect("/CoreSkillsTest/createquiz.jsp");   
                break;
        }
    }

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
     * Quizzes will be transmitted to the server using the HTTP Post method
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path = req.getRequestURI();
        String[] pathComponents = path.split("/");
        PrintWriter output = resp.getWriter();
        QuizDataAccessObject qDAO = new QuizDataAccessObject();    
        ServerClientResponse response = new ServerClientResponse();
        String json;

        switch(pathComponents[3])
        {
            case "add":  
                json = req.getParameter("quiz");
                try
                {
                    qDAO.addNewQuizByJson(json);
                }catch(UnabletoAddResourceException ex)
                {
                    String returnMessage = ex.getMessage();

                    output.write(returnMessage);
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                break;
            case "edit":
                String quizParam = req.getParameter("quiz");
                String quizIdParam = req.getParameter("quizId");
                int quizId = Integer.parseInt(quizIdParam);
                
                try
                {
                    qDAO.editQuizById(quizId, quizParam);
                }catch(Exception ex)
                {
                    System.err.println("Unable to edit quiz");
                    System.err.println(ex.getMessage());
                    response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
                    response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Unable to Edit Quiz", "There has been an error updating the quiz, please try again"));
                }
                
                setResponse(response, resp);                
            break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = req.getRequestURI();
        ServerClientResponse response;
        String[] pathComponents = path.split("/");
        QuizDataAccessObject qDAO = new QuizDataAccessObject();
        //
        // Delete request doesn't have associated parameters, so data must be passed as
        // part of the url.
        String quizParameter = pathComponents[4];
        int quizId = Integer.parseInt(quizParameter);
        switch(pathComponents[3])
        {
            case "delete":
                try
                {
                    response = qDAO.deleteQuizById(quizId);
                }catch(Exception ex)
                {
                    System.err.println("Error: unable to delete quiz");
                    System.err.println(ex.getMessage());
                    response = new ServerClientResponse();
                    response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
                    response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Servlet Error", "Servlet has produced an error deleting the quiz."));
                }         
                setResponse(response, resp);
            break;
        }
    }
}
