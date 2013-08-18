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
        removeGroup: function(delGroup)
        {
            
        },
        getGroups: function()
        {
            return AllGroups;
        },
        addUser: function(newUser)
        {
            AllUsers.push(newUser);
        },
        removeUser: function(delUser)
        {
            
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
        sendUserToServer: function(user)
        {
            var url = 'User/add/user';
            var data = JSON.stringify(user);
            data = 'user='+data;
            $.ajax({
                type:'POST',
                url: url,
                data: data,
                dataType: 'json',
                success: function(data)
                {
                    alert('User Added');
                },
                error: function()
                {
                    alert('Server Error: User could not be added');
                }
            });
        },
        sendGroupToServer: function(group)
        {
            var url = 'User/add/group';
            var data = JSON.stringify(group);
            data = 'group='+data;
            
            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                dataType: 'json',
                success: function()
                {
                    alert('Group Added');
                },
                error: function()
                {
                    alert('Error: group coould not be added to server');
                }
            });
         }
    };
};

