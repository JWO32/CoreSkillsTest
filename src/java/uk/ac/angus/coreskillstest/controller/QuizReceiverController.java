package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
         */
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
        {
            String path = req.getRequestURI();
            String[] pathComponents = path.split("/");
            PrintWriter output = resp.getWriter();
            QuizDataAccessObject qDAO = new QuizDataAccessObject();
            String json;
            
            switch(pathComponents[3])
            {
                case "get":
                    
                    int quizId = Integer.valueOf(pathComponents[4]);
                    
                    qDAO.getQuizById(quizId);
                break;                
                case "quizlist":
                    json = qDAO.getShortQuizList();
                    resp.setContentType("text/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                    output.write(json);
                break;
                
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
            }
	}
        
        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            String path = req.getRequestURI();
            String[] pathComponents = path.split("/");
            PrintWriter output;
            QuizDataAccessObject qDAO = new QuizDataAccessObject();
            
            switch(pathComponents[3])
            {
                case "delete":
                    
                break;
            }
        }

}
