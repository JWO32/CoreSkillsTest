package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import uk.ac.angus.coreskillstest.datamanagement.QuizDataAccessObject;

/**
 * Servlet implementation class QuizReceiver
 * 
 * Web browser question creator client directs quiz specification here.
 * 
 * Servlet processes and stores the quiz and returns a success code.
 * 
 */

public class QuizReceiverController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
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
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {

        }

	/**
	 * Quizzes will be transmitted to the server using the HTTP Post method
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
            String path = req.getRequestURI();
            String[] pathComponents = path.split("/");
            PrintWriter output;
            QuizDataAccessObject qDAO = new QuizDataAccessObject();          
            String json;
            
            
            switch(pathComponents[3])
            {
                case "add":  
                    json = req.getParameter("quiz");         
                    qDAO.addNewQuiz(json);
                    
                break;
            }
	}
        
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            
        }

}
