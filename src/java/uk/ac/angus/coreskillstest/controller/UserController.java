package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;

import uk.ac.angus.coreskillstest.datamanagement.UserDataAccessObject;
import uk.ac.angus.coreskillstest.datamanagement.GroupDataAccessObject;


/**
 * Servlet implementation class UserController
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
 * /edit/user/ <POST PARAMETER>
 * /edit/group/ <POST PARAMETER>
 * 
 * /get/user/<USERID>
 * /get/group/<GROUPID>
 * /get/allusers
 * /get/allgroups
 * 
 * /delete/user/<USERID>
 * /delete/users/
 * /delete/group/<GROUPID>
 */

/*
 * This servlet has been partially refactored - doDelete now uses setResponse.
 * 
 * TODO: edit the doGet method so that all requests use the setResponse method and pass
 * data back to the client using the ServerClientResponse JSON object
 * 
 */

public class UserController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
        
        /**
         * @see HttpServlet#HttpServlet()
         */
        public UserController() 
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
            PrintWriter output;
            UserDataAccessObject uDAO;
            GroupDataAccessObject gDAO;
            String json;
         
            if(pathComponents[3].equals("get"))
            {
                switch (pathComponents[4]) 
                {
                    case "allusers":                
                        uDAO = new UserDataAccessObject();
                        
                        json = uDAO.fetchAllUsersJson();
                        
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.setContentType("application/json");
                        output = resp.getWriter();
                        output.write(json);             
                        break;
                    case "allgroupsandusers":
                        gDAO = new GroupDataAccessObject();
                        
                        json = gDAO.fetchAllGroupsandUsersJSON();
                        
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.setContentType("application/json");
                        output = resp.getWriter();
                        output.write(json);                       
                        break;
                    case "allgroups":
                         gDAO = new GroupDataAccessObject();
                        
                        json = gDAO.fetchAllGroupsJSON();
                        
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.setContentType("application/json");
                        output = resp.getWriter();
                        output.write(json);         
                        break;
                    case "user":
                        uDAO = new UserDataAccessObject();
                        
                        String userId = req.getParameter("userid");
                        
                        json = uDAO.fetchSingleUserJson(Integer.valueOf(userId));
                        
                        resp.setStatus(HttpServletResponse.SC_OK);
                        output = resp.getWriter();
                        output.write(json);                    
                        break;
                    case "group":
                        gDAO = new GroupDataAccessObject();
                        
                        String groupId = req.getParameter("groupid");
                        
                        json = gDAO.fetchGroupByIdJSON(Integer.valueOf(groupId));
                        
                        resp.setStatus(HttpServletResponse.SC_OK);
                        output = resp.getWriter();
                        output.write(json);                   
                        break;   
                        
                    case "groupdetails":
                        gDAO = new GroupDataAccessObject();
                        json = gDAO.fetchGroupDetailsJSON();
                        output = resp.getWriter();
                        output.write(json);
                        resp.setContentType("text/json");
                        resp.setStatus(HttpServletResponse.SC_OK);                     
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
            UserDataAccessObject uDAO = new UserDataAccessObject();
            GroupDataAccessObject gDAO = new GroupDataAccessObject();
            ServerClientResponse response;
            
            if(!pathComponents[2].equals("User"))
            {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentLength(0);
                resp.setContentType("text/plain");
                return;
            }
            
            //Add users and groups
            //
            if(pathComponents[3].equals("add"))
            {
                  switch(pathComponents[4])
                  {
                      case "user":
                          String userJson = req.getParameter("user");
                          String groupParam = req.getParameter("groupId");
                          int groupId = Integer.parseInt(groupParam);
                          
                          response = uDAO.addSingleUser(userJson, groupId);

                          setResponse(response, resp);
                      break;
                      case "group":
                          String groupJson = req.getParameter("group");
                          response = gDAO.addSingleGroup(groupJson);
                          
                          setResponse(response, resp);                      
                      break;                         
                  }
            }   
            
            //Edit functionality is not required for version 1 - users are deleted
            //and re-added if required.  This will be need when the system is needed
            //to deal with large numbers of users
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
    
    /**
     * Incorporated during refactoring - not all methods take advantage of setResponse
     * 
     * TODO: refactor class to use setResponse to return data to client
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
         * Refactored to use the setResponse method
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
            UserDataAccessObject uDAO = new UserDataAccessObject();
            GroupDataAccessObject gDAO = new GroupDataAccessObject();
            ServerClientResponse response;
            
            if (pathComponents[3].equals("delete"))
            {
                switch(pathComponents[4])
                {
                    case "user":                     
                        String userIdParam = pathComponents[5];
                        int userId = Integer.parseInt(userIdParam);
                        response = uDAO.deleteUser(userId);

                        setResponse(response, resp);
                        break;
                    case "group":
                        String groupIdparam = pathComponents[5];
                        int groupId = Integer.parseInt(groupIdparam);
                        response = gDAO.deleteGroup(groupId);
                        
                        setResponse(response, resp);
                }
            }    
        }
}
