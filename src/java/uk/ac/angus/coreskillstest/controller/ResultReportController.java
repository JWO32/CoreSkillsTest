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
 * @author JWO
 */
@WebServlet(name = "ResultReportController", urlPatterns = {"/Result/*"})
public class ResultReportController extends HttpServlet 
{


    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
        ServerClientResponse clientResponse = null;
        
        switch(pathComponents[3])
        {
            case "getgroupresults":
                String groupParam = req.getParameter("groupId");
                int groupId = Integer.parseInt(groupParam);
                clientResponse = repDAO.getSingleItem(groupId);
                
                processResponse(clientResponse, resp);
                
                break;
        }    
    }

    private void processResponse(ServerClientResponse response, HttpServletResponse resp)
    {
        
    }
    
    
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        
    }

}
