/* 
 * Group
 * 
 * This is the group entity class, provides getter and setter functions for all properties.
 * Users are managed as a list of objects -- TODO: See if this might be better managed as collection of properties
 * 
 */

Group = function()
{
    var GroupID = 0;
    var GroupName = "";
    var GroupDescription = "";
    var Users = [];
    
    return {
        init: function(id, newGroupName, newGroupDescription, newUsers)
        {
            GroupID = id;
            GroupName = newGroupName;
            GroupDescription = newGroupDescription;
            Users = newUsers;
        },
        setGroupId: function(newId)
        {
            GroupID = newId;
        },
        getGroupId: function()
        {
            return GroupID;
        },
        setGroupName: function(newGroupName)
        {
            GroupName = newGroupName;
        },
        getGroupName: function()
        {
            return GroupName;
        },
        setGroupDescription: function(newGroupDescription)
        {
            GroupDescription = newGroupDescription;
        },
        getGroupDescription: function()
        {
            return GroupDescription;
        },
        setUsers: function(userlist)
        {
            Users = userlist;
        },
        getUsers: function()
        {
            return Users;
        } 
    };  
};