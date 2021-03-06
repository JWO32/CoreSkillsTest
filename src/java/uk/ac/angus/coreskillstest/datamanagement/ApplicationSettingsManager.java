
package uk.ac.angus.coreskillstest.datamanagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of a Servlet to manage global application settings.
 * 
 * 
 * Added in sprint 7 - will be required to set when expired QuizEvents should be deleted
 * Stub Only - NOT IMPLEMENTED
 * 
 * 
 * @author JWO
 */
@WebServlet(name = "ApplicationSettingsManager", urlPatterns = {"/Settings/*"})
public class ApplicationSettingsManager extends HttpServlet 
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
}
