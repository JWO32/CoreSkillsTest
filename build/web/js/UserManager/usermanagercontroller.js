UserManagerController = function($treeWidget, $userDialogue, $groupDialogue)
{
    var $TreeWidget = $treeWidget;
    var $UserDialogue = $userDialogue;
    var $GroupDialogue = $groupDialogue;
    
    var UserModel = new UserManagerModel();
    var UserView = new UserManagerView($TreeWidget, $GroupDialogue, $UserDialogue);
    
    var UserBaseURL = 'User/';
    
    
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
          
      },
      editUserEvent: function()
      {
          
      },
      addGroupEvent: function(addGroupCallback)
      {
          UserView.displayNewGroupDialogue($groupDialogue, addGroupCallback, false, 0);
      },
      deleteGroupEvent: function()
      {
          
      },
      editGroupEvent: function()
      {
          
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
           }
        });   
        
        
      },
      downloadGroups: function()
      {
          
      },
      addUserCallback: function(userDetails)
      {
          UserModel.addUser(userDetails);
          
          UserModel.sendUserToServer(userDetails);
      },
      addGroupCallback: function(groupDetails)
      {
          UserModel.addGroup(groupDetails);
          
          UserModel.sendGroupToServer(groupDetails);
      }
    };
    
};

