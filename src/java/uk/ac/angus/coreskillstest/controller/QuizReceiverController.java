package uk.ac.angus.coreskillstest.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import uk.ac.angus.coreskillstest.entity.QuizUser;
import uk.ac.angus.coreskillstest.entity.QuizGroup;


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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
	}
        
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            
        }

}
