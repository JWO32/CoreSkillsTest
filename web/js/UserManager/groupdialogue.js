var GroupDialogue = function(htmlElement, callback, edit, group)
{
  edit = typeof(edit==='undefined')? false: edit;
  
  htmlElement
    .dialog(
    {
        autoOpen: false,
        height: 800,
        width: 800,
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
                var groupDescription = $('#group_desription').val();
                
                groupDetails.
              
                callback(groupDetails);
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

