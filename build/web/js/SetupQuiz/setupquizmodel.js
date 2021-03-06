QuizSetupModel = function()
{
    var QuizDetailsList = [];
    var GroupDetailsList = [];
    var QuizEvents = [];
    
    return{
      init: function(quizDetails, groupDetails, quizEvents)
      {
          QuizDetailsList = quizDetails;
          GroupDetailsList = groupDetails;
          QuizEvents = quizEvents;
      },
      setQuizDetails: function(quizDetails)
      {
          QuizDetailsList = quizDetails;
      },
      getQuizDetails: function()
      {
          return QuizDetailsList;
      },
      setGroupDetails: function(groupDetails)
      {
          GroupDetailsList = groupDetails;
      },
      getGroupDetails: function()
      {
          return GroupDetailsList;
      },
      setQuizEvents: function(quizEvents)
      {
          QuizEvents = quizEvents;
      },
      getQuizEvents: function()
      {
          return QuizEvents;
      },
      clearQuizEvents: function()
      {
           QuizEvents = [];  
      },
      getQuizEventById: function(quizId)
      {
          quizId = parseInt(quizId);
          
          for(var i = 0; i < QuizDetailsList.length; i++)
          {
              if(QuizDetailsList[i].QuizId === quizId)
                  return QuizDetailsList[i];
          }
          
          return null;
      }
    };
};

QuizEvent = function()
{
    this.QuizId = 0;
    this.QuizTitle = 0;
    this.GroupId = 0;
    this.GroupName = 0;
    this.OpenDate = 0;
    this.CloseDate = 0;
    this.Randomise = 0;
    this.NumberOfQuestions = 0;
    this.Feedback = 0;
};