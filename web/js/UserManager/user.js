/* 
 * User
 * 
 * This is the entity class for Users that are added/edited in the Quiz System
 * Provides get and set function and initialisation.
 */
User = function()
{
    var UserId = 0;
    var UserFirstName = "";
    var UserLastName = "";
    var UserEmail = "";
    var UserDescription = "";
    
    return {
        init: function(Id, FirstName, LastName, Email, Description)
        {
            UserId = Id;
            UserFirstName = FirstName;
            UserLastName = LastName;
            UserEmail = Email;
            UserDescription = Description;
        },
        setUserID: function(newUserId)
        {
            UserId = newUserId;
        },
        getUserID: function()
        {
            return UserId;
        },
        setUserFirstName: function(newFirstName)
        {
            UserFirstName = newFirstName;
        },
        getUserFirstName: function()
        {
            return UserFirstName;
        },
        setUserLastName: function(newLastName)
        {
            UserLastName = newLastName;
        },
        getUserLastName: function()
        {
            return UserLastName;
        },
        setUserEmail: function(newEmail)
        {
            UserEmail = newEmail;
        },
        getUserEmail: function()
        {
            return UserEmail;
        },
        setUserDescription: function(newDescription)
        {
            UserDescription = newDescription;
        },
        getUserDescription: function()
        {
            return UserDescription;
        }
    }; 
};