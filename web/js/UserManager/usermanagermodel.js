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
                success: function()
                {
                    alert('User Added');
                },
                error: function()
                {
                    alert('Server Error: User could not be added');
                }
            });
        }
    };
};

