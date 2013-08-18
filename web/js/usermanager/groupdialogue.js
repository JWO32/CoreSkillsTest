var GroupDialogue = function(htmlElement, callback, edit, group)
{
    
  edit = typeof(edit==='undefined')? false: edit;
  
  htmlElement
    .dialog(
    {
        autoOpen: false,
        height: 'auto',
        width: 'auto',
        modal: true,
        title:'Add/Edit a Group',
        show:'blind',
        hide:'blind',
        open: function()
        {
            
        },
        buttons:
        {
            "OK": function()
            {
                groupDetails = new Group();
                
                var groupName = $('#group_name').val();
                var groupDescription = $('#group_description').val();
                
                groupDetails.GroupName = groupName;
                groupDetails.GroupDescription = groupDescription;
              
                callback(groupDetails);
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
    }).dialog('open');  
};

