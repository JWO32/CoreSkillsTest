UserManagerModel = function()
{
    AllGroups = [];
    AllUsers = [];
    UsersAndGroups = {};
    
    return {
        addGroup: function(newGroup)
        {
            AllGroups.push(newGroup);
        },
        getGroups: function()
        {
            return AllGroups;
        },
        addUser: function(newUser)
        {
            AllUsers.push(newUser);
        },
        getUsers: function()
        {
            return AllUsers;
        },
        getUsersandGroups: function()
        {
            return UsersAndGroups;
        },
        setUsersandGroups: function(newGroups)
        {
            UsersAndGroups = newGroups;
        },
        parseGroupsandUsers: function(data)
        {
           // Assign all users and groups to 
           UsersAndGroups = data;
        },
        deleteUser: function(userId, successCallback)
        {
          
            $.ajax({
                type:'delete',
                url:'User/delete/user'+userId,
                dataType:'json',
                success: successCallback,
                error: function(data)
                {     
                    $.alert('User Delete Error', 'User could not be deleted');
                }
            });
        },
        deleteGroup: function(groupId, successCallback)
        {
            $.ajax({
               type:'delete',
               url: 'User/delete/group/'+groupId,
               dataType: 'json',
               success: successCallback,
               error: function (data)
               {
                   $.alert('Group Delete Error', 'Group could not be deleted');
               }              
            });
        },
        sendUserToServer: function(user, successCallback)
        {
            var url = 'User/add/user';
            var data = JSON.stringify(user);
            data = 'user='+data;
            $.ajax({
                type:'POST',
                url: url,
                data: data,
                dataType: 'json',
                success: successCallback,
                error: function(data)
                {
                    $.alert('Add User Error', 'The user could not be added');
                }
            });
        },
        sendGroupToServer: function(group, successCallback)
        {
            var url = 'User/add/group';
            var data = JSON.stringify(group);
            data = 'group='+data;
            
            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                dataType: 'json',
                success: successCallback,
                error: function(data)
                {
                    $.alert('Add Group Error','The group could not be added');
                }
            });
         }
    };
};

