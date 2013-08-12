QuizSetupController = function()
{
    var Model = new QuizSetupModel();
    var View = new QuizSetupView();
       
    return {
        init: function(model, view)
        {
            Model = model;
            View = view;
        },
        initAllDetails: function()
        {
            this.downloadQuizList();
            this.downloadUserGroup();
            this.downloadQuizEventList(this);
        },
        addQuizEvent: function()
        {
            var eventDetails;
            eventDetails = View.getAllDetails();
            
            if(eventDetails !== null)
            {
                this.sendQuizEventDetails(eventDetails, this);
            }
        },
        editQuizEvent: function()
        {
            var selectedQuizEventId = View.getSelectedQuizEvents();
            
            if(selectedQuizEventId.length>1)
            {
                //TODO: move this to the view using jquery dialogue
                //
                alert('Please select only 1 quiz to edit');
                return;
            }
            
            var selectedEvent = Model.getQuizEventById(selectedQuizEventId);
            
            View.displayEditDialogue(selectedEvent);
        },
        deleteQuizEvent: function()
        {
            var selectedQuizId = View.getSelectedQuizEvents();
            
            if(selectedQuizId.length>1)
            {
                //TODO: move this to the view using jquery dialogue
                //
                alert('Please select only 1 quiz to delete');
                return;
            }

            this.deleteQuizEvents(selectedQuizId, this);
        },
        updateQuizList: function(data)
        {
            Model.setQuizDetails(data);
            View.renderQuizDetailsList(Model.getQuizDetails());
        },
        changeQuizEvent: function()
        {
            var quizId = View.getSelectedQuizId();
            var QuizEventDetails = 0;
            
            QuizEventDetails = Model.getQuizEventById(quizId);
            
            View.setNumberOfQuestions(QuizEventDetails.NumberOfQuestions);          
        },
        checkNumberOfQuestionsEvent: function()
        {
            var enteredNumber = View.getNumberOfQuestions();
            var currentQuizEvent = View.getSelectedQuizId();
            var currentEventObject = Model.getQuizEventById(currentQuizEvent);
            var maxQuestions = 1;
            
            if(currentEventObject !== null)
                maxQuestions = currentEventObject.NumberOfQuestions;
       
            if(enteredNumber > maxQuestions)
            {
                View.setNumberOfQuestions(maxQuestions);

            }else if(enteredNumber <= 0)
            {
                //Make sure the user doesn't set the number of questions to 0
                View.setNumberOfQuestions(1);
            }
            //TODO: Alert the user that their input has been changed.
       
        },
        updateGroupList: function(data)
        {
            Model.setGroupDetails(data);
            View.renderGroupDetailsList(Model.getGroupDetails());
        },
        updateEventList: function(data)
        {
            // Reinitialise the model
            Model.clearQuizEvents();
            View.clearEventCache();
            
            if(data instanceof Array)
            {
                // if the data is an array - update the model
                // cache the events
                // render the list of events
                Model.setQuizEvents(data);
                View.cacheEvents(Model.getQuizEvents());
                View.renderEventList();
            }else
            {
                //if the data is not an array - render the empty eventlist
                View.renderEventList();
            }     
        },
        sendQuizEventDetails: function(eventData, controllerRef)
        {    
            //TODO: This is a bit clunky refactor network operations into separate JavaScript object?
            //
            eventData = JSON.stringify(eventData);
            
            $.ajax({
                url:'Event/addevent',
                method:'POST',
                dataType: 'json',
                data: 'event='+eventData,
                success: function(data)
                {
                    controllerRef.downloadQuizEventList(controllerRef);
                },
                error: function()
                {
                  var errorObj = jQuery.parseJSON(data.responseText);
                  var errorTitle = errorObj.Status;
                  var errorMsg = errorObj.Message;
                   
                   if(errorTitle !== null && errorMsg !== null)
                        View.renderDialogue(errorTitle, errorMsg);
                   else
                        View.renderDialogue("Event Add Error", "Unable to add event");
                }
            });
        },
        downloadQuizList: function()
        {
            $.ajax({
               url:'Quiz/quizlist',
               method:'GET',
               dataType: 'json',
               success: this.updateQuizList,
               error: function(data)
               {
                  var errorObj = jQuery.parseJSON(data.responseText);
                  var errorTitle = errorObj.Status;
                  var errorMsg = errorObj.Message;
                   
                   if(errorTitle !== null && errorMsg !== null)
                        View.renderDialogue(errorTitle, errorMsg);
                   else
                       View.renderDialogue("Quiz List Error", "Unable to download quiz list");
               }
            });
        },
        downloadUserGroup: function()
        {
            $.ajax({
               url: 'User/get/groupdetails',
               method: 'GET',
               dataType: 'json',
               success: this.updateGroupList,
               error: function(data)
               {
                  var errorObj = jQuery.parseJSON(data.responseText);
                  var errorTitle = errorObj.Status;
                  var errorMsg = errorObj.Message;
                  
                  if(errorTitle !== null && errorMsg !== null)
                    View.renderDialogue(errorTitle, errorMsg);
                  else
                     View.renderDialogue("Group Download Error", "Unable to download group list");
               }
            });
        },
        downloadQuizEventList: function(controllerRef)
        {
          $.ajax({
              url:'Event/eventlist',
              method:'GET',
              cache: 'no',
              dataType:'json',
              success: controllerRef.updateEventList,
              error: function(data)
              {
                  var errorObj = jQuery.parseJSON(data.responseText);
                  var errorTitle = errorObj.Status;
                  var errorMsg = errorObj.Message;
                  
                  if(errorTitle !== null && errorMsg !== null)
                        View.renderDialogue(errorTitle, errorMsg);
                   else
                       View.renderDialogue("Quiz Event Download Error", "Unable to download event list");
                  controllerRef.updateEventList(null);
              }        
          });
        },
        deleteQuizEvents: function(eventToDelete, controllerRef)
        {
            $.ajax({
               url:'Event/delete/'+eventToDelete,
               method:'DELETE',
               dataType:'json',
               success: function(data)
               {
                   controllerRef.downloadQuizEventList();
               },
               error: function(data)
               {
                  var errorObj = jQuery.parseJSON(data.responseText);
                  var errorTitle = errorObj.Status;
                  var errorMsg = errorObj.Message;
                  
                  View.renderDialogue(errorTitle, errorMsg);
               }          
            });
        }
    };
};