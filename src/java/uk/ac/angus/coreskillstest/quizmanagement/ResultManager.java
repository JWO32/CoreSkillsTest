package uk.ac.angus.coreskillstest.quizmanagement;

import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.Result;
import uk.ac.angus.coreskillstest.entity.QuizUser;

import uk.ac.angus.coreskillstest.datamanagement.QuizDataAccessObject;
import uk.ac.angus.coreskillstest.datamanagement.UserDataAccessObject;

import com.google.gson.Gson;

/**
 *
 * @author JWO
 */
public class ResultManager 
{
    private QuizUserResponse QuizResponse;
    private Quiz SelectedQuiz;
    private QuizUser SelectedUser;
    
    private int Score = 0;
    
    public ResultManager()
    {
        
    }
    
    public ResultManager(Quiz quiz, QuizUser quizUser)
    {
        SelectedQuiz = quiz;
        SelectedUser = quizUser;
    }
    
    /**
     * 
     * Json string containing user response to quiz.  This is deserialised into QuizUserResponse class
     * calls assignQuiz() and assignUser() to look up Quiz and User by ids.
     * 
     * @param resultJson
     * @return 
     */
    public void getQuizResources(String resultJson) throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
    {
        Gson gsn = new Gson();
        boolean quizFound, userFound;
        
        QuizResponse = gsn.fromJson(resultJson, QuizUserResponse.class);
        
        quizFound = assignQuiz();
        userFound = assignUser();
     
        if(quizFound == false)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Cannot process result: Unable to locate specified Quiz");
        }else if(userFound == false)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Cannot process result: Unable to locate specified User");
        }
    }
    
    /**
     * 
     * Load the Quiz identified by the QuizId already assigned to the QuizResponse object
     * 
     */
    private boolean assignQuiz()
    {
        Quiz quiz;
   
        QuizDataAccessObject qDAO = new QuizDataAccessObject();   
        
        int quizId = QuizResponse.getQuizId();      
        
        quiz = qDAO.getQuizById(quizId);

        if(quiz == null)
        {
            SelectedQuiz = null;
            return false;
        }       
        else
        {
            SelectedQuiz = quiz;
            return true;
        }
    }
    
    /**
     * 
     * Load the User identified by the UserId already assigned to the QuizResponse object
     */
    
    private boolean assignUser()
    {
        QuizUser user;
        
        UserDataAccessObject uDAO = new UserDataAccessObject();
        
        int userId = QuizResponse.getUserId();
        
        user = uDAO.fetchSingleUserObject(userId);
        
        if(user == null)
        {
            SelectedUser = null;
            return false;
        }else
        {
            SelectedUser = user;
            return true;
        }
    }
    
    public Result getQuizResult()
    {
        Result quizResult = new Result();
        
        
        return quizResult;
    }
    
    public void applyQuizRules(Quiz quiz)
    {
        
    }
}
