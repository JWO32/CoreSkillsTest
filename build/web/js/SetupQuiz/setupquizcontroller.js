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
            SetupModel.setEventDetails(data);
            SetupView.renderEventList(SetupModel.getEventDetails());
        },
        sendQuizEventDetails: function(eventData)
        {         
            $.ajax({
                url:'Event/add',
                method:'POST',
                dataType: 'json',
                data: 'event='+eventData,
                success: this.updateEventList,
                error: function()
                {
                    
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
               error: function()
               {
                   
               }
            });
        },
        downloadUserGroup: function()
        {
            $.ajax({
               url: 'Group/grouplist',
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
              url:'QuizEvent/eventlist',
              method:'GET',
              dataType:'json',
              success: this.updateEventList,
              error: function(data)
              {
                  
              }        
          });
        }
    };
};