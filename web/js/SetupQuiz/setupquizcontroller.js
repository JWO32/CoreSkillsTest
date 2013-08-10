QuizSetupController = function()
{
    var SetupModel = QuizSetupModel();
    var SetupView = QuizSetupView();
       
    return {
        init: function()
        {
            
        },
        initAllDetails: function()
        {
            this.downloadQuizList();
            this.downloadUserGroup();
            this.downloadQuizEventList();
        },
        addQuizEvent: function()
        {
            var eventDetails;
            eventDetails = SetupView.getAllDetails();
            
            if(eventDetails !== null)
            {
                this.sendQuizEventDetails(eventDetails);
            }
        },
        editQuizEvent: function()
        {
            
        },
        deleteQuizEvent: function()
        {
            
        },
        updateQuizList: function(data)
        {
            SetupModel.setQuizDetails(data);
            SetupView.renderQuizDetailsList(SetupModel.getQuizDetails());
        },
        changeQuizEvent: function()
        {
            var quizId = SetupView.getSelectedQuizId();
            var numberOfQuestions = 0;
            var QuizEventDetails = 0;
            
            QuizEventDetails = SetupModel.getQuizEventById(quizId);
            
            SetupView.setNumberOfQuestions(QuizEventDetails.NumberOfQuestions);
            
        },
        checkNumberOfQuestionsEvent: function()
        {
            var enteredNumber = SetupView.getNumberOfQuestions();
            var currentQuizEvent = SetupView.getSelectedQuizId();
            var currentEventObject = SetupModel.getQuizEventById(currentQuizEvent);
            var maxQuestions = currentEventObject.NumberOfQuestions;
            
            if(enteredNumber > maxQuestions)
            {
                SetupView.setNumberOfQuestions(maxQuestions);

            }else if(enteredNumber <= 0)
            {
                //Make sure the user doesn't set the number of questions to 0
                SetupView.setNumberOfQuestions(1);
            }
            //TODO: Alert the user that their input has been changed.
            
            
        },
        updateGroupList: function(data)
        {
            SetupModel.setGroupDetails(data);
            SetupView.renderGroupDetailsList(SetupModel.getGroupDetails());
        },
        updateEventList: function(data)
        {
            if(data instanceof Array)
            {
                // if the data is an array - update the model
                // cache the events
                // render the list of events
                SetupModel.setQuizEvents(data);
                SetupView.cacheEvents(SetupModel.getQuizEvents());
                SetupView.renderEventList();
            }else
            {
                //if the data is not an array - render the empty eventlist
                SetupView.renderEventList();
            }     
        },
        sendQuizEventDetails: function(eventData)
        {         
            eventData = JSON.stringify(eventData);
            
            $.ajax({
                url:'Event/addevent',
                method:'POST',
                dataType: 'json',
                data: 'event='+eventData,
                success: this.downloadQuizEventList,
                error: function()
                {
                    alert("Server Error: Database not available");
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
                   alert('Server Error: Database not available');
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
                   alert('Server Error: Database not available');
               }
            });
        },
        downloadQuizEventList: function()
        {
          $.ajax({
              url:'Event/eventlist',
              method:'GET',
              dataType:'json',
              success: this.updateEventList,
              error: function(data)
              {
                  alert("Server Error: Database not available");
              }        
          });
        }
    };
};