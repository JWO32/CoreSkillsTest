
var UserDialogue = function(htmlElement, callback, edit, user)
{
    edit = typeof(edit==='undefined')? false : edit;
    
    htmlElement
        .dialog(
        {
                autoOpen: false,
                height: 'auto',
                width: 'auto',
                modal: true,
                title: 'Create User',
                show: 'blind',
                hide: 'blind',
                open: function()
                {
                     $.ajax({
                        url: 'User/get/groupdetails',
                        method: 'GET',
                        dataType: 'json',
                        success: function(data)
                        {
                            $('#group_list').empty();
                            $('#group_list').append('<option value="0">Default Group</option>');

                            $.each(data, function(key, val)
                            {
                                $('#group_list').append('<option value="'+val.GroupId+'">'+val.GroupName+'</option>');
                            });
                        },
                        error: function(data)
                        {
                           var errorObj = jQuery.parseJSON(data.responseText);
                           var errorTitle = errorObj.Status;
                           var errorMsg = errorObj.Message;

                           if(errorTitle !== null && errorMsg !== null)
                             $.alert(errorTitle, errorMsg);
                           else
                             $.alert("Group Download Error", "Unable to download group list");
                         
                            $('#group_list').empty();
                            $('#group_list').append('<option value="0">Default Group</option>');
                        }
                     });
                        
                      
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