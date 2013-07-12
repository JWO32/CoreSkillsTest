UserManagerView = function(ListWidget, AddGroupForm, AddUserForm)
{
    var HierarchyListWidget = ListWidget;
    var AddGroupDialogue = AddGroupForm;
    var AddUserDialogue = AddUserForm;
    
    return{
        init: function()
        {
          //AddGroupDialogue.empty().load('js/templ/new_group.html');
          //AddUserDialogue.empty().load('js/templ/new_user.html');
        },
        render: function(GroupList)
        {
            
        },
        renderHierarchicalList: function(GroupList)
        {
            
        },
        displayNewUserDialogue: function(htmlElement, callback, edit, user)
        {
            htmlElement.empty().load('js/templ/new_user.html');
            
            UserDialogue(htmlElement, callback, edit, 0);
        }
    };
};