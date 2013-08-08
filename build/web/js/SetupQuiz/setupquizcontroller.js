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
                    alert("error");
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
                  alert("Error");
              }        
          });
        }
    };
};