QuizPlayerModel = function()
{
  var Quiz = 0;
  var QuizIntroduction = 0;
  var QuizEnd = 0;
  
  var UserSelections = new UserSelection();
  
  var Responses = new Array();
  var NumberOfQuestions = 0;
  var NumberOfMarks = 0;
  
    return {
      initModel: function()
      {
          NumberOfQuestions = Quiz.Questions.length;
          
      },
      setQuizJson: function(newQuiz, startDetails, endDetails)
      {          
          Quiz = jQuery.parseJSON(newQuiz);
          QuizIntroduction = jQuery.parseJSON(startDetails);
          QuizEnd = jQuery.parseJSON(endDetails);
      },
      setSelectionDetails: function(quizId, userId)
      {
          UserSelections.QuizId = quizId;
          UserSelections.UserId = userId;
      },
      getQuizId: function()
      {
        return Quiz.QuizId;  
      },
      getUserId: function()
      {
        return Quiz.UserId;  
      },
      setQuiz: function(newQuiz)
      {
          Quiz = newQuiz;
      },
      getQuiz: function()
      {
          return Quiz;
      },
      getNumberOfMarks: function()
      {
          return NumberOfMarks;
      },
      setNumberOfMarks: function(numberOfMarks)
      {
          NumberOfMarks = numberOfMarks;
      },
      calcNumberOfMarks: function()
      {
          for(var i = 0; i < NumberOfQuestions; i++)
          {
              
          }
      },
      getQuestionList: function()
      {
        return Quiz.Questions;  
      },
      addResponse: function(questionNumber, questionID, response)
      {
          var Response = new QuestionResponse();
          
          Response.QuestionId = questionID;
          Response.SelectedResponse = response;
                 
          Responses[questionNumber] = Response;
      },
      getResponse: function(responseIndex)
      {
          if(Responses[responseIndex])
            return Responses[responseIndex];
          else
            return null;
      },
      getResponses: function()
      {
          return Responses;
      },
      getDuration: function()
      {
          return Quiz.QuizDuration;
      },
      getNumberOfQuestions: function()
      {
          return Quiz.Questions.length;
      },
      getQuizEndObject: function()
      {
          return QuizEnd;
      },
      getQuizIntroductionObject: function()
      {
          return QuizIntroduction;  
      },
      getQuizIntroductionJSON: function()
      {
          return JSON.stringify(QuizIntroduction);
      },
      getQuizTitle: function()
      {
          return QuizIntroduction.Title;
      },
      getQuizInstructions: function()
      {
          return QuizIntroduction.Content;
      },
      getQuizPassCriteria: function()
      {
          return QuizIntroduction.PassCriteria;
      }
    };
};