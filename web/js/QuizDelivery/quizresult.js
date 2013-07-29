var QuizResult = function()
{
  this.QuizId = 0;
  this.UserId = 0;
  this.Responses = [];
  
    return {
      setResponseArray: function(newResponseArray)
      {
          
      },
      getResponseArray: function()
      {
          return Responses;
      }
    };
};

var QuestionResponse = function()
{
  var QuestionId = 0;
  
  var SelectedResponse = 0;    
  
  return {
      getResponse: function()
      {
          return SelectedResponse;
      },
      setResponse: function(response)
      {
          SelectedResponse = response;
      },
      setQuestionId: function(newQuestionId)
      {
          QuestionId = newQuestionId;
      },
      getQuestionId: function()
      {
          return QuestionId;
      }  
  };
};