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
        private EntityManagerFactory factory;
        private static final String p_unit_name = "CoreSkillsTestPU";
       
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
            factory = Persistence.createEntityManagerFactory(p_unit_name);
            EntityManager objEntityManager = factory.createEntityManager();

            objEntityManager.getTransaction().begin();

            QuizGroup ug = new QuizGroup();

            ug.setGroupName("NC Computing");
            ug.setGroupDescription("This is the computing group");

            QuizUser newUser1 = new QuizUser();

            newUser1.setFirstName("Alistair");
            newUser1.setLastName("Keys");
            newUser1.setUserDescription("This is a new user");
            newUser1.setUserEmail("akeys@angus.ac.uk");
            java.util.Calendar dob = java.util.Calendar.getInstance();
            dob.set(1980, 3, 18);
            newUser1.setUserDateAdded(dob);        
            objEntityManager.persist(newUser1);

            QuizUser newUser2 = new QuizUser();
            newUser2.setFirstName("Bob");
            newUser2.setLastName("Pickering");
            newUser2.setUserDescription("Here is another new user");
            newUser2.setUserEmail("james@james.com");
            newUser2.setUserDateAdded(dob);
            objEntityManager.persist(newUser2);

            QuizUser qu3 = new QuizUser();
            qu3.setFirstName("Moron");
            qu3.setLastName("The Moron");
            qu3.setUserDescription("This is the next user");
            qu3.setUserEmail("mo@ron.com");
            qu3.setUserDateAdded(dob);       
            objEntityManager.persist(qu3);
            
            ug.getUserList().add(newUser1);
            ug.getUserList().add(newUser2);
            ug.getUserList().add(qu3);
            
            
            objEntityManager.persist(ug);

            objEntityManager.getTransaction().commit();
            objEntityManager.close();

            objEntityManager = factory.createEntityManager();
            
            Query q = objEntityManager.createQuery("SELECT p FROM QUIZ_GROUP p");
            
            List <QuizGroup> list = q.getResultList();

            for(QuizGroup group:list)
            {
                System.out.println("Group ID: " + group.getGroupID());
                System.out.println("Group Name: " + group.getGroupName());
                System.out.println("Group Description: " + group.getGroupDescription());
                
            }


            try {
               JAXBContext jaxbContext = JAXBContext.newInstance(QuizGroup.class);
               Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

               jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

               jaxbMarshaller.marshal(ug, System.out);

           } catch (JAXBException ex) {
               Logger.getLogger(QuizReceiverController.class.getName()).log(Level.SEVERE, null, ex);
           }
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
