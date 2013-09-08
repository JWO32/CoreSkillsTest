package uk.ac.angus.coreskillstest.quizmanagement;

import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.Result;
import uk.ac.angus.coreskillstest.entity.QuizUser;
import uk.ac.angus.coreskillstest.entity.Question;
import uk.ac.angus.coreskillstest.entity.ResultRule;
import uk.ac.angus.coreskillstest.datamanagement.QuizDataAccessObject;
import uk.ac.angus.coreskillstest.datamanagement.UserDataAccessObject;
import uk.ac.angus.coreskillstest.datamanagement.QuizEventDataAccessObject;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;
import uk.ac.angus.coreskillstest.datamanagement.ResultDataAccessObject;
import uk.ac.angus.coreskillstest.entity.StoredFeedback;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;


/**
 *
 * Processes and returns results.  Links to quiz, user and quiz configuration to ensure that appropriate data is returned
 * 
 * @author JWO
 */
public class ResultManager 
{
    private QuizUserResponse QuizResponse;
    private Quiz SelectedQuiz;
    private QuizUser SelectedUser;
    private QuizEvent SelectedQuizConfiguration;
    private Result QuizResult;
    private int Score = 0;
    private float PercentScore = 0f;
    
    public ResultManager()
    {
        QuizResult = new Result();
    }
    
    public ResultManager(Quiz quiz, QuizUser quizUser, QuizEvent quizConfiguration)
    {
        SelectedQuiz = quiz;
        SelectedUser = quizUser;
        SelectedQuizConfiguration = quizConfiguration;
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
        GsonBuilder gb = new GsonBuilder();
        gb.excludeFieldsWithoutExposeAnnotation();
        Gson gsn = gb.create();
        boolean quizFound, userFound, configFound;
       
        
        QuizResponse = gsn.fromJson(resultJson, QuizUserResponse.class);
        
        quizFound = assignQuiz();
        userFound = assignUser();
        configFound = assignQuizEvent();
                
        if(quizFound == false)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Cannot process result: Unable to locate specified Quiz");
        }else if(userFound == false)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Cannot process result: Unable to locate specified User");
        }else if(configFound == false)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Cannot process result: Unable to locate quiz configuration");
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
        
        quiz = qDAO.getQuizObjectById(quizId);

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
    
    private boolean assignQuizEvent()
    {
        QuizEvent qc;
        QuizEventDataAccessObject qcDAO = new QuizEventDataAccessObject();
      
        int quizConfigurationId = QuizResponse.getQuizEventId();
        
        qc = qcDAO.getQuizEventByIdObject(quizConfigurationId);
        
        if(qc == null)
        {
            SelectedQuizConfiguration = null;
            return false;
        }else
        {
            SelectedQuizConfiguration = qc;
            return true;
        }
    }
    
    /**
     * Calculate the quiz result.
     * 
     * Fetch all questions from the quiz object and extract the database ids of the correct answers.
     * 
     * @throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException 
     */
    public void processQuizResult() throws uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException
    { 
        if(SelectedQuiz == null || SelectedUser == null)
        {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Quiz or User resource not set call assignUser() and assignQuiz() before attempting to calculate the quiz result");
        }
        
        QuestionUserResponse currentUserResponse;
        boolean questionIsCorrect;
        int totalMarks;
        int currentQuestionId;
        
        //Ensure that the Quiz has its total marks added...
        SelectedQuiz.calcTotalMarks();
        
        totalMarks = SelectedQuiz.getTotalMarks();   
        
        List<Question> questionList = SelectedQuiz.getQuestions();
        List<QuestionUserResponse> responseList = QuizResponse.getResponses();
        
        //Bugfix: Deal with situations where the quiz is submitted when not complete.
        //
        // Go through each response.  Find the corresponding question (using primary key ids).
        // Check to see if the ids of the options the user selected as correct are the same
        // as the correct options from the database.
         
        for(QuestionUserResponse currentResponse: responseList)
        {
            currentQuestionId = currentResponse.getQuestionId();
            
            Question currentQuestion = findQuestionById(questionList, currentQuestionId);
            
            List<Integer> correctOptionIds = currentQuestion.getCorrectOptions();
            List<Integer> userSelectionIds = currentResponse.getOptionIdList();
            questionIsCorrect = checkResponseCorrect(correctOptionIds, userSelectionIds);
            
            if(questionIsCorrect)
            {
                Score++;
            }
        }
        
//        for(Question currentQuestion : questionList)
//        {
//            currentQuestionId = currentQuestion.getQuestionId();
//            
//            currentUserResponse = QuizResponse.getResponseByQuestionId(currentQuestionId);
//
//            List<Integer> correctOptionIds = currentQuestion.getCorrectOptions();
//            List<Integer> userSelectionIds = currentUserResponse.getOptionIdList();
//            questionIsCorrect = checkResponseCorrect(correctOptionIds, userSelectionIds);
//         
//            if(questionIsCorrect)
//                Score++;
//        }     
        
        
        QuizResult.setScoreandPercentage(Score, totalMarks);
        //Use the local PercentScore to cache the Percentage Score for the result
        PercentScore = QuizResult.getQuizPercentage();
        
        
        // If the quiz object has rules associated with it
        // run the rules to determine the feedback to the student
        if(SelectedQuiz.getResultRules().size() > 0)
        {
            applyQuizRules();
        }else
        {            
            //TODO: If there are no rules to apply - change this for a default feedback object.
            QuizResult.setLinkedFeedback(StoredFeedback.getDefaultFeedback());
        }
        
        // Quiz Result is complete - serialise to the database
        saveResult();
    }
    
    /**
     * This method has been placed here for convenience, eventually remove to Quiz
     * class
     * 
     * Search for the question that matches the question id.  Very simple implementation
     * probably inefficient, but question lists are never going to be large enough that
     * this becomes a serious problem.
     * 
     */
    public Question findQuestionById(List<Question> questionList, int searchId)
    {
        Question q = null;
        
        for(Question currentQuestion : questionList)
        {
            if(currentQuestion.getQuestionId() == searchId)
            {
                q = currentQuestion;
            }
        }
        
        return q;
    }
    
    /**
     * Return the current instance of the QuizResult Object
     * @return 
     */
    public ServerClientResponse getClientResult()
    {
        ServerClientResponse response = new ServerClientResponse();
        String responseJson;
        
        // Check to see if the current quiz configuration allows the result to be returned.
        // If it does, we need:
        // String (if any)
        // Score
        // Number of questions
        // Percentage
        if(SelectedQuizConfiguration.isReturnResult())
        {
            responseJson = ResultDataAccessObject.getResultObjectasJSON(QuizResult);
            QuizResult.setResultStatus(Result.SUCCESS_RESULT_AVAILABLE);
            
            response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
            response.setClientJson(responseJson);
        }else
        {
            QuizResult.setResultStatus(Result.SUCCESS_RESULT_NOT_AVAILABLE);
            response.setResponse(ServerClientResponse.CLIENT_STATUS_RESPONSE);
            response.setStatusMessage(ServerClientResponseFactory.formatStatusJSON("Result Not Available", "Your result has been processed but has been witheld.  Please close the window when you are ready."));
        }
        
        return response;
    }
    
    /**
     * Takes the question ids of the correct answers for the current question and then compares with the user responses
     * Since the ids are just integers, sort them into the correct order first.
     * 
     * @param correctQuestionOptions
     * @param userResponses
     * @return 
     */
    private boolean checkResponseCorrect(List<Integer> correctQuestionOptions, List<Integer> userResponses)
    {
        boolean responseCorrect = false;
        
        if(correctQuestionOptions.size() == userResponses.size())
        {
            java.util.Collections.sort(userResponses);
            java.util.Collections.sort(correctQuestionOptions);
            
            if(userResponses.equals(correctQuestionOptions))
            {
                responseCorrect = true;
            }
        }else
        {
            return responseCorrect;
        }
        
        return responseCorrect;
    }
    
    /**
     * Fetch the list of quiz rules from the quiz and check if one of them applies
     * to the score achieved by the user.
     */
    private void applyQuizRules()
    {
        List<ResultRule> ResultRuleList = SelectedQuiz.getResultRules();
        String ruleFeedback = null;
        
        for(ResultRule currentRule : ResultRuleList)
        {
            if(currentRule.appliesTo(PercentScore))
            {
                // If the Rule is pass or fail
                if(currentRule.isPassFail() == true)
                {
                    ruleFeedback = "Pass";
                }else
                {
                    ruleFeedback = "Fail";
                }
                
                //If the rule is not pass or fail then apply rule feedback
                //This will overwrite the 'fail' feedback.
                if(currentRule.isPassFail() == false)
                {
                    ruleFeedback = currentRule.getFeedback();
                }
            }
            
        }
        
        if(ruleFeedback == null)
        {
            //Set default feedback
            QuizResult.setLinkedFeedback(StoredFeedback.getDefaultFeedback()); 
        }
        else
        {
             QuizResult.setLinkedFeedback(ruleFeedback);
        }
    }
    
    /**
     * Utility function to return the current result object as JSON for transmission back to the client
     * 
     * @return 
     */
    public String getResultJson()
    {
        String json = ResultDataAccessObject.getResultObjectasJSON(QuizResult);
        
        return json;
    }
    
    /**
     * Serialises the instance of QuizResult
     */
    public void saveResult()
    {
        ResultDataAccessObject rDAO = new ResultDataAccessObject();
        
        QuizResult.setQuizUser(SelectedUser);
        QuizResult.setLinkedQuiz(SelectedQuiz);
        
        rDAO.addResultObject(QuizResult);
    }
    
    public int getScore()
    {
        return Score;
    }
    
    public void setScore(int newScore)
    {
        Score = newScore;
    }
}
