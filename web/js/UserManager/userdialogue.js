
var UserDialogue = function(htmlElement, callback, edit, user)
{
    edit = typeof(edit==='undefined')? false : edit;
    
    htmlElement
        .dialog(
        {
                autoOpen: false,
                height: 800,
                width: 800,
                modal: true,
                title: 'Create/Edit User Details',
                show: 'blind',
                hide: 'blind',
                open: function()
                {
                    
                },
                buttons:
                {
                    "OK": function()
                    {
                        var userDetails = new User();
                        
                        var firstName = $('#first_name').val();
                        var lastName = $('#last_name').val();
                        var description = $('#user_description').val();
                        var email = $('#user_email').val();
                        
                        //Remember this works out to the group ID.
                        var groupId = $('#group_list :selected').val();
                        
                        
                        //userDetails.init(0, firstName, lastName, description, email, groupId)
                        
                        userDetails.FirstName = firstName;
                        userDetails.LastName = lastName;
                        userDetails.Description = description;
                        userDetails.Email = email;
                        userDetails.GroupId = groupId;
                        
                        callback(userDetails);
                        
                        $(this).dialog('destroy');

                    },
                    "Cancel": function()
                    {
                        $(this).dialog('destroy');
                    }
                },
                close: function()
                {
                    $(this).dialog('destroy');
                }
        }
        ).dialog('open');
};