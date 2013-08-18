/* 
 * User
 * 
 * This is the entity class for Users that are added/edited in the Quiz System
 * Provides get and set function and initialisation.
 */
User = function()
{
    this.Id = 0;
    this.FirstName = "";
    this.LastName = "";
    this.Email = "";
    this.Description = "";
    this.DateAdded = new Date();
    this.Group = 0;
    
    return {
        init: function(id, firstName, lastName, email, description, groupId)
        {
            Id = id;
            FirstName = firstName;
            LastName = lastName;
            Email = email;
            Description = description;
            GroupId = groupId;
        },
        setUserID: function(newUserId)
        {
            Id = newUserId;
        },
        getUserID: function()
        {
            return Id;
        },
        setFirstName: function(newFirstName)
        {
            FirstName = newFirstName;
        },
        getFirstName: function()
        {
            return FirstName;
        },
        setLastName: function(newLastName)
        {
            LastName = newLastName;
        },
        getLastName: function()
        {
            return LastName;
        },
        setEmail: function(newEmail)
        {
            Email = newEmail;
        },
        getEmail: function()
        {
            return Email;
        },
        setDescription: function(newDescription)
        {
            Description = newDescription;
        },
        getDescription: function()
        {
            return Description;
        }
    }; 
};