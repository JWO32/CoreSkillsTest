QuizSetupView = function()
{ 
    
    var EventCache = [];
    
  return{
      renderQuizDetailsList: function(quizDetailsList)
      {
        $('#quiz_list').empty();
        $('#quiz_list').append('<option>Select Quiz</option>');
        
        $.each(quizDetailsList, function(key, val)
        {
            $('#quiz_list').append('<option value="'+val.QuizId+'">'+val.QuizTitle+'</option>');
        });
      },
      renderGroupDetailsList: function(groupDetailsList)
      {
          $('#group_list').empty();
          $('#group_list').append('<option value="0">Select Group</option>');
          
          $.each(groupDetailsList, function(key, val)
          {
              $('#group_list').append('<option value="'+val.GroupId+'">'+val.GroupName+'</option>');
          });
      },
      renderEventList: function(eventsList)
      {
          var events = [];
          var eventTemplateSource = $('#quiz_event_template').html();
          
          for(i = 0; i < eventsList.length; i++)
          { 
            var template = Handlebars.compile(eventTemplateSource);

            var eventHTML = template(events);

            EventCache.push(eventHTML);
          }
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

