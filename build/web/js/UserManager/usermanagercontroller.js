UserManagerController = function($treeWidget, $userDialogue, $groupDialogue)
{
    var $TreeWidget = $treeWidget;
    var $UserDialogue = $userDialogue;
    var $GroupDialogue = $groupDialogue;
    
    var UserModel = new UserManagerModel();
    var UserView = new UserManagerView($TreeWidget, $GroupDialogue, $UserDialogue);
      
    return {
      init: function()
      {
          
          UserView.init($TreeWidget);   
      },
      fetchUsersFromServer: function(url)
      {
          
      },
      fetchGroupsFromServer: function(url)
      {
          
      },
      fetchUsersAndGroupsFromServer: function(url)
      {
          
      },
      addUserEvent: function(addUserCallback)
      {
          UserView.displayNewUserDialogue($userDialogue, addUserCallback, false, 0);
      },
      deleteUserEvent: function()
      {
          var userKey = UserView.getSelectedItem();
          
          if(typeof userKey === 'undefined')
          {
              $.alert('Nothing Selected', 'Please select the user that you wish to delete');
              return;  
          }
          else if(userKey.charAt(0) === 'g')
          {
              $.alert('Group Selected', 'You have selected a group, please select a user');
              return;
          }
          
          var userId = userKey.replace('u_','');
          
          UserModel.deleteUser(userId, controller.deleteUserCallback);
      },
      addGroupEvent: function(addGroupCallback)
      {
          UserView.displayNewGroupDialogue($groupDialogue, addGroupCallback, false, 0);
      },
      deleteGroupEvent: function()
      {
          var groupKey = UserView.getSelectedItem();
          
          if(typeof groupKey === 'undefined')
          {
             $.alert('Nothing Selected', 'Please select the group you wish to delete');
             return; 
          }
          if(groupKey.charAt(0) === 'u')
          {
              $.alert('User Selected', 'You have selected a user, please select a group');
              return;
          }
          
          var groupId = groupKey.replace('g_','');
          
          UserModel.deleteGroup(groupId, controller.deleteGroupCallback);
          
      },
      downloadGroupsandUsersEvent: function()
      {
        $.ajax({
           type: 'GET',
           url: 'User/get/allgroupsandusers',
           dataType:'json',
           success: function(data, status, jqXHR)
           {
               UserModel.parseGroupsandUsers(data);
               UserView.renderUserGroupList(UserModel.getUsersandGroups());
           },
           error: function(data, status, jqXHR)
           {
               
           }
        });      
      },
      downloadGroups: function()
      {
          $.ajax({
             type: 'GET',
             url: 'Groups/get/getgroups',
             dataType:'json',
             success: function()
             {
                 
             },
             error: function ()
             {
                 
             }
          });
      },
      addUserCallback: function(userDetails)
      {
          UserModel.addUser(userDetails);
          UserModel.sendUserToServer(userDetails, controller.downloadGroupsandUsersEvent);
      },
      addGroupCallback: function(groupDetails)
      {
          UserModel.addGroup(groupDetails);
          UserModel.sendGroupToServer(groupDetails, controller.downloadGroupsandUsersEvent);
      },
      deleteUserCallback: function()
      {
          //Trigger download of updated users and groups
          $.alert('User Deleted', 'User has been successfully deleted');
          controller.downloadGroupsandUsersEvent();
      },
      deleteGroupCallback: function()
      {
          $.alert('Group Deleted', 'Group has been successfully deleted');
          controller.downloadGroupsandUsersEvent();
      }
    };
    
};

