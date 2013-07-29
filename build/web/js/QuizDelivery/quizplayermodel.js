QuizPlayerModel = function()
{
  var Quiz = 0;
  var QuizIntroduction = 0;
  var QuizEnd = 0;
  
  
  var Responses = new Array();
  var NumberOfQuestions = 0;
  
    return{
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
      getDuration: function()
      {
          return Quiz.QuizDuration;
      },
      getNumberOfQuestions: function()
      {
          return Quiz.Questions.length;
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