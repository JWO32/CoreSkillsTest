UserManagerController = function(hierarchicalComponent, userDialogue, groupDialogue)
{
    var UserModel = new UserManagerModel();
    var UserView = new UserManagerView();
    var UserDialogue = userDialogue;
    var GroupDialogue = groupDialogue;
    
    var UserBaseURL = 'User/';
    
    
    return {
      init: function()
      {
          
          UserView.init();
          
          $.ajaxSetup ({
                        // Disable caching of AJAX responses
                        cache: false
                      });
          
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
          UserView.displayNewUserDialogue(userDialogue, addUserCallback, false, 0);
      },
      deleteUserEvent: function()
      {
          
      },
      editUserEvent: function()
      {
          
      },
      addGroupEvent: function(addGroupCallback)
      {
          
      },
      deleteGroupEvent: function()
      {
          
      },
      editGroupEvent: function()
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

