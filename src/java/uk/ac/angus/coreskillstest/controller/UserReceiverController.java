package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import uk.ac.angus.coreskillstest.DataManagement.UserDataAccessObject;


/**
 * Servlet implementation class UserReceiverController
 * 
 * Users and groups created by the user are sent to this servlet as XML.
 * XML is parsed and stored in database.
 * 
 * The recognised REST urls are as follows:
 * 
 * /add/users
 * /add/user
 * /add/group
 * /add/groupwithusers
 * 
 * /edit/user/
 * /edit/group/
 * 
 * /get/user
 * /get/group
 * /get/allusers
 * /get/allgroups
 * 
 * /delete/user/
 * /delete/users/
 * /delete/group/
 */

public class UserReceiverController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
        
       
        /**
         * @see HttpServlet#HttpServlet()
         */
        public UserReceiverController() 
        {
            super();
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         * 
         * Get Requests will be used to fetch data from the server...
	 */
        @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
            String path = req.getRequestURI();
            String[] pathComponents = path.split("/");
            String json;
            
            if(pathComponents[3].equals("get"))
            {
                switch (pathComponents[4]) 
                {
                    case "allusers":                
                        UserDataAccessObject uDAO = new UserDataAccessObject();
                        
                        json = uDAO.fetchAllUsers();
                        
                        resp.setStatus(HttpServletResponse.SC_OK);
                        PrintWriter output = resp.getWriter();
                        output.write(json);             
                        break;
                    case "allgroups":
                        
                        break;
                    case "user":

                        
                        break;
                    case "group":
                        
                        break;               
                }
            }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         * 
         * Most requests will be made over POST from forms, rather than get requests.
         * 
         * 
	 */
        @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
            String path = req.getRequestURI();
            String[] pathComponents = path.split("/");
            
            if(!pathComponents[2].equals("User"))
            {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentLength(0);
                resp.setContentType("text/plain");
                return;
            }
            
            if(pathComponents[3].equals("add"))
            {
                // Indicates that there will be more than one user
                //
                if(pathComponents[4].equals("users"))
                {
                    
                }
            }   
            
            if(pathComponents[3].equals("edit"))
            {
                
                switch(pathComponents[4])
                {
                    case "user":
                        
                        break;
                    case "group":
                        
                        break;        
                }  
            }
	}
        
        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            String path = req.getRequestURI();
            String[] pathComponents = path.split("/");
            
            if (pathComponents[2].equals("delete"))
            {
                
            }
            
        }
}
