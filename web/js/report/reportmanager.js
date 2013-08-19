//Slight change in development of the JavaScript client management code
//Using the 'Revealing Module Pattern' makes it easier to only expose the
//necessary functions to the outside world.

//Also makes it possible to implement the Model and View as 'sub-modules' of the controller.

ReportManager = (function($)
{
    return {
        init: function ()
        {
            this.downloadGroupsEvent();
        },
        updateGroupList: function (groupList)
        {
            ReportManager.Model.setGroupList(groupList);
            ReportManager.View.renderGroupList(groupList);
        },
        downloadGroupsEvent: function ()
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
                    $.alert(errorTitle, errorMsg);
                  else
                    $.alert("Group Download Error", "Unable to download group list");
               }
            });
        },
        downloadReportEvent: function()
        {
            var groupId = this.View.getCurrentGroup();
            
            if(groupId === 0)
            {
                $.alert("No Group Selected", "Please select a group to view a report");
                return;
            }
            
            this.downloadReportForGroup(groupId);
        },
        downloadReportForGroup: function(groupId)
        {
            $.ajax({
               url: 'Report/getgroupresults', 
               data:'groupId='+groupId,
               method: 'GET',
               dataType: 'json',
               success: function()
               {
                   
               },
               error: function(data)
               {
                  var errorObj = jQuery.parseJSON(data.responseText);
                  var errorTitle = errorObj.Status;
                  var errorMsg = errorObj.Message;
                  
                  if(errorTitle !== null && errorMsg !== null)
                    $.alert(errorTitle, errorMsg);
                  else
                    $.alert("Group Download Error", "Unable to download group list");
               }
            });
            
        },
        clearCurrentReportEvent: function ()
        {
            
        }
    };
})(jQuery);

ReportManager.Model = (function()
{
    var GroupList = [];
    var Results = [];
 
    return {
      getGroupList: function ()
      {
          return GroupList;
      },
      setGroupList: function (groupList)
      {
          GroupList = groupList;
      },
      getResults: function ()
      {
          return Results;
      },
      setResults: function (results)
      {
          Results = results;
      }
    };    
})(ReportManager);

ReportManager.View = (function ()
{
    var ReportCache = [];
    
    return{
        renderGroupList: function (groupList)
        {
            $('#group_list').empty();
            $('#group_list').append('<option value="0">Select Group</option>');

            $.each(groupList, function(key, val)
            {
                $('#group_list').append('<option value="'+val.GroupId+'">'+val.GroupName+'</option>');
            });
        },
        cacheResults: function (resultList)
        {

        },
        getCurrentGroup: function ()
        {
            return $('#group_list').val();
        }
    };
})(ReportManager);