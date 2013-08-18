QuizSetupView = function()
{    
    var EventCache = [];
    
  return{
      renderQuizDetailsList: function(quizDetailsList)
      {
        $('#quiz_list').empty();
        $('#quiz_list').append('<option value="0">Select Quiz</option>');
        
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
      getSelectedQuizEvents: function()
      {
          var selectedEventList = [];
                  
          $('#quiz_events input:checked').each(function()
          {
              var id = $(this).attr('id');
              
              // Remove the leading characters from the HTML DIV id.
              id = id.replace('qe_','');
              
              selectedEventList.push(id);
          });
          
          return selectedEventList;
      },
      cacheEvents: function(eventsList)
      {
          if(eventsList.length === 0)
              return;
          
          var eventTemplateSource = $('#quiz_event_template').html();
          
          for(i = 0; i < eventsList.length; i++)
          { 
            //Little bit of a hack, but need to change boolean true/false
            //into Yes/No for user friendliness.
            //These are compiled into HTML templates and so don't affect data in the model
            if(eventsList[i].Feedback === true)
                eventsList[i].Feedback = 'Yes';
            else
                eventsList[i].Feedback = 'No';
            
            if(eventsList[i].RandomQuestions === true)
                eventsList[i].RandomQuestions = 'Yes';
            else
                eventsList[i].RandomQuestions = 'No';
            
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
            for(var i = 0; i < EventCache.length; i++)
            {
                var currentEvent = EventCache[i];
                $('#quiz_events').append(currentEvent);
            }
          }else
          {
            $('#quiz_events').html('<h3>No Events</h3>');
          }
      },
      getSelectedGroupId: function()
      {
        return $('#group_list option:selected').val();  
      },
      getSelectedQuizId: function()
      {
        return $('#quiz_list option:selected').val();  
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
          
          //Ensure that a group has been picked
          //
          if(quizEvent.GroupId === '0')
          {
              alert('Please select a group');
              return null;
          }
          
          //Ensure that a quiz has been picked 
          //
          if(quizEvent.QuizId === '0')
          {
              alert('Please select a quiz');
              return null;
          }
          
          var openDate = $('#opening_date').datepicker('getDate');
          var closeDate = $('#closing_date').datepicker('getDate');
          
          // Validate open and close dates
          //
          if(closeDate < openDate)
          {
              alert('End date is before open date!');
              return null;
          }
          
          quizEvent.OpenDate = $('#opening_date').val();
          quizEvent.CloseDate = $('#closing_date').val();
         
          
          quizEvent.RandomQuestions = $('#randomise_questions').is(':checked');
          quizEvent.Feedback = $('#feedback').is(':checked');
          quizEvent.NumberOfQuestions = $('#number_questions').val();
          
          return quizEvent;
      },
      setNumberOfQuestions: function(numberOfQuestions)
      {
          $('#number_questions').val(numberOfQuestions);
      },
      getNumberOfQuestions: function()
      {
          return $('#number_questions').val();
      },
      renderDialogue: function(title, message)
      {
          $('<div></div>').dialog({
             modal: true,
             title: title,
             width: 'auto',
             height: 'auto',
             buttons: {
                 "OK": function()
                 {
                     $(this).dialog('close');
                 }
             },
             close: function(event, ui)
             {
                 $(this).remove();
             },
             resizable: false            
          }).text(message);
      },
      clearEventCache: function ()
      {
          EventCache = [];
      },
      displayEditDialogue: function(selectedEvent)
      {
          
        $('#new_event_form').dialog({
            autoOpen: false,
            height: 'auto',
            width: 'auto',
            modal: true,
            title:'Edit Quiz Event',
            show:'blind',
            hide:'blind',
            buttons:{
                        "OK": function()
                        {

                        },
                        "Cancel": function()
                        {
                            $(this).dialog('destroy');
                        }
                    },
            close: function()
            {
                $(this).dialog('destroy');
            }
        }).dialog('open');
      }
  };
};

