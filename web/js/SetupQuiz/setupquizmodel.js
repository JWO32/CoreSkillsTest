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
      }
    };
};

QuizEvent = function()
{
    this.QuizTitle = 0;
    this.OpenDate = 0;
    this.CloseDate = 0;
    this.RandomQuestions = 0;
    this.NumberOfQuestions = 0;
    this.Feedback = 0;
};