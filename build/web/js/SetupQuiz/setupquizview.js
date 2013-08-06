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
      renderQuizEvents: function()
      {
          
      },
      renderAddEventDialogue: function()
      {
          
      }     
  };
};

