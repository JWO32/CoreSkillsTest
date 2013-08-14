LoginController = function()
{
    var View = new LoginView();
    
    return{
        loginEvent: function ()
        {
          var emailAddress = View.getEmailAddress();
          
          if(emailAddress !== null)
              this.ajaxSendLogin(emailAddress);
        },
        doQuizEvent: function ()
        {
            
        },
        updateQuizList: function (eventList)
        {
            if(eventList !== null)
            {
                View.cacheQuizEvents(eventList);
                View.renderQuizEvents();
            }
        },
        ajaxSendLogin: function (emailAddress)
        {   
            $.ajax({
                url:'Dispatcher/getevents',
                method:'GET',
                dataType: 'json',
                data: 'email='+emailAddress,
                success: this.updateQuizList,
                error: function ()
                {
                    
                    
                }
            });
        },
        ajaxDoQuiz: function(quizId, quizEventId)
        {
           $.ajax({
             url: 'Dispatcher/doquiz',
             method: 'GET',
             dataType: 'json',
             data:'quizid='+quizId+"&quizeventid="+quizEventId,
             success: this.updateEventList,
             error: function ()
             {
                 
             }
           });
        }
    };
};

LoginView = function ()
{
    var cachedEvents = [];
    
    return{
      getEmailAddress: function()
      {
         return $('#email').val(); 
      },
      renderQuizEvents: function()
      {
          $('#quiz_event_list').empty();
          
          if(cachedEvents.length > 0)
          {
              for(var i = 0; i < cachedEvents.length; i++)
              {
                  $('#quiz_event_list').append(cachedEvents[i]);
              }
          }else
          {
              $('#quiz_event_list').append('<h3>No Quiz Events</h3>');
          }
      },
      cacheQuizEvents: function(eventList)
      {
          var templateSource = $('#quiz_events_template').html();
          
          for(var i = 0; i < eventList.length; i++)
          {
              //Small hack to change the value of Feedback to something human readable.
              if(eventList[i].Feedback === true)
                  eventList[i].Feedback = "will";
              else
                  eventList[i].Feedback = "will not";
              
              var template = Handlebars.compile(templateSource);
              var eventHTML = template(eventList[i]);
              
              cachedEvents.push(eventHTML);
          }   
      }
    };
};