QuizManagerController = (function ($)
{
    var deleteQuiz = function ()
    {
        var quizId;

        quizId = QuizManagerController.View.getSelectedQuiz();
        
        //This is an array, will add feature to deal with multiple deletes in future version
        //at the moment, we are just going to use the first element to delete 1 quiz only.
        
        $.ajax({
            url:'Quiz/delete/'+quizId[0],
            dataType: 'json',
            success: downloadQuizList,
            method: 'delete',
            error: function (data)
            {
                var errorObj = jQuery.parseJSON(data.responseText);
                var errorTitle = errorObj.Status;
                var errorMsg = errorObj.Message;

                if(errorTitle !== null && errorMsg !== null)
                  $.alert(errorTitle, errorMsg);
                else
                  $.alert("Quiz Delete Error", "There was an error processing this request");
            }

        });
    };
    
    var editQuiz = function (quizId)
    {
        
        $.ajax({
           url:'Quiz/edit',
           dataType: 'json',
           data: 'quizId='+quizId,
           method: 'get',
           success: function (data, textStatus)
           {
               if(data.redirect)
               {
                   window.location.href = data.redirect;
               }
           }
        });
        
    };
    var updateQuizList = function (quizList)
    {
        QuizManagerController.View.cacheQuizList(quizList);
        QuizManagerController.View.renderQuizList();
    };
    
    var downloadQuizList =  function ()
    {
        $.ajax({
            url: 'Quiz/getfulldetails',
            dataType: 'json',
            method: 'get',
            success: updateQuizList,
            error: function (data)
            {
                var errorObj = jQuery.parseJSON(data.responseText);
                var errorTitle = errorObj.Status;
                var errorMsg = errorObj.Message;

                if(errorTitle !== null && errorMsg !== null)
                  $.alert(errorTitle, errorMsg);
                else
                  $.alert("Quiz Download Error", "Unable to download quiz list");
            }
        });
    };
 
    return {
        init: function ()
        {
            downloadQuizList();
        },
        deleteQuizEvent: function ()
        {
            var quizId;

            quizId = QuizManagerController.View.getSelectedQuiz();

            if(quizId.length > 1)
            {
                $.alert('Cannot Delete Multiple Quizzes', 'Please select one quiz at a time.');
                return;
            }else if(typeof quizId === 'undefined')
            {
                $.alert('Select a Quiz to Delete', 'No quiz is selected, please select a quiz');
                return;
            }

                $.alertconfirmation('Are You Sure?', 'Do you really wish to delete this quiz?', deleteQuiz);
        },
        editQuizEvent: function ()
        {
            var quizId;
            
            quizId = QuizManagerController.View.getSelectedQuiz();

            if(quizId.length > 1)
            {
                $.alert('Cannot Delete Multiple Quizzes', 'Please select one quiz at a time.');
                return;
            }else if(typeof quizId === 'undefined')
            {
                $.alert('Select a Quiz to Delete', 'No quiz is selected, please select a quiz');
                return;
            }
            
            editQuiz(quizId[0]);
        }
    };
}(jQuery));

QuizManagerController.View = (function ()
{
  var QuizCache = [];
    
    return {
      getSelectedQuiz: function ()
      {
          var selectedEventList = [];
                  
          $('#quiz_wrapper input:checked').each(function()
          {
              var id = $(this).attr('id');
              
              // Remove the leading characters from the HTML DIV id.
              id = id.replace('q_','');
              
              selectedEventList.push(id);
          });
          
          return selectedEventList;
      },
      cacheQuizList: function(quizList)
      {
          var templateSource = $('#quiz_template').html();
          var template = Handlebars.compile(templateSource);
          
          for(var i = 0; i < quizList.length; i++)
          {
              var templateHTML = template(quizList[i]);
              QuizCache.push(templateHTML);
          }
      },
      renderQuizList: function ()
      {
          for(var i = 0; i < QuizCache.length; i++)
          {
              $('#quiz_wrapper').append(QuizCache[i]);
          }
      }
    };
    
}(QuizManagerController));