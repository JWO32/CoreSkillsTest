package uk.ac.angus.coreskillstest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.datamanagement.QuizDispatcher;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizPackage;

/**
 *
 * The quiz dispatcher controller is responsible for managing quiz events and
 * quiz 'packages'.
 * 
 * This controller only needs to deal with GET requests.
 * 
 * @author JWO
 */
@WebServlet(name = "QuizDispatcherController", urlPatterns = {"/Dispatcher/*"})
public class QuizDispatcherController extends HttpServlet {

    /**
     * doGet Rest interface deals with two events:
     * getEvents - gets all events for the supplied e-mail address and returns to the client
     * doQuiz - gets the UserID and EventID and gets the quiz associated quiz and passes to
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String path = req.getRequestURI();
        String [] pathComponents = path.split("/");
        ServerClientResponse response;
        QuizDispatcher dispatcher;
        QuizPackage quizPackage;
        
        switch(pathComponents[3])
        {
            case "getevents":
                String emailAddress = req.getParameter("email");
                dispatcher = new QuizDispatcher();
                
                dispatcher.getUserByEmail(emailAddress);
                response = dispatcher.getValidQuizEventsForUser();
                setResponse(response, resp);
                
                break;
            case "doquiz":
                HttpSession session = req.getSession();
                dispatcher = new QuizDispatcher();
                int userId = Integer.parseInt(pathComponents[4]);
                int eventId = Integer.parseInt(pathComponents[5]);
                
                quizPackage = dispatcher.getQuizForEvent(userId, eventId);
                
                // Set the quiz along with the start and end message
                session.setAttribute("QuizPackage", quizPackage);
                
                // Direct the browser to the quiz player.
                // Wonder if there's a better way to deal with the base directory.
                resp.sendRedirect("/CoreSkillsTest/quizplayer.jsp");
                break;
        }   
    }

    /**
     * Takes the response obtained from the interaction in the GET or POST method and write the
     * server response to the output that will be sent to the web browser
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
