QuizSetupView = function()
{ 
  return{
      renderQuizDetailsList: function(quizDetailsList)
      {
        $('#quiz_list').empty();
        $('#quiz_list').append('<option>No Quiz Selected</option>');
        
        $.each(quizDetailsList, function(key, val)
        {
            $('#quiz_list').append('<option value="'+val.QuizId+'">'+val.QuizTitle+'</option>');
        });
      },
      renderGroupDetailsList: function(groupDetailsList)
      {
          
      },
      renderEventList: function()
      {
          
      },
      renderAddEventDialogue: function()
      {
          
      },
      getAllDetails: function ()
      {
          var quizEvent = new QuizEvent();
          
          quizEvent.GroupId = $('#group_list option:selected').val();
          quizEvent.QuizId = $('#quiz_list option:selected').val();
          
          quizEvent.OpenDate = $('#opening_date').val();
          quizEvent.CloseDate = $('#closing_date').val();
          quizEvent.RandomQuestions = $('#randomise_questions').is('checked');
          quizEvent.NumberOfQuestions = $('#number_questions').val();
          quizEvent.Feedback = $('#feedback').is('checked');
          
          return quizEvent;
      }
  };
};

