/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.datamanagement.ReportDataAccessObject;

/**
 *
 * Responsible for collecting data required for the reporting client to generate student reports
 * 
 * Only requires get interface because deleting/modifying results would be the responsibility of the
 * Result controller
 * 
 * @author JWO
 */
@WebServlet(name = "ResultReportController", urlPatterns = {"/Report/*"})
public class ResultReportController extends HttpServlet 
{
    /**
     * /Report/getgroupresults
     * Initiates gathering of group results for the specified quiz.  the gorupId and the
     * Quiz Id are both required
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path = req.getRequestURI();
        String[] pathComponents = path.split("/");
        ReportDataAccessObject repDAO = new ReportDataAccessObject();
        ServerClientResponse clientResponse;
        
        switch(pathComponents[3])
        {
            case "getgroupresults":
                String groupParam = req.getParameter("groupId");
                String quizParam = req.getParameter("quizId");
                int groupId = Integer.parseInt(groupParam);
                int quizId = Integer.parseInt(quizParam);
                
                clientResponse = repDAO.getSingleItem(groupId, quizId);
         
                setResponse(clientResponse, resp);
                
                break;
        }    
    }

    /**
     * Sets the response from the server to the client
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
}
