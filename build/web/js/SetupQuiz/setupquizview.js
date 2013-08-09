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
      cacheEvents: function(eventsList)
      {
          if(eventsList.length === 0)
              return;
          
          var eventTemplateSource = $('#quiz_event_template').html();
          
          for(i = 0; i < eventsList.length; i++)
          { 
            var template = Handlebars.compile(eventTemplateSource);

            var eventHTML = template(eventsList[i]);

            EventCache.push(eventHTML);
          }
      },
      renderEventList: function()
      {
          if(EventCache.length > 0)
          {
              $('#quiz_events').empty();
            for(i = 0; i < EventCache.length; i++)
            {
                var currentEvent = EventCache[i];
                $('#quiz_events').append(currentEvent);
            }
          }else
          {
            $('#quiz_events').html('<h3>No Events</h3>');
          }
      },
      renderDefaultValues: function()
      {
          //Reset the form back to defaults.
          //
          
      },
      getAllDetails: function ()
      {
          var quizEvent = new QuizEvent();
          
          //TODO: add validation
          //
          quizEvent.GroupId = $('#group_list option:selected').val();
          quizEvent.QuizId = $('#quiz_list option:selected').val();
          
          quizEvent.OpenDate = $('#opening_date').val();
          quizEvent.CloseDate = $('#closing_date').val();
          quizEvent.RandomQuestions = $('#randomise_questions').is(':checked');
          quizEvent.Feedback = $('#feedback').is(':checked');
          quizEvent.NumberOfQuestions = $('#number_questions').val();
          
          return quizEvent;
      }
  };
};

