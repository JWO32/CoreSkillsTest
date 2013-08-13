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
        updateQuizList: function ()
        {
            
        },
        ajaxSendLogin: function (emailAddress)
        {   
            $.ajax({
                url:'Dispatcher/getevents',
                method:'GET',
                dataType: 'json',
                data: 'email='+emailAddress,
                success: function ()
                {
                    
                },
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
      displayQuizEvents: function()
      {
          
      },
      createEventsFromTemplates: function(eventList)
      {
          
          
      }
    };
};